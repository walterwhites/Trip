package com.ecommerce.clientui.controller;

import com.ecommerce.clientui.beans.AdventureBean;
import com.ecommerce.clientui.beans.ClientBean;
import com.ecommerce.clientui.exception.UnauthorisedException;
import com.ecommerce.clientui.proxies.MicroserviceAdventureProxy;
import com.ecommerce.clientui.proxies.MicroserviceLoginProxy;
import com.ecommerce.clientui.responseDTO.ClientResponseDTO;
import com.ecommerce.clientui.service.impl.ClientServiceImpl;
import com.ecommerce.clientui.utils.DebugUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static com.ecommerce.clientui.constants.SecurityConstants.AUTHORIZATION_HEADER;
import static com.ecommerce.clientui.constants.SecurityConstants.REFERER_HEADER;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ClientController {

    @Autowired
    MicroserviceAdventureProxy microserviceAdventureProxy;

    @Autowired
    MicroserviceLoginProxy microserviceLoginProxy;

    @Autowired
    ClientServiceImpl clientService;

    //@RequestMapping("/")
    /*public String home(Model model) {
        return "trip-index";
    }*/
    @RequestMapping("/")
    public String home(Model model) {
        return "index";
    }

    @RequestMapping("/about-us")
    public String aboutUs(Model model, HttpServletRequest request) {
        List<AdventureBean> adventures = microserviceAdventureProxy.adventureList(request.getHeader(AUTHORIZATION_HEADER));
        model.addAttribute("adventures", adventures);
        return "about-us";
    }

    @RequestMapping("/pricing")
    public String pricing(Model model) {
        return "login";
    }

    @RequestMapping("/adventures")
    public String adventures(Model model, HttpServletRequest request) throws UnauthorisedException {
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
        AdventureBean adventure = microserviceAdventureProxy.displayAdventure(id, request.getHeader(AUTHORIZATION_HEADER));
        model.addAttribute("adventure", adventure);
        return "adventures/detail";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = POST)
    public ModelAndView submit(@Valid @ModelAttribute("login") ClientBean clientBean, BindingResult result, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws UnauthorisedException {
        try {
            String token = microserviceLoginProxy.postLogin(clientBean, request.getHeader(REFERER_HEADER));
            Cookie cookie = new Cookie("jwt_token", token);
            cookie.setMaxAge(60 * 60); // expires in 1 hour
            // cookie.setSecure(true); https only
            cookie.setHttpOnly(true); // JS not able to read it
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (UnauthorisedException unauthorisedException) {
            model.addAttribute("error", "Wrong Username or password");
            return new ModelAndView("login", model);
        }
        return new ModelAndView("redirect:/account", model);
    }

    /*
    @RequestMapping("/account")
    public ModelAndView account(ModelMap model, HttpServletRequest request) throws UnauthorisedException  {
        try {
            ClientBean clientBean = microserviceClientProxy.searchClient(re, request.getHeader(REFERER_HEADER));
        } catch (UnauthorisedException unauthorisedException) {
            model.addAttribute("error", "Wrong Username or password");
            return new ModelAndView("login", model);
        }
        return "account/index";
    } */

    @RequestMapping("/account")
    public ModelAndView account(ModelMap model, HttpServletRequest request) throws UnauthorisedException  {
        try {
            System.out.println("dfsdfsdfdpooer" + request.getCookies());
            DebugUtils.RequestInfo.displayAllRequestHeaders(request);
            //Optional<ClientResponseDTO> clientResponseDTO = clientService.getUserInformations(DebugUtils.RequestInfo.getRequestHeader(request, "Authorisation"));
            //System.out.println("dto response + " + clientResponseDTO);
        } catch (UnauthorisedException unauthorisedException) {
            model.addAttribute("error", "Wrong username or password");
            return new ModelAndView("login", model);
        }
        return new ModelAndView("account/index", model);
    }
}
