package com.ecommerce.comment.service.impl;

import com.ecommerce.comment.model.Comment;
import com.ecommerce.comment.repositories.CommentRepository;
import com.ecommerce.comment.responseDTO.CommentResponseDTO;
import com.ecommerce.comment.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<CommentResponseDTO> getComments(int adventureId) {
        List<Comment> comments = commentRepository.fetchAllCommentsByAdventureId(adventureId);
        ModelMapper modelMapper = new ModelMapper();
        List<CommentResponseDTO> commentResponseDTOS = Arrays.asList(modelMapper.map(comments, CommentResponseDTO[].class));

        return commentResponseDTOS;
    }

    @Override
    public void deleteComment(int id) {
        Comment commentToDelete = commentRepository.findById(id).get();
        commentRepository.delete(commentToDelete);
    }

    @Override
    public void editComment(int id, String content) {
        Comment commentToEdit = commentRepository.findById(id).get();
        commentToEdit.setContent(content);
        commentToEdit.setUpdatedDate(LocalDateTime.now());
        commentRepository.save(commentToEdit);
    }
}
