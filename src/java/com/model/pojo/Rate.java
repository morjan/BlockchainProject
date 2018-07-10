package com.model.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class that map the entity Rate in the database
 * @author morjan
 */
@Entity
@Table(name = "Rate", catalog = "MA9627S")
public class Rate implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_rate", unique = true/*, nullable = false*/)
    private int idRate;
    @ManyToOne
    @JoinColumn(name = "currency_from")
    private Currency currencyFrom;
    @ManyToOne
    @JoinColumn(name = "currency_to")
    private Currency currencyTo;
    @Column(name = "rate")
    private double rate;

    
    public Rate() {}

    public Rate(int idRate, Currency currencyFrom, Currency currencyTo, double rate) {
        this.idRate = idRate;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
    }
    

    public int getIdRate() {
        return idRate;
    }

    public void setIdRate(int idRate) {
        this.idRate = idRate;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rate{" + "idRate=" + idRate + ", currencyFrom=" + currencyFrom + ", currencyTo=" + currencyTo + ", rate=" + rate + '}';
    }   
}
