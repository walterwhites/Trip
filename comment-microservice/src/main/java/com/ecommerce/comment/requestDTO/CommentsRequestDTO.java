package com.ecommerce.comment.requestDTO;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentsRequestDTO implements Serializable {

    private int adventureId;
}
