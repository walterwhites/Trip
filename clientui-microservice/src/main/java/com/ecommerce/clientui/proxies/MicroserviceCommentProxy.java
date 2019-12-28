package com.ecommerce.clientui.proxies;

import com.ecommerce.clientui.requestDTO.*;
import com.ecommerce.clientui.responseDTO.ChargeResponseDTO;
import com.ecommerce.clientui.responseDTO.PaymentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

import static com.ecommerce.clientui.constants.SecurityConstants.AUTHORIZATION_HEADER;
import static com.ecommerce.clientui.constants.SecurityConstants.REFERER_HEADER;

@FeignClient(name = "comment-microservice")
@Service
public interface MicroserviceCommentProxy {

    @PostMapping(value = "/comments")
    List<CommentResponseDTO> getComments(@RequestBody CommentsRequestDTO commentsRequestDTO, @RequestHeader(value = REFERER_HEADER) String referer, @RequestHeader(value = AUTHORIZATION_HEADER) String authorisation);
}