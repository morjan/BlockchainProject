package com.model.pojo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that map the entity Currency in the database
 * @author morjan
 */
@Entity
@Table(name = "Currency", catalog = "MA9627S")
public class Currency implements Serializable {
    private int idCurrency;
    private String currencyName;
    private String currencyCode;

    public Currency() {}

    public Currency(int idCurrency, String currencyName, String currencyCode) {
        this.idCurrency = idCurrency;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_currency", unique = true/*, nullable = false*/)
    public int getIdCurrency() {
        return idCurrency;
    }

    public void setIdCurrency(int idCurrency) {
        this.idCurrency = idCurrency;
    }

    @Column(name = "currency_name")
    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    @Column(name = "currency_code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "Currency{" + "idCurrency=" + idCurrency + ", currencyName=" + currencyName + ", currencyCode=" + currencyCode + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Currency other = (Currency) obj;
        if (this.idCurrency != other.idCurrency) {
            return false;
        }
        if (!Objects.equals(this.currencyName, other.currencyName)) {
            return false;
        }
        if (!Objects.equals(this.currencyCode, other.currencyCode)) {
            return false;
        }
        
        return true;
    }    
}
