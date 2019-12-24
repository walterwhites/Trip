package com.ecommerce.clientui.responseDTO;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
