package com.ecommerce.clientui.requestDTO;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentAddRequestDTO implements Serializable {

    private int adventureId;
    private String content;
}
