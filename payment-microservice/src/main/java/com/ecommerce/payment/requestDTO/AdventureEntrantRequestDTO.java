package com.ecommerce.payment.requestDTO;

import com.ecommerce.payment.beans.ChargeRequest;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdventureEntrantRequestDTO implements Serializable {

    private String chargeId;
    private String adventure;
}
