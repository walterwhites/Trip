package com.ecommerce.payment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "payment")
@Entity
@Getter
@Setter
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, name = "commandId")
    private String commandId;

    @Column(unique = true, name = "chargeId")
    private String chargeId;

    @Column(name="amount")
    private Integer amount;

    @Column(name="paymentDate")
    private LocalDateTime paymentDate;

    @Column(name="state")
    private String state;

    @Column(name="client")
    private Long clientId;

    @Column(name="adventure")
    private String adventure;

    public Payment() {
    }

    public Payment(int id, String commandId, Integer amount, Long clientId, String adventure) {
        this.id = id;
        this.commandId = commandId;
        this.amount = amount;
        this.clientId = clientId;
        this.adventure = adventure;
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
