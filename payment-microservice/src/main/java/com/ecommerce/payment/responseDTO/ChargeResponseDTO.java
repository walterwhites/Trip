package com.ecommerce.payment.responseDTO;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargeResponseDTO implements Serializable {

    private String id;
    private String status;
    private String chargeId;
    private String balance_transaction;
}
