package com.delegate;

import com.model.dao.ContactDAO;
import com.model.dao.UserDAO;
import com.model.pojo.Contact;
import com.model.pojo.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lovet
 */
@Service
public class ContactDelegateImpl implements ContactDelegate {
    @Autowired
    ContactDAO contactDAO;
    @Autowired
    UserDAO userDAO;
    
    
    @Override
    public List<Contact> getContactByUsers(User userList, User userRecipient) {
        return contactDAO.getContactByUsers(userList, userRecipient);
    }
    
    @Override
    public List<?> getContactsListWithEmail(User userContactsList) {
        return contactDAO.getContactsListWithEmail(userContactsList);
    }
    
    @Override
    public List<User> getContactsList(User userContactsList) {
        return contactDAO.getContactsList(userContactsList);
    }

    @Override
    public void addContact(User userContactsList, User userAdd) {
        Contact newContact = new Contact(userContactsList, userAdd);
        
        contactDAO.addContact(newContact);
    }

    @Override
    public void deleteContact(Contact deleteContact) {
        contactDAO.deleteContact(deleteContact);
    }
    
    @Override
    public boolean userInListContact(User user, int idUserContact) {
        User userContact = userDAO.getUserById(idUserContact);
        
        return !contactDAO.getContactByUsers(user, userContact).isEmpty();
    }
}
