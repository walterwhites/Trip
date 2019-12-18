package com.ecommerce.clientui.beans;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class PaymentBean {

    private int id;

    private String commandId;

    private Integer amount;

    private LocalDateTime paymentDate;

    private String state;

    private int clientId;

    private int adventureId;

    public PaymentBean() {
    }

    public PaymentBean(int id, String commandId, Integer amount, int clientId, int adventureId, String state, LocalDateTime paymentDate) {
        this.id = id;
        this.commandId = commandId;
        this.amount = amount;
        this.clientId = clientId;
        this.adventureId = adventureId;
        this.state = state;
        this.paymentDate = paymentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAdventureId() {
        return adventureId;
    }

    public void setAdventureId(int adventureId) {
        this.adventureId = adventureId;
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
                ", adventureId=" + adventureId +
                '}';
    }
}
