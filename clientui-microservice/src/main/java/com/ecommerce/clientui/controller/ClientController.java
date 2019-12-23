package com.ecommerce.clientui.controller;

import com.ecommerce.clientui.beans.AdventureBean;
import com.ecommerce.clientui.beans.ClientBean;
import com.ecommerce.clientui.exception.AdventureNotFoundException;
import com.ecommerce.clientui.exception.CustomException;
import com.ecommerce.clientui.exception.UnauthorisedException;
import com.ecommerce.clientui.proxies.MicroserviceAdventureProxy;
import com.ecommerce.clientui.proxies.MicroserviceLoginProxy;
import com.ecommerce.clientui.responseDTO.ClientResponseDTO;
import com.ecommerce.clientui.service.impl.ClientServiceImpl;
import com.ecommerce.clientui.utils.CookiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.ecommerce.clientui.constants.SecurityConstants.*;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ClientController {

    @Autowired
    MicroserviceAdventureProxy microserviceAdventureProxy;

    @Autowired
    MicroserviceLoginProxy microserviceLoginProxy;

    @Autowired
    ClientServiceImpl clientService;

    @InitBinder
    public void allowEmptyDateBinding( WebDataBinder binder )
    {
        // set empty values as null instead of empty string.
        binder.registerCustomEditor( String.class, new StringTrimmerEditor( true ));
    }

    @ModelAttribute("logged")
    public Boolean bindCookies(HttpServletRequest request) {
        String jwt_token = CookiesUtils.getCookie(request, "jwt_token");
        return (jwt_token != null && !jwt_token.isEmpty());
    }

    @RequestMapping("/")
    public String home(Model model) {
        return "index";
    }

    @RequestMapping("/about")
    public String aboutUs(Model model, HttpServletRequest request) {
        List<AdventureBean> adventures = microserviceAdventureProxy.adventureList(request.getHeader(AUTHORIZATION_HEADER));
        model.addAttribute("adventures", adventures);
        model.addAttribute("logged", true);
        return "about";
    }

    @RequestMapping("/blog")
    public String blog(Model model, HttpServletRequest request) {
        List<AdventureBean> adventures = microserviceAdventureProxy.adventureList(request.getHeader(AUTHORIZATION_HEADER));
        model.addAttribute("adventures", adventures);
        return "blog";
    }

    @RequestMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @RequestMapping("/adventures")
    public String adventures(Model model, HttpServletRequest request) {
        try {
            List<AdventureBean> adventures = microserviceAdventureProxy.adventureList(request.getHeader(AUTHORIZATION_HEADER));
            model.addAttribute("adventures", adventures);
        } catch (UnauthorisedException unauthorisedException) {
            return "redirect:/login";
        }
        return "adventures/list";
    }

    @RequestMapping("/adventures/{id}")
    public String adventures(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        try {
            AdventureBean adventure = microserviceAdventureProxy.displayAdventure(id, request.getHeader(AUTHORIZATION_HEADER));
            model.addAttribute("adventure", adventure);
        } catch (UnauthorisedException unauthorisedException) {
            return "redirect:/login";

        } catch (AdventureNotFoundException adventureNotFoundException){
            return "redirect:/error404";
        }
        return "adventures/detail";
    }

    @RequestMapping("/error404")
    public String error404(Model model) {
        return "404";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @RequestMapping(value = "/register", method = POST)
    public ModelAndView register(@Valid @ModelAttribute("register") @Validated ClientBean clientBean, BindingResult bindingResult, ModelMap model, HttpServletRequest request, @RequestParam("confirm_password") String confirmPassword) {
        if (bindingResult.hasErrors()) {
            var error = new Object() {
                String message = "";
            };
            bindingResult.getFieldErrors().forEach(f -> error.message += f.getField() + ": " + f.getDefaultMessage() + "<br/>");
            model.addAttribute("error", error.message);
            return new ModelAndView("register", model);
        }
        if (clientBean.getPassword() != null && confirmPassword != null && !clientBean.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "2 passwords are differents");
            return new ModelAndView("register", model);
        }
        try {
            microserviceLoginProxy.postRegister(clientBean, request.getHeader(REFERER_HEADER));
        } catch (CustomException customException) {
            model.addAttribute("error", customException.getErrorResponse().getErrorMsg());
            return new ModelAndView("register", model);
        }
        model.addAttribute("success", "Welcome here, sign in and discover our adventures");
        return new ModelAndView("login", model);
    }

    @RequestMapping(value = "/login", method = POST)
    public ModelAndView login(@Valid @ModelAttribute("login") @Validated ClientBean clientBean, BindingResult bindingResult, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (bindingResult.hasErrors()) {
                var error = new Object() {
                    String message = "";
                };
                bindingResult.getFieldErrors().forEach(f -> error.message += f.getField() + ": " + f.getDefaultMessage() + "\n");
                model.addAttribute("error", error.message);
                return new ModelAndView("login", model);
            }
            if (CookiesUtils.getCookie(request, JWT_COOKIE) != null)  {
                CookiesUtils.removeCookie(JWT_COOKIE, response);
            }
            String token = microserviceLoginProxy.postLogin(clientBean, request.getHeader(REFERER_HEADER));
            Cookie cookie = new Cookie(JWT_COOKIE, token);
            cookie.setMaxAge(60 * 60); // expires in 1 hour
            // cookie.setSecure(true); https only
            cookie.setHttpOnly(true); // JS not able to read it
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (CustomException customException) {
            String message = customException.getErrorResponse().getErrorMsg();
            if (customException instanceof UnauthorisedException) {
                message = "Wrong username or password";
            }
            model.addAttribute("error", message);
            return new ModelAndView("login", model);
        }
        return new ModelAndView("redirect:/account", model);
    }

    @RequestMapping("/account")
    public ModelAndView account(ModelMap model) {
        try {
            Optional<ClientResponseDTO> clientResponseDTO = clientService.getUserInformations();
            model.addAttribute("client", clientResponseDTO.get());
        } catch (UnauthorisedException unauthorisedException) {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("account/index", model);
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletResponse response) {
        CookiesUtils.removeCookie(JWT_COOKIE, response);
        return new ModelAndView("redirect:/login");
    }
}
