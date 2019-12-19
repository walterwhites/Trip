package com.ecommerce.clientui.controller;

import brave.http.HttpServerRequest;
import com.ecommerce.clientui.beans.ChargeRequest;
import com.ecommerce.clientui.service.impl.StripeService;
import com.ecommerce.clientui.utils.CookiesUtils;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChargeController {

    @Autowired
    private StripeService paymentsService;

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

    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model, @RequestParam String email, @RequestParam String name, @RequestParam String adventure) throws StripeException {

        chargeRequest.setDescription("Book adventure: " + adventure);
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Customer customer = paymentsService.createCustomer(email, name);
        Card card = paymentsService.createAcard(customer, chargeRequest.getStripeToken());
        Charge charge = paymentsService.charge(chargeRequest, customer.getId(), card);

        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());

        return "commands/result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "commands/result";
    }
}