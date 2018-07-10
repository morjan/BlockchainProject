package com.delegate;

import com.model.pojo.Contact;
import com.model.pojo.User;
import java.util.List;

/**
 *
 * @author lovet
 */
public interface ContactDelegate {
    
    public List<Contact> getContactByUsers(User userList, User userRecipient);
    
    public List<?> getContactsListWithEmail(User userContactsList);
    
    public List<User> getContactsList(User userContactsList);
    
    public void addContact(User userContactsList, User userAdd);
    
    public void deleteContact(Contact deleteContact);
    
    public boolean userInListContact(User user, int idUserContact);
}
