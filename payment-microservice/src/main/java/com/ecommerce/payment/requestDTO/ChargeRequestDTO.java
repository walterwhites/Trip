package com.ecommerce.payment.requestDTO;

import com.ecommerce.payment.beans.ChargeRequest;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargeRequestDTO implements Serializable {

    private ChargeRequest chargeRequest;
    private String email;
    private String name;
    private String adventure;
}
