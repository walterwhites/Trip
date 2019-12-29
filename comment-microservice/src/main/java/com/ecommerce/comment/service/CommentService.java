package com.ecommerce.comment.service;

import com.ecommerce.comment.responseDTO.CommentResponseDTO;
import java.util.List;

public interface CommentService {

    List<CommentResponseDTO> getComments(int adventureId);
    void deleteComment(int id);
    void editComment(int id, String content);
}
