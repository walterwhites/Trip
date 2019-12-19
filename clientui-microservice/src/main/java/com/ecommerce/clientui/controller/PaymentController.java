package com.ecommerce.clientui.controller;

import com.ecommerce.clientui.beans.AdventureBean;
import com.ecommerce.clientui.beans.ChargeRequest;
import com.ecommerce.clientui.beans.PaymentBean;
import com.ecommerce.clientui.exception.UnauthorisedException;
import com.ecommerce.clientui.proxies.MicroserviceAdventureProxy;
import com.ecommerce.clientui.proxies.MicroserviceLoginProxy;
import com.ecommerce.clientui.responseDTO.ClientResponseDTO;
import com.ecommerce.clientui.service.impl.ClientServiceImpl;
import com.ecommerce.clientui.utils.CookiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.ecommerce.clientui.constants.SecurityConstants.AUTHORIZATION_HEADER;

@Controller
public class PaymentController {

    @Autowired
    MicroserviceAdventureProxy microserviceAdventureProxy;

    @Autowired
    MicroserviceLoginProxy microserviceLoginProxy;

    @Autowired
    ClientServiceImpl clientService;

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

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

    @RequestMapping("/adventures/{id}")
    public String adventures(Model model, @PathVariable("id") int id, HttpServletRequest request) {
        try {
            AdventureBean adventure = microserviceAdventureProxy.displayAdventure(id, request.getHeader(AUTHORIZATION_HEADER));
            model.addAttribute("adventure", adventure);
            model.addAttribute("amount", adventure.getPrice()); // in cents
            model.addAttribute("stripePublicKey", stripePublicKey);
            model.addAttribute("currency", ChargeRequest.Currency.EUR);
        } catch (UnauthorisedException unauthorisedException) {
            return "redirect:/login";
        }
        return "adventures/detail";
    }

    @RequestMapping("/commands/{id}")
    public ModelAndView commands(ModelMap model, @PathVariable("id") int id, HttpServletRequest request) {
        try {
            Optional<ClientResponseDTO> clientResponseDTO = clientService.getUserInformations();
            PaymentBean paymentBean = new PaymentBean(id, "23423482348284", 100, 1, 1, "in progress", LocalDateTime.now());
            model.addAttribute("payment", paymentBean);
        } catch (UnauthorisedException unauthorisedException) {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("commands/detail", model);
    }

    @RequestMapping("/commands")
    public ModelAndView commands(ModelMap model) {
        try {
            Optional<ClientResponseDTO> clientResponseDTO = clientService.getUserInformations();
            PaymentBean paymentBean = new PaymentBean(1, "dfsf", 100, 1, 1, "in progress", LocalDateTime.now());
            PaymentBean paymentBean2 = new PaymentBean(2, "dfsf", 100, 1, 1, "in progress", LocalDateTime.now());
            PaymentBean paymentBean3 = new PaymentBean(1, "dfsf", 100, 1, 1, "in progress", LocalDateTime.now());
            PaymentBean paymentBean4 = new PaymentBean(1, "kkkkkkkkkk", 100, 1, 1, "in progress", LocalDateTime.now());
            PaymentBean paymentBean5 = new PaymentBean(1, "dfsf", 100, 1, 1, "in progress", LocalDateTime.now());
            PaymentBean paymentBean6 = new PaymentBean(1, "dfsf", 100, 1, 1, "in progress", LocalDateTime.now());
            PaymentBean paymentBean7 = new PaymentBean(1, "dfsf", 100, 1, 1, "in progress", LocalDateTime.now());
            PaymentBean paymentBean8 = new PaymentBean(1, "dfsf", 100, 1, 1, "in progress", LocalDateTime.now());
            PaymentBean paymentBean9 = new PaymentBean(1, "dfsf", 100, 1, 1, "in progress", LocalDateTime.now());
            PaymentBean paymentBean10 = new PaymentBean(1, "dfsf", 100, 1, 1, "in progress", LocalDateTime.now());
            PaymentBean paymentBean11 = new PaymentBean(1, "kkkkkkkkkk", 100, 1, 1, "paid", LocalDateTime.now());

            List<PaymentBean> paymentBeans  = new LinkedList<>();
            paymentBeans.add(paymentBean);
            paymentBeans.add(paymentBean2);
            paymentBeans.add(paymentBean3);
            paymentBeans.add(paymentBean4);
            paymentBeans.add(paymentBean5);
            paymentBeans.add(paymentBean6);
            paymentBeans.add(paymentBean7);
            paymentBeans.add(paymentBean8);
            paymentBeans.add(paymentBean9);
            paymentBeans.add(paymentBean10);
            paymentBeans.add(paymentBean11);
            model.addAttribute("payments", paymentBeans);
        } catch (UnauthorisedException unauthorisedException) {
            return new ModelAndView("redirect:/login");
        }
        return new ModelAndView("commands/index", model);
    }
}
