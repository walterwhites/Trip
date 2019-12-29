package com.ecommerce.comment.controller;

import com.ecommerce.comment.requestDTO.CommentAddRequestDTO;
import com.ecommerce.comment.requestDTO.CommentEditRequestDTO;
import com.ecommerce.comment.requestDTO.CommentsRequestDTO;
import com.ecommerce.comment.responseDTO.ClientResponseDTO;
import com.ecommerce.comment.responseDTO.CommentResponseDTO;
import com.ecommerce.comment.service.impl.ClientServiceImpl;
import com.ecommerce.comment.service.impl.CommentServiceImpl;
import com.ecommerce.comment.utils.CookiesUtils;
import com.ecommerce.comment.utils.DebugUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

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

    @PostMapping(value="comments/{id}/delete")
    @ApiOperation(value = "Delete a comment")
    @ResponseBody
    ResponseEntity<?> deleteComment(@RequestParam int id, HttpServletRequest request) {
        try {
            clientService.getUserInformations();
            commentService.deleteComment(id);
            return ok().build();
        } catch (Exception exception) {
            return badRequest().body(exception.getMessage());
        }
    }

    @PostMapping(value="comments/{id}/edit")
    @ApiOperation(value = "Edit a comment")
    @ResponseBody
    ResponseEntity<?> editComment(@RequestParam int id, @RequestBody CommentEditRequestDTO commentEditRequestDTO, HttpServletRequest request) {
        try {
            clientService.getUserInformations();
            commentService.editComment(commentEditRequestDTO.getId(), commentEditRequestDTO.getContent());
            return ok().build();
        } catch (Exception exception) {
            return badRequest().body(exception.getMessage());
        }
    }

    @PostMapping(value="comments/add")
    @ApiOperation(value = "Add a comment")
    @ResponseBody
    ResponseEntity<?> addComment(@RequestBody CommentAddRequestDTO commentAddRequestDTO, HttpServletRequest request) {
        try {
            ClientResponseDTO clientResponseDTO = clientService.getUserInformations().get();
            commentAddRequestDTO.setClientId(clientResponseDTO.getId());
            commentService.addComment(commentAddRequestDTO.getAdventureId(), commentAddRequestDTO.getClientId(), commentAddRequestDTO.getContent());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception exception) {
            return badRequest().body(exception.getMessage());
        }
    }
}