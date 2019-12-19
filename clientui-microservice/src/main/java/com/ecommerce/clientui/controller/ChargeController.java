package com.ecommerce.clientui.controller;

import com.ecommerce.clientui.beans.ChargeRequest;
import com.ecommerce.clientui.service.impl.StripeService;
import com.ecommerce.clientui.utils.CookiesUtils;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String charge(ChargeRequest chargeRequest, Model model) throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentsService.charge(chargeRequest);
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