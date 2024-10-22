package com.ecommerce.clientui.controller;

import com.ecommerce.clientui.beans.*;
import com.ecommerce.clientui.exception.BadRequestException;
import com.ecommerce.clientui.proxies.*;
import com.ecommerce.clientui.requestDTO.CommentResponseDTO;
import com.ecommerce.clientui.requestDTO.CommentsRequestDTO;
import com.ecommerce.clientui.requestDTO.PaymentDetailRequestDTO;
import com.ecommerce.clientui.requestDTO.RefundRequestDTO;
import com.ecommerce.clientui.responseDTO.ChargeResponseDTO;
import com.ecommerce.clientui.responseDTO.ClientResponseDTO;
import com.ecommerce.clientui.responseDTO.PaymentResponseDTO;
import com.ecommerce.clientui.service.impl.ClientServiceImpl;
import com.ecommerce.clientui.utils.CookiesUtils;
import com.ecommerce.clientui.utils.DebugUtils;
import com.stripe.exception.StripeException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.ecommerce.clientui.constants.SecurityConstants.AUTHORIZATION_HEADER;
import static com.ecommerce.clientui.constants.SecurityConstants.REFERER_HEADER;

@Controller
public class PaymentController {

    @Autowired
    MicroserviceAdventureProxy microserviceAdventureProxy;

    @Autowired
    MicroserviceLoginProxy microserviceLoginProxy;

    @Autowired
    MicroservicePaymentProxy microservicePaymentProxy;

    @Autowired
    MicroserviceCategoryProxy microserviceCategoryProxy;

    @Autowired
    MicroserviceCommentProxy microserviceCommentProxy;

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

        AdventureBean adventure = microserviceAdventureProxy.displayAdventure(id, request.getHeader(AUTHORIZATION_HEADER));
        Optional<ClientResponseDTO> clientResponseDTO = clientService.getUserInformations();

        CategoryBean categoryBean = microserviceCategoryProxy.getCategoryById(adventure.getCategory(), request.getHeader(AUTHORIZATION_HEADER));
        adventure.setCategoryName(categoryBean.getName());
        adventure.setCategoryColor(categoryBean.getColor());

        CommentsRequestDTO commentsRequestDTO = new CommentsRequestDTO();
        commentsRequestDTO.setAdventureId(adventure.getId());
        List<CommentResponseDTO> commentResponseDTO = microserviceCommentProxy.getComments(commentsRequestDTO, request.getHeader(REFERER_HEADER), request.getHeader(AUTHORIZATION_HEADER));
        ModelMapper modelMapper = new ModelMapper();
        List<CommentBean> commentBeanList = Arrays.asList(modelMapper.map(commentResponseDTO, CommentBean[].class));

        model.addAttribute("client", clientResponseDTO.get());
        model.addAttribute("adventure", adventure);
        model.addAttribute("amount", adventure.getPrice() * 100); // Stripe payment in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        model.addAttribute("comments", commentBeanList);
        return "adventures/detail";
    }

    @RequestMapping("/commands/{id}")
    public ModelAndView commandDetail(ModelMap model, HttpServletRequest request, @PathVariable("id") int id) {

        Optional<ClientResponseDTO> clientResponseDTO = clientService.getUserInformations();
        PaymentDetailRequestDTO paymentDetailRequestDTO = new PaymentDetailRequestDTO();
        paymentDetailRequestDTO.setClientId(clientResponseDTO.get().getId());
        paymentDetailRequestDTO.setId(id);
        Optional<PaymentResponseDTO> payment  = microservicePaymentProxy.paymentDetail(paymentDetailRequestDTO, request.getHeader(REFERER_HEADER), request.getHeader(AUTHORIZATION_HEADER));
        ModelMapper modelMapper = new ModelMapper();
        PaymentBean paymentBean = modelMapper.map(payment.get(), PaymentBean.class);
        model.addAttribute("payment", paymentBean);
        return new ModelAndView("commands/detail", model);
    }

    @RequestMapping("/commands")
    public ModelAndView commands(ModelMap model, HttpServletRequest request) {
        DebugUtils.RequestInfo.displayAllRequestHeaders(request);
        Optional<ClientResponseDTO> clientResponseDTO = clientService.getUserInformations();
        DebugUtils.RequestInfo.displayAllRequestHeaders(request);
        List<PaymentResponseDTO> payments  = microservicePaymentProxy.payments(clientResponseDTO.get().getId(), request.getHeader(REFERER_HEADER), request.getHeader(AUTHORIZATION_HEADER));
        ModelMapper modelMapper = new ModelMapper();
        List<PaymentBean> paymentBeans  = Arrays.asList(modelMapper.map(payments, PaymentBean[].class));

        model.addAttribute("payments", paymentBeans);
        return new ModelAndView("commands/index", model);
    }

    @PostMapping("/commands/{id}/refund")
    public ModelAndView commandRefund(ModelMap model, HttpServletRequest request, @RequestParam String chargeId, @RequestParam String adventure, @PathVariable("id") int id) {

        try {
            ClientResponseDTO clientResponseDTO = clientService.getUserInformations().get();
            RefundRequestDTO refundRequestDTO = new RefundRequestDTO();
            refundRequestDTO.setClientId(clientResponseDTO.getId());
            refundRequestDTO.setChargeId(chargeId);
            refundRequestDTO.setAdventure(adventure);
            microservicePaymentProxy.refundCommand(refundRequestDTO, request.getHeader(REFERER_HEADER), request.getHeader(AUTHORIZATION_HEADER));
            model.addAttribute("success", "Payment has been refunded ");
        } catch (BadRequestException badRequestException) {
            model.addAttribute("error", "Error processing the refund " + badRequestException.getMessage());
        }

        return new ModelAndView("forward:/commands/" + id, model);
    }
}
