package com.model.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class that map the entity UserApplication in the database
 * @author morjan
 */
@Entity
@Table(name = "UserApplication", catalog = "MA9627S")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_user", unique = true/*, nullable = false*/)
    private int idUser;
    @Column(name = "id_login")
    private int idLogin;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name="currency", nullable=false)
    private Currency currency;
    @Column(name = "credit")
    private double credit;

    
    public User() {}

    public User(String firstName, String lastName, Date birthDate, String phoneNumber, Currency currency, double credit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.currency = currency;
        this.credit = credit;
    }   
    
    public User(int idLogin, String firstName, String lastName, Date birthDate, String phoneNumber, Currency currency, double credit) {
        this.idLogin = idLogin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.currency = currency;
        this.credit = credit;
    }

    public User(int idUser, int idLogin, String firstName, String lastName, Date birthDate, String phoneNumber, Currency currency, double credit) {
        this.idUser = idUser;
        this.idLogin = idLogin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.currency = currency;
        this.credit = credit;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", idLogin=" + idLogin + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + ", phoneNumber=" + phoneNumber + ", currency=" + currency + ", credit=" + credit + '}';
    }    
}
