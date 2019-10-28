package com.ecommerce.clientui.controller;

import com.ecommerce.clientui.beans.AdventureBean;
import com.ecommerce.clientui.proxies.MicroserviceAdventureProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    MicroserviceAdventureProxy microserviceAdventureProxy;

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
        return "pricing";
    }

    @RequestMapping("/adventures")
    public String adventures(Model model) {
        List<AdventureBean> adventures = microserviceAdventureProxy.adventureList();
        model.addAttribute("adventures", adventures);
        return "adventures";
    }
}
