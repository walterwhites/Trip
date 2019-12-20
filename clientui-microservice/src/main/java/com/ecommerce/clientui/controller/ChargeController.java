package com.ecommerce.clientui.controller;

import com.ecommerce.clientui.beans.ChargeRequest;
import com.ecommerce.clientui.exception.CustomException;
import com.ecommerce.clientui.exception.UnauthorisedException;
import com.ecommerce.clientui.proxies.MicroservicePaymentProxy;
import com.ecommerce.clientui.requestDTO.ChargeRequestDTO;
import com.ecommerce.clientui.responseDTO.ChargeResponseDTO;
import com.ecommerce.clientui.service.PaymentService;
import com.ecommerce.clientui.service.impl.StripeService;
import com.ecommerce.clientui.utils.CookiesUtils;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class ChargeController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    MicroservicePaymentProxy microservicePaymentProxy;

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
    public String charge(ChargeRequestDTO chargeRequestDTO, ChargeRequest chargeRequest, Model model, @RequestParam String email, @RequestParam String name, @RequestParam String adventure, HttpServletRequest request) throws StripeException {
        try {
            Optional<ChargeResponseDTO> chargeResponseDTO = paymentService.charge(chargeRequest, chargeRequestDTO, email, name, adventure, request);
            model.addAttribute("id", chargeResponseDTO.get().getId());
            model.addAttribute("status", chargeResponseDTO.get().getStatus());
            model.addAttribute("chargeId", chargeResponseDTO.get().getId());
            model.addAttribute("balance_transaction", chargeResponseDTO.get().getBalance_transaction());
        }
        catch (StripeException stripeException) {
            System.out.println("fdsfsdfsfs " + stripeException.getMessage());
        }
        catch (UnauthorisedException unauthorisedException) {
            return "redirect:/login";
        }
        return "commands/result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "commands/result";
    }
}