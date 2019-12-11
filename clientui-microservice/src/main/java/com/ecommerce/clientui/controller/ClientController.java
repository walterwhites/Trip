package com.ecommerce.clientui.controller;

import com.ecommerce.clientui.beans.AdventureBean;
import com.ecommerce.clientui.beans.ClientBean;
import com.ecommerce.clientui.exception.UnauthorisedException;
import com.ecommerce.clientui.proxies.MicroserviceAdventureProxy;
import com.ecommerce.clientui.proxies.MicroserviceLoginProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static com.ecommerce.clientui.constants.SecurityConstants.REFERER_HEADER;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ClientController {

    @Autowired
    MicroserviceAdventureProxy microserviceAdventureProxy;

    @Autowired
    MicroserviceLoginProxy microserviceLoginProxy;

    //@RequestMapping("/")
    /*public String home(Model model) {
        return "trip-index";
    }*/
    @RequestMapping("/")
    public String home(Model model) {
        return "index";
    }

    @RequestMapping("/about-us")
    public String aboutUs(Model model) {
        List<AdventureBean> adventures = microserviceAdventureProxy.adventureList();
        model.addAttribute("adventures", adventures);
        return "about-us";
    }

    @RequestMapping("/pricing")
    public String pricing(Model model) {
        return "login";
    }

    @RequestMapping("/adventures")
    public String adventures(Model model) throws UnauthorisedException {
        try {
            List<AdventureBean> adventures = microserviceAdventureProxy.adventureList();
            model.addAttribute("adventures", adventures);
        } catch (UnauthorisedException unauthorisedException) {
            return "redirect:/login";
        }
        return "adventures/list";
    }

    @RequestMapping("/adventures/{id}")
    public String adventures(Model model, @PathVariable("id") int id) {
        AdventureBean adventure = microserviceAdventureProxy.displayAdventure(id);
        model.addAttribute("adventure", adventure);
        return "adventures/detail";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = POST)
    public ModelAndView submit(@Valid @ModelAttribute("login") ClientBean clientBean, BindingResult result, ModelMap model, HttpServletRequest request) throws UnauthorisedException {
        try {
            String token = microserviceLoginProxy.postLogin(clientBean, request.getHeader(REFERER_HEADER));
        } catch (UnauthorisedException unauthorisedException) {
            model.addAttribute("error", "Wrong Username or password");
            return new ModelAndView("login", model);
        }
        return new ModelAndView("redirect:/account", model);
    }

    @RequestMapping("/account")
    public String account(Model model) {
        return "account/index";
    }
}
