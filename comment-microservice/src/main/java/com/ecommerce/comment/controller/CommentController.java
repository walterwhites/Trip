package com.ecommerce.comment.controller;

import com.ecommerce.comment.requestDTO.CommentsRequestDTO;
import com.ecommerce.comment.responseDTO.CommentResponseDTO;
import com.ecommerce.comment.service.impl.ClientServiceImpl;
import com.ecommerce.comment.service.impl.CommentServiceImpl;
import com.ecommerce.comment.utils.CookiesUtils;
import com.ecommerce.comment.utils.DebugUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping()
@Api(value = "This is comment controller")
public class CommentController {

    private final CommentServiceImpl commentService;
    private final ClientServiceImpl clientService;

    public CommentController(CommentServiceImpl commentService, ClientServiceImpl clientService) {
        this.commentService = commentService;
        this.clientService = clientService;
    }

    @PostMapping(value="comments")
    @ApiOperation(value = "Get all comments of adventure")
    @ResponseBody
    ResponseEntity<?> getComments(@RequestBody CommentsRequestDTO commentsRequestDTO, HttpServletRequest request) {
        try {
            clientService.getUserInformations();
            List<CommentResponseDTO> commentResponseDTOS = commentService.getComments(commentsRequestDTO.getAdventureId());
            return ok().body(commentResponseDTOS);
        } catch (Exception exception) {
            return badRequest().body(exception.getMessage());
        }
    }
}