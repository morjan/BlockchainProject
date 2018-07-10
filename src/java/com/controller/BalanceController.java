package com.controller;

import com.delegate.BalanceDelegate;
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
public class BalanceController {
    @Autowired
    UserDelegate userDelegate;
    @Autowired
    BalanceDelegate balanceDelegate;
    
    
    @RequestMapping(value = "creditPage")
    public String creditPage(Model model, HttpSession session) {
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");

            model.addAttribute("typeOperation", balanceDelegate.getAllTypeOperation());
            model.addAttribute("listOperation", balanceDelegate.getAllBalanceOperationsByUser(userConnected.getIdUser()));
            
            return "user_credit";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
    
    
    
    @RequestMapping(value = "/processBalanceOperation", method = RequestMethod.POST)
    public String processBalanceOperation(Model model, HttpSession session, @ModelAttribute("typeOperation") int idTypeOperation, @ModelAttribute("amountOperation") double amountOperation) {
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            boolean userHasDebitCard = userDelegate.userHasDebitCard(userConnected.getIdUser());
            boolean userHasBankDetails = userDelegate.userHasBankDetails(userConnected.getIdUser());

            if(amountOperation > 0) {
                switch (idTypeOperation) {
                    case 1: {
                        if(userHasDebitCard == true) {
                            balanceDelegate.processBalanceOperation(userConnected, idTypeOperation, amountOperation);
                            
                            return "redirect: creditPage.htm";
                        } else {
                            model.addAttribute("errorMessage", "You need to associate a debit card to your account before credit it");
                        }
                        break;
                    }
                    case 2: {
                        if(userHasBankDetails == true){
                            if(userConnected.getCredit() >= amountOperation) {
                                balanceDelegate.processBalanceOperation(userConnected, idTypeOperation, amountOperation);
                                
                                return "redirect: creditPage.htm";
                            } else {
                                model.addAttribute("errorMessage", "You do not have enaugh cash in your balance to proceed this cash out");
                            }
                        } else {
                            model.addAttribute("errorMessage", "You need to associate a bank details to your account before cash out it");
                        }
                        break;
                    }
                    default:
                        model.addAttribute("errorMessage", "error on the type operation");
                        break;
                }
            } else {
                model.addAttribute("errorMessage", "amount operation input is incorrect");
            }
            model.addAttribute("typeOperation", balanceDelegate.getAllTypeOperation());
            model.addAttribute("listOperation", balanceDelegate.getAllBalanceOperationsByUser(userConnected.getIdUser()));

            return "user_credit";            
        }else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
    
    @RequestMapping(value = "/searchBalanceOperations", method = RequestMethod.POST)
    public String searchBalanceOperations(Model model, HttpSession session, HttpServletRequest request) {
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            String idTypeOperation = request.getParameter("typeOperation");
            String dateFrom = request.getParameter("dateFrom");
            String dateTo = request.getParameter("dateTo");
            String amountMin = request.getParameter("amountMin");
            String amountMax = request.getParameter("amountMax");
              
            model.addAttribute("typeOperation", balanceDelegate.getAllTypeOperation());
            model.addAttribute("listOperation", balanceDelegate.getAllBalanceOperationsByUserAndCriteria(userConnected.getIdUser(), idTypeOperation, dateFrom, dateTo, amountMin, amountMax));
            
            return "user_credit";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
}