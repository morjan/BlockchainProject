package com.model.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that map the entity BankDetails in the database
 * @author morjan
 */
@Entity
@Table(name = "BankDetails", catalog = "MA9627S")
public class BankDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_bankDetails", unique = true/*, nullable = false*/)
    private int idBankDetails;
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "name_account")
    private String accountName;
    @Column(name = "sort_code")
    private String sortCode;
    @Column(name = "account_number")
    private String accountNumber;
    
    
    public BankDetails() {}

    public BankDetails(int idUser, String bankName, String accountName, String sortCode, String accountNumber) {
        this.idUser = idUser;
        this.bankName = bankName;
        this.accountName = accountName;
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
    }

    public BankDetails(int idBankDetails, int idUser, String bankName, String accountName, String sortCode, String accountNumber) {
        this.idBankDetails = idBankDetails;
        this.idUser = idUser;
        this.bankName = bankName;
        this.accountName = accountName;
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
    }
            
        
    public int getIdBankDetails() {
        return idBankDetails;
    }

    public void setIdBankDetails(int idBankDetails) {
        this.idBankDetails = idBankDetails;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "BankDetails{" + "idBankDetails=" + idBankDetails + ", idUser=" + idUser + ", bankName=" + bankName + ", accountName=" + accountName + ", sortCode=" + sortCode + ", accountNumber=" + accountNumber + '}';
    }   
}
