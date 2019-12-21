package com.ecommerce.payment.responseDTO;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO implements Serializable {

    private Long id;
    private String adventure;
    private int amount;
    private Long clientId;
    private String commandId;
    private LocalDateTime paymentDate;
    private String state;
}