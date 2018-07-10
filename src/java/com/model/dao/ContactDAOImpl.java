package com.model.dao;

import com.model.pojo.Contact;
import com.model.pojo.User;
import com.model.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lovet
 */
@Repository
public class ContactDAOImpl implements ContactDAO {
    private static Session session;
    private static Transaction transaction;
    
    
    @Override
    public List<Contact> getContactByUsers(User userList, User userRecipient) {
        List<Contact> contactList = null;
        String hqlQuery = "SELECT c FROM Contact c WHERE c.userList = :userList AND c.userRecipient = :userRecipient";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            contactList = session.createQuery(hqlQuery)
                    .setEntity("userList", userList)
                    .setEntity("userRecipient", userRecipient)
                    .list();
            
            session.close();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        
        return contactList;
    }
    
    @Override
    public List<?> getContactsListWithEmail(User userList) {
        List<?> contactsLstWithEmail = null;
        String hqlQuery = "Select c.userRecipient, l.email FROM Contact c, UserLogin l WHERE c.userList = :userList AND c.userRecipient.idLogin = l.idLogin";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            contactsLstWithEmail = session.createQuery(hqlQuery)
                    .setEntity("userList", userList)
                    .list();
            
            session.close();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        
        return contactsLstWithEmail;
    }
    
    @Override
    public List<User> getContactsList(User userContactsList) {
        List<User> userList = null;
        String hqlQuery = "SELECT c.userRecipient FROM Contact c WHERE c.userList = :userContactsList ORDER BY c.userRecipient.firstName";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            userList = session.createQuery(hqlQuery)
                    .setEntity("userContactsList", userContactsList)
                    .list();
            
            session.close();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        
        return userList;
    }

    @Override
    public void addContact(Contact newContact) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            session.save(newContact);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteContact(Contact deleteContact) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.delete(deleteContact);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e.getMessage());
        }
    }
}