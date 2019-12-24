package com.ecommerce.clientui.requestDTO;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefundRequestDTO implements Serializable {

    private String chargeId;
    private Long clientId;
    private String adventure;
}
