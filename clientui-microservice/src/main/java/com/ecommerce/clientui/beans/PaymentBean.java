package com.ecommerce.clientui.beans;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class PaymentBean {

    private Long id;
    private String adventure;
    private int amount;
    private Long clientId;
    private String commandId;
    private LocalDateTime paymentDate;
    private String state;

    public PaymentBean() {
    }

    public PaymentBean(Long id, String commandId, Integer amount, Long clientId, String adventure, String state, LocalDateTime paymentDate) {
        this.id = id;
        this.commandId = commandId;
        this.amount = amount;
        this.clientId = clientId;
        this.adventure = adventure;
        this.state = state;
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", commandId=" + commandId +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", state='" + state + '\'' +
                ", clientId=" + clientId +
                ", adventure=" + adventure +
                '}';
    }
}
