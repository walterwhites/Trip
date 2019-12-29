package com.ecommerce.comment.requestDTO;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentAddRequestDTO implements Serializable {

    private int adventureId;
    private Long clientId;
    private String content;
}
