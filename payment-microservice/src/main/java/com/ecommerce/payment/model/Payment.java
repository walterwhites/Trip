package com.ecommerce.payment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String commandId;

    private Integer amount;

    private Date paymentDate;

    private String state;

    private int clientId;

    private int adventureId;

    public Payment() {
    }

    public Payment(int id, String commandId, Integer amount, int clientId, int adventureId) {
        this.id = id;
        this.commandId = commandId;
        this.amount = amount;
        this.clientId = clientId;
        this.adventureId = adventureId;
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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
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
