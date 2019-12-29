package com.ecommerce.comment.repositories;

import com.ecommerce.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>, CommentRepositoryCustom {

    @Query(value = "SELECT c FROM Comment c WHERE c.id = :id")
    Optional<Comment> getCommentById(@Param("id") int id);

    @Query(value = "SELECT c FROM Comment c WHERE c.adventureId = :adventureId")
    List<Comment> fetchAllCommentsByAdventureId(@Param("adventureId") int adventureId);

}