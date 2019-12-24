package com.ecommerce.payment.requestDTO;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetailRequestDTO implements Serializable {

    private int id;
    private Long clientId;
}
