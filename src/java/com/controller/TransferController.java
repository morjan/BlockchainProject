package com.controller;

import com.delegate.ContactDelegate;
import com.delegate.CurrenciesDelegate;
import com.delegate.TransferDelegate;
import com.delegate.UserDelegate;
import com.model.pojo.User;
import javax.servlet.http.HttpServletRequest;
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
public class TransferController {
    @Autowired
    TransferDelegate transferDelegate;
    @Autowired
    UserDelegate userDelegate;
    @Autowired
    CurrenciesDelegate currenciesDelegate;
    @Autowired
    ContactDelegate contactDelegate;
    
    
    @RequestMapping(value = "transferPage")
    public String transferPage(Model model, HttpSession session) {
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            
            model.addAttribute("transfersList", transferDelegate.getAllTransfersByUser(userConnected));
            model.addAttribute("contactsList", contactDelegate.getContactsList(userConnected));
            model.addAttribute("currenciesList", currenciesDelegate.getAllCurrencies());
            model.addAttribute("ratesList", currenciesDelegate.getAllRatesForCurrency(userConnected.getCurrency()));
            
            return "user_transfer";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
    
    @RequestMapping(value = "processTransfer", method = RequestMethod.POST)
    public String processTransfer(Model model, HttpSession session, 
            @ModelAttribute("recipient") int idRecipient, @ModelAttribute("amount") double amountSender) {
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            
            if(amountSender > 0) {
                if(amountSender < userConnected.getCredit()) {
                    if(userDelegate.userExistById(idRecipient)) {
                        if(contactDelegate.userInListContact(userConnected, idRecipient)) {
                            transferDelegate.processTransfer(userConnected, amountSender, idRecipient);
                            
                            return "redirect: transferPage.htm";
                        } else {
                            model.addAttribute("errorMessage", "The recipient is not in your contact list");
                        }
                    } else {
                        model.addAttribute("errorMessage", "The recipient not exist");
                    }
                } else {
                    model.addAttribute("errorMessage", "You don't have enaugh credit in your account");
                }
            } else {
                model.addAttribute("errorMessage", "You can just process a transfer of a positive amount");
            }
            
            model.addAttribute("transfersList", transferDelegate.getAllTransfersByUser(userConnected));
            model.addAttribute("contactsList", contactDelegate.getContactsList(userConnected));
            model.addAttribute("currenciesList", currenciesDelegate.getAllCurrencies());
            model.addAttribute("ratesList", currenciesDelegate.getAllRatesForCurrency(userConnected.getCurrency()));
            
            return "user_transfer";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
    
    @RequestMapping(value = "/searchTransfers", method = RequestMethod.GET)
    public String searchTransfers(Model model, HttpSession session, HttpServletRequest request) {
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            String transferStatus = request.getParameter("transferStatus");
            String contact = request.getParameter("contact");
            String dateFrom = request.getParameter("dateFrom");
            String dateTo = request.getParameter("dateTo");
            
            model.addAttribute("transfersList", transferDelegate.getTransferByUserForSearch(userConnected, transferStatus, contact, dateFrom, dateTo));
            model.addAttribute("contactsList", contactDelegate.getContactsList(userConnected));
            model.addAttribute("currenciesList", currenciesDelegate.getAllCurrencies());
            model.addAttribute("ratesList", currenciesDelegate.getAllRatesForCurrency(userConnected.getCurrency()));
                        
            return "user_transfer";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }   
}
