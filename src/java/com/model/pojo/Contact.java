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
 * Class that map the entity Contact in the database
 * @author lovet
 */
@Entity
@Table(name = "ContactList", catalog = "MA9627S")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_contact", unique = true)
    private int idContact;
    @ManyToOne
    @JoinColumn(name="id_userList", nullable=false)
    private User userList;
    @ManyToOne
    @JoinColumn(name="id_recipient", nullable=false)
    private User userRecipient;
    

    public Contact() {
    }

    public Contact(User userList, User userRecipient) {
        this.userList = userList;
        this.userRecipient = userRecipient;
    }

    public Contact(int idContact, User userList, User userRecipient) {
        this.idContact = idContact;
        this.userList = userList;
        this.userRecipient = userRecipient;
    }    
    

    public int getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
    }

    public User getUserList() {
        return userList;
    }

    public void setUserList(User userList) {
        this.userList = userList;
    }

    public User getUserRecipient() {
        return userRecipient;
    }

    public void setUserRecipient(User userRecipient) {
        this.userRecipient = userRecipient;
    }   
}
