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
    private Integer idCommand;

    private Integer amount;

    private Long cardNumber;

    private Date paymentDate;

    private String state;

    public Payment() {
    }

    public Payment(int id, Integer idCommand, Integer amount, Long cardNumber) {
        this.id = id;
        this.idCommand = idCommand;
        this.amount = amount;
        this.cardNumber = cardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdCommand() {
        return idCommand;
    }

    public void setIdCommand(Integer idCommand) {
        this.idCommand = idCommand;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
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

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", idCommand=" + idCommand +
                ", amount=" + amount +
                ", cardNumber=" + cardNumber +
                ", paymentDate=" + paymentDate +
                ", state='" + state + '\'' +
                '}';
    }
}
