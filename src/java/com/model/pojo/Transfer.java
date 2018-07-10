package com.model.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class that map the entity Transfer in the database
 * @author lovet
 */
@Entity
@Table(name = "Transfer", catalog = "MA9627S")
public class Transfer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transfer")
    private int idTransfer;
    @ManyToOne
    @JoinColumn(name = "user_sender")
    private User userSender;
    @ManyToOne
    @JoinColumn(name = "user_recipient")
    private User userRecipient;
    @ManyToOne
    @JoinColumn(name = "currency_sender")
    private Currency currencySender;
    @ManyToOne
    @JoinColumn(name = "currency_recipient")
    private Currency currencyRecipient;
    @Column(name = "rate")
    private double rateTansfer;
    @Column(name = "amount_sender")
    private double amountSender;
    @Column(name = "amount_recipient")
    private double amountRecipient;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_transfer")
    private Date dateTransfer;

    
    public Transfer() {
    }
    
    public Transfer(User userSender, User userRecipient, Currency currencySender, Currency currencyRecipient, double rateTansfer, double amountSender, double amountRecipient, Date dateTransfer) {
        this.userSender = userSender;
        this.userRecipient = userRecipient;
        this.currencySender = currencySender;
        this.currencyRecipient = currencyRecipient;
        this.rateTansfer = rateTansfer;
        this.amountSender = amountSender;
        this.amountRecipient = amountRecipient;
        this.dateTransfer = dateTransfer;
    }

    public Transfer(int idTransfer, User userSender, User userRecipient, Currency currencySender, Currency currencyRecipient, double rateTansfer, double amountSender, double amountRecipient, Date dateTransfer) {
        this.idTransfer = idTransfer;
        this.userSender = userSender;
        this.userRecipient = userRecipient;
        this.currencySender = currencySender;
        this.currencyRecipient = currencyRecipient;
        this.rateTansfer = rateTansfer;
        this.amountSender = amountSender;
        this.amountRecipient = amountRecipient;
        this.dateTransfer = dateTransfer;
    }
    

    public int getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(int idTransfer) {
        this.idTransfer = idTransfer;
    }

    public User getUserSender() {
        return userSender;
    }

    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }

    public User getUserRecipient() {
        return userRecipient;
    }

    public void setUserRecipient(User userRecipient) {
        this.userRecipient = userRecipient;
    }

    public Currency getCurrencySender() {
        return currencySender;
    }

    public void setCurrencySender(Currency currencySender) {
        this.currencySender = currencySender;
    }

    public Currency getCurrencyRecipient() {
        return currencyRecipient;
    }

    public void setCurrencyRecipient(Currency currencyRecipient) {
        this.currencyRecipient = currencyRecipient;
    }

    public double getRateTansfer() {
        return rateTansfer;
    }

    public void setRateTansfer(double rateTansfer) {
        this.rateTansfer = rateTansfer;
    }

    public double getAmountSender() {
        return amountSender;
    }

    public void setAmountSender(double amountSender) {
        this.amountSender = amountSender;
    }

    public double getAmountRecipient() {
        return amountRecipient;
    }

    public void setAmountRecipient(double amountRecipient) {
        this.amountRecipient = amountRecipient;
    }

    public Date getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(Date dateTransfer) {
        this.dateTransfer = dateTransfer;
    }

    @Override
    public String toString() {
        return "Transfer{" + "idTransfer=" + idTransfer + ", userSender=" + userSender + ", userRecipient=" + userRecipient + ", currencySender=" + currencySender + ", currencyRecipient=" + currencyRecipient + ", rateTansfer=" + rateTansfer + ", amountSender=" + amountSender + ", amountRecipient=" + amountRecipient + ", dateTransfer=" + dateTransfer + '}';
    }   
}
