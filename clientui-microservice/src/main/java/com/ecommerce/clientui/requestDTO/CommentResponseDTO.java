package com.ecommerce.clientui.requestDTO;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO implements Serializable {

    private int id;
    private int adventureId;
    private LocalDateTime updatedDate;
    private Long clientId;
    private String content;
}