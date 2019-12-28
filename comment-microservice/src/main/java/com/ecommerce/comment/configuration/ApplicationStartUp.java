package com.ecommerce.comment.configuration;

import com.ecommerce.comment.model.Comment;
import com.ecommerce.comment.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Component
@EnableConfigurationProperties(StartupProperties.class)
public class ApplicationStartUp {

    @Autowired
    private StartupProperties startupProperties;

    @Bean
    public CommandLineRunner loadData(CommentRepository commentRepository) {
        return (args) -> {
            List<Comment> comments = commentRepository.findAll();

            if (ObjectUtils.isEmpty(comments)) {
                commentRepository.save(saveComment());
            }
        };
    }

    public Comment saveComment() {
        Comment comment = new Comment();
        comment.setClientId(startupProperties.getClientId());
        comment.setAdventureId(startupProperties.getAdventureId());
        comment.setContent(startupProperties.getContent());
        comment.setUpdatedDate(LocalDateTime.now());

        return comment;
    }
}
