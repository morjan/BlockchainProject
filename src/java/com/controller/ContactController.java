package com.controller;

import com.delegate.ContactDelegate;
import com.delegate.UserDelegate;
import com.delegate.UserLoginDelegate;
import com.model.pojo.Contact;
import com.model.pojo.User;
import com.model.pojo.UserLogin;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author lovet
 */
@Controller
public class ContactController {
    @Autowired
    UserDelegate userDelegate;
    @Autowired
    UserLoginDelegate userLoginDelegate;
    @Autowired
    ContactDelegate contactDelegate;
    
    
    @RequestMapping(value = "contactsPage")
    public String contactsPage(Model model, HttpSession session) {
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            List<?> contactsList = contactDelegate.getContactsListWithEmail(userConnected);
            
            model.addAttribute("contactsList", contactsList);

            return "user_contacts";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
    
    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    public String addContact(Model model, HttpSession session, @ModelAttribute(value="emailContactUser") String emailContactUser) {
        
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            UserLogin userLoginConnected = userLoginDelegate.getLoginByIdLogin(userConnected.getIdLogin());
            
            if(!userLoginDelegate.getIdLoginByEmail(emailContactUser).isEmpty()) {
                if(!userLoginConnected.getEmail().equals(emailContactUser)) {
                    UserLogin userLoginAdd = userLoginDelegate.getIdLoginByEmail(emailContactUser).get(0);
                    User userAdd = userDelegate.getUserByIdLogin(userLoginAdd.getIdLogin());

                    if(contactDelegate.getContactByUsers(userConnected, userAdd).isEmpty()) {
                        contactDelegate.addContact(userConnected, userAdd);                    
                    } else {
                        model.addAttribute("errorMessage", "User already in your contacts list");
                    }
                } else {
                    model.addAttribute("errorMessage", "You can't add yourself in your contacts list");
                }
            } else {
                model.addAttribute("errorMessage", "User does not exist");
            }            
            
            model.addAttribute("contactsList", contactDelegate.getContactsListWithEmail(userConnected));
            
            return "user_contacts";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
    
    @RequestMapping(value = "/deleteContact", method = RequestMethod.POST)
    public String deleteContact(Model model, HttpSession session, @ModelAttribute(value="emailDeleteContact") String emailDeleteContact) {
        
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            
            if(!userLoginDelegate.getIdLoginByEmail(emailDeleteContact).isEmpty()) {
                UserLogin userLoginDelete = userLoginDelegate.getIdLoginByEmail(emailDeleteContact).get(0);
                User userDelete = userDelegate.getUserByIdLogin(userLoginDelete.getIdLogin());
                
                if(!contactDelegate.getContactByUsers(userConnected, userDelete).isEmpty()) {
                    Contact deleteContact = contactDelegate.getContactByUsers(userConnected, userDelete).get(0);
                    
                    contactDelegate.deleteContact(deleteContact);
                } else {
                    model.addAttribute("errorMessage", "User not in your contacts list");
                }
            } else {
                model.addAttribute("errorMessage", "User does not exist");
            }
            
            model.addAttribute("contactsList", contactDelegate.getContactsListWithEmail(userConnected));
            
            return "user_contacts";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
}