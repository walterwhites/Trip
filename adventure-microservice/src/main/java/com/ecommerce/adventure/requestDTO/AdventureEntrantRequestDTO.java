package com.ecommerce.adventure.requestDTO;

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
