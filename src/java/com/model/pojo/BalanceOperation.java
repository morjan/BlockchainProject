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
 * Class that map the entity BalanceOperation in the database
 * @author lovet
 */
@Entity
@Table(name = "BalanceOperations", catalog = "MA9627S")
public class BalanceOperation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_balanceOperation", unique = true/*, nullable = false*/)
    private int idBalanceOperation;
    @Column(name= "id_user")
    private int idUser;
    @ManyToOne
    @JoinColumn(name="id_typeOperation")
    private TypeOperation typeOperation;
    @ManyToOne
    @JoinColumn(name="id_currencyOperation")
    private Currency currencyOperation;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name= "date_balanceOperation")
    private Date dateOperation;
    @Column(name= "amount_balanceOperation")
    private double amountOperation;

    
    public BalanceOperation() {
    }

    public BalanceOperation(int idUser, TypeOperation typeOperation, Currency currencyOperation, Date dateOperation, double amountOperation) {
        this.idUser = idUser;
        this.typeOperation = typeOperation;
        this.currencyOperation = currencyOperation;
        this.dateOperation = dateOperation;
        this.amountOperation = amountOperation;
    }

    public BalanceOperation(int idBalanceOperation, int idUser, TypeOperation typeOperation, Currency currencyOperation, Date dateOperation, double amountOperation) {
        this.idBalanceOperation = idBalanceOperation;
        this.idUser = idUser;
        this.typeOperation = typeOperation;
        this.currencyOperation = currencyOperation;
        this.dateOperation = dateOperation;
        this.amountOperation = amountOperation;
    }   

    
    public int getIdBalanceOperation() {
        return idBalanceOperation;
    }

    public void setIdBalanceOperation(int idBalanceOperation) {
        this.idBalanceOperation = idBalanceOperation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public Currency getCurrencyOperation() {
        return currencyOperation;
    }

    public void setCurrencyOperation(Currency currencyOperation) {
        this.currencyOperation = currencyOperation;
    }    

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public double getAmountOperation() {
        return amountOperation;
    }

    public void setAmountOperation(double amountOperation) {
        this.amountOperation = amountOperation;
    }

    @Override
    public String toString() {
        return "BalanceOperation{" + "idBalanceOperation=" + idBalanceOperation + ", idUser=" + idUser + ", typeOperation=" + typeOperation.toString() + ", dateOperation=" + dateOperation + ", amountOperation=" + amountOperation + '}';
    }   
}
