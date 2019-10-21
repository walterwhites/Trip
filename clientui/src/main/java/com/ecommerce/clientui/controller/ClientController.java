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
        List<AdventureBean> adventures = microserviceAdventureProxy.adventureList();
        model.addAttribute("adventures", adventures);
        return "index";
    }
}
