package com.ecommerce.comment.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {
    protected ErrorResponse errorResponse;

    public CustomException(String message) {
        super(message);
    }
}
