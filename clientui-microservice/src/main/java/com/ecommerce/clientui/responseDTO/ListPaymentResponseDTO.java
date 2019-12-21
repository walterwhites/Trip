package com.ecommerce.clientui.responseDTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListPaymentResponseDTO {

    private List<PaymentResponseDTO> paymentResponseDTOList;
}
