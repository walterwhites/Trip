package com.ecommerce.adventure.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {

    private HttpStatus status;
    private String errorMsg;

    @JsonIgnore
    private String developerMsg;
}