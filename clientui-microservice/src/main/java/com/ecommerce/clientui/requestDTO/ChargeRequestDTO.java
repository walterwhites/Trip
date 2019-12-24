package com.ecommerce.clientui.requestDTO;

import com.ecommerce.clientui.beans.ChargeRequest;
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
