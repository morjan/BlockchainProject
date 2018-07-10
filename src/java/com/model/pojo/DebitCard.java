package com.model.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that map the entity DebitCard in the database
 * @author morjan
 */
@Entity
@Table(name = "DebitCard", catalog = "MA9627S")
public class DebitCard implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_debitCard", unique = true/*, nullable = false*/)
    private int idDebitCard;
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "name_onCard")
    private String nameOnCard;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "date_expire")
    private String dateExpire;
    @Column(name = "cvc")
    private String cvc;
    
    
    public DebitCard() {}

    public DebitCard(int idUser, String nameOnCard, String cardNumber, String dateExpire, String cvc) {
        this.idUser = idUser;
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.dateExpire = dateExpire;
        this.cvc = cvc;
    }

    public DebitCard(int idDebitCard, int idUser, String nameOnCard, String cardNumber, String dateExpire, String cvc) {
        this.idDebitCard = idDebitCard;
        this.idUser = idUser;
        this.nameOnCard = nameOnCard;
        this.cardNumber = cardNumber;
        this.dateExpire = dateExpire;
        this.cvc = cvc;
    }
    
    
    public int getIdDebitCard() {
        return idDebitCard;
    }

    public void setIdDebitCard(int idDebitCard) {
        this.idDebitCard = idDebitCard;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(String dateExpire) {
        this.dateExpire = dateExpire;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }    

    @Override
    public String toString() {
        return "DebitCard{" + "idDebitCard=" + idDebitCard + ", idUser=" + idUser + ", nameOnCard=" + nameOnCard + ", cardNumber=" + cardNumber + ", dateExpire=" + dateExpire + ", cvc=" + cvc + '}';
    }    
}
