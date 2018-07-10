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
 * Class that map the entity CurrencyChange in the database
 * @author lovet
 */
@Entity
@Table(name = "CurrencyChange", catalog = "MA9627S")
public class CurrencyChange implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_currencyChange")
    private int idCurrencyChange;
    @Column(name = "id_user")
    private int idUser;
    @ManyToOne
    @JoinColumn(name = "currency_from")
    private Currency currencyFrom;
    @ManyToOne
    @JoinColumn(name = "currency_to")
    private Currency currencyTo;
    @Column(name = "credit_from")
    private double creditFrom;
    @Column(name = "credit_to")
    private double creditTo;
//    @Column(name = "rate")
//    private double rate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_change")
    private Date dateChange;

    
    public CurrencyChange() {
    }

    public CurrencyChange(int idUser, Currency currencyFrom, Currency currencyTo, double creditFrom, double creditTo, Date dateChange) {
        this.idUser = idUser;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.creditFrom = creditFrom;
        this.creditTo = creditTo;
        this.dateChange = dateChange;
    }

    public CurrencyChange(int idCurrencyChange, int idUser, Currency currencyFrom, Currency currencyTo, double creditFrom, double creditTo, Date dateChange) {
        this.idCurrencyChange = idCurrencyChange;
        this.idUser = idUser;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.creditFrom = creditFrom;
        this.creditTo = creditTo;
        this.dateChange = dateChange;
    }

    
    public int getIdCurrencyChange() {
        return idCurrencyChange;
    }

    public void setIdCurrencyChange(int idCurrencyChange) {
        this.idCurrencyChange = idCurrencyChange;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Currency getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(Currency currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public Currency getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(Currency currencyTo) {
        this.currencyTo = currencyTo;
    }

    public double getCreditFrom() {
        return creditFrom;
    }

    public void setCreditFrom(double creditFrom) {
        this.creditFrom = creditFrom;
    }

    public double getCreditTo() {
        return creditTo;
    }

    public void setCreditTo(double creditTo) {
        this.creditTo = creditTo;
    }

    public Date getDateChange() {
        return dateChange;
    }

    public void setDateChange(Date dateChange) {
        this.dateChange = dateChange;
    }

    @Override
    public String toString() {
        return "CurrencyChange{" + "idCurrencyChange=" + idCurrencyChange + ", idUser=" + idUser + ", currencyFrom=" + currencyFrom + ", currencyTo=" + currencyTo + ", creditFrom=" + creditFrom + ", creditTo=" + creditTo + ", dateChange=" + dateChange + '}';
    }  
}
