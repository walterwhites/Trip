package com.ecommerce.clientui.requestDTO;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentEditRequestDTO implements Serializable {

    private int id;
    private String content;
}
