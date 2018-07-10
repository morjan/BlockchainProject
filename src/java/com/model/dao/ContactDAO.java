package com.model.dao;

import com.model.pojo.Contact;
import com.model.pojo.User;
import java.util.List;

/**
 * DAO class to query the pojo object entity mapping class Contact
 * @author lovet
 */
public interface ContactDAO {
    
    /**
     * get the contact object between two users
     * @param userList
     * @param userRecipient
     * @return 
     */
    public List<Contact> getContactByUsers(User userList, User userRecipient);
    
    /**
     * get the contact list with their email associated 
     * @param userList
     * @return 
     */
    public List<?> getContactsListWithEmail(User userList);
    
    /**
     * get the contact list
     * @param userContactsList
     * @return 
     */
    public List<User> getContactsList(User userContactsList);
    
    /** 
     * add a contact to the contact list of a user
     * @param newContact 
     */
    public void addContact(Contact newContact);
    
    /**
     * delete a contact to the contact list of a user
     * @param deleteContact 
     */
    public void deleteContact(Contact deleteContact);
}
