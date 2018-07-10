package com.controller;

import com.delegate.CurrenciesDelegate;
import com.delegate.UserDelegate;
import com.delegate.UserLoginDelegate;
import com.model.pojo.BankDetails;
import com.model.pojo.Currency;
import com.model.pojo.DebitCard;
import com.model.pojo.User;
import com.model.pojo.UserLogin;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author morjalem
 */
@Controller
public class UserController {
    @Autowired
    UserDelegate userDelagte;
    @Autowired
    UserLoginDelegate userLoginDelegate;
    @Autowired
    CurrenciesDelegate currenciesDelegate;
    
    
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(Model model, 
            @ModelAttribute("User") User user, BindingResult result,
            @ModelAttribute("UserLogin") UserLogin userLogin, BindingResult result2,
            @ModelAttribute(value="currency") int idCurrency) {        
        
        if(!userLoginDelegate.testEmailExist(userLogin.getEmail())) {
            UserLogin userLoginSaved = userLoginDelegate.saveLoginForUserApplication(userLogin);            
            userDelagte.saveUser(user, userLoginSaved.getIdLogin(), idCurrency);
            
            model.addAttribute("successMessage", "Your are well registered");
        } else {
            model.addAttribute("errorMessage", "this email already exist");
        }       
        
        return "login";
    }
    
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String update(Model model, HttpSession session, @ModelAttribute("User") User userUpdate, BindingResult result, @ModelAttribute(value="currency") int idCurrency) {                 
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            Currency currencyUserConnected = userConnected.getCurrency();
            Currency newCurrency = currenciesDelegate.getCurrencyById(idCurrency);
            
            if(!currencyUserConnected.equals(newCurrency)) {
                userUpdate.setCurrency(newCurrency);
                userUpdate.setCredit(currenciesDelegate.amountConverted(userConnected.getCredit(), currencyUserConnected.getIdCurrency(), newCurrency.getIdCurrency()));
            } else {
                userUpdate.setCurrency(userConnected.getCurrency());
                userUpdate.setCredit(userConnected.getCredit());
            }
            
            userUpdate = userDelagte.updateUser(userConnected, userUpdate);
            
            model.addAttribute("successMessage", "Your informations were well changed");
            model.addAttribute("currenciesList", currenciesDelegate.getAllCurrencies());
            model.addAttribute("ratesList", currenciesDelegate.getAllRatesForCurrency(userConnected.getCurrency()));
            session.setAttribute("user", userUpdate);
            
            return "user_home";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
    
    @RequestMapping(value = "profilePage")
    public String profilePage(Model model, HttpSession session) {
        if(session.getAttribute("user") != null) {                     
            User userConnected = (User)session.getAttribute("user");
            UserLogin userLoginConnected = userLoginDelegate.getLoginByIdLogin(userConnected.getIdLogin());
            List<Currency> currenciesList = currenciesDelegate.getAllCurrencies();
            boolean userHasDebitCard = userDelagte.userHasDebitCard(userConnected.getIdUser());
            boolean userHasBankDetails = userDelagte.userHasBankDetails(userConnected.getIdUser());
            
            model.addAttribute("userProfile", userConnected);
            model.addAttribute("userEmail", userLoginConnected.getEmail());
            model.addAttribute("currenciesList", currenciesList);
            if(userHasDebitCard == true) {
                model.addAttribute("debitCard", true);
            }
            if(userHasBankDetails == true){
                model.addAttribute("bankDetails", true);
            }           
            
            return "user_profile";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
    
    @RequestMapping(value = "changeEmail", method = RequestMethod.POST)
    public String changeEmail(Model model, HttpSession session, @ModelAttribute(value="newEmail") String newEmail) {
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            UserLogin userLogin = userLoginDelegate.getLoginByIdLogin(userConnected.getIdLogin());
            
            if(!userLoginDelegate.testEmailExist(newEmail)) {
                userLoginDelegate.updateUserLoginEmail(userLogin, newEmail);
                model.addAttribute("successMessage", "the email was well changed");
            } else {
                model.addAttribute("errorMessage", "this email already exist");
            }           
            
            model.addAttribute("currenciesList", currenciesDelegate.getAllCurrencies());
            model.addAttribute("ratesList", currenciesDelegate.getAllRatesForCurrency(userConnected.getCurrency()));
            
            return "user_home";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
    
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassword(Model model, HttpSession session, @ModelAttribute(value="currentPassword") String currentPassword, @ModelAttribute(value="newPassword") String newPassword) {
        if(session.getAttribute("user") != null) {            
            User userConnected = (User) session.getAttribute("user");
            UserLogin userLogin = userLoginDelegate.getLoginByIdLogin(userConnected.getIdLogin());
            
            if(userLogin.getPassword().equals(currentPassword)) {
                userLoginDelegate.udpdateUserLoginPassword(userLogin, newPassword);
                
                model.addAttribute("successMessage", "the password was well changed");
            } else {
                model.addAttribute("errorMessage", "the current password input is not the good one");
            }
            
            model.addAttribute("currenciesList", currenciesDelegate.getAllCurrencies());
            model.addAttribute("ratesList", currenciesDelegate.getAllRatesForCurrency(userConnected.getCurrency()));
            
            return "user_home";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
    
    @RequestMapping(value = "/addDebitCard", method = RequestMethod.POST)
    public String addDebitCard(Model model, HttpSession session, @ModelAttribute("DebitCard") DebitCard debitCard) {
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            boolean userHasDebitCard = userDelagte.userHasDebitCard(userConnected.getIdUser());
            
            if(userHasDebitCard == false) {
                userDelagte.saveDebitCard(debitCard, userConnected.getIdUser());
                model.addAttribute("successMessage", "your debit card is save, you can credit your account");
            } else {
                model.addAttribute("errorMessage", "You already have a debit card save in your account");
            }
            
            model.addAttribute("currenciesList", currenciesDelegate.getAllCurrencies());
            model.addAttribute("ratesList", currenciesDelegate.getAllRatesForCurrency(userConnected.getCurrency()));
            
            return "user_home";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
    
    @RequestMapping(value = "/deleteDebitCard", method = RequestMethod.POST)
    public String deleteDebitCard(Model model, HttpSession session) {
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            boolean userHasDebitCard = userDelagte.userHasDebitCard(userConnected.getIdUser());
            
            if(userHasDebitCard == true) {
                userDelagte.deleteDebitCardByUser(userConnected.getIdUser());
                model.addAttribute("successMessage", "your debit card is delete");
            } else {
                model.addAttribute("errorMessage", "You don't have a debit card save in your account");
            }
            
            model.addAttribute("currenciesList", currenciesDelegate.getAllCurrencies());
            model.addAttribute("ratesList", currenciesDelegate.getAllRatesForCurrency(userConnected.getCurrency()));
            
            return "user_home";           
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
    
    @RequestMapping(value = "/addBankDetails", method = RequestMethod.POST)
    public String addBankDetails(Model model, HttpSession session, @ModelAttribute("BankDetails") BankDetails bankDetails) {
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            boolean userHasBankDetails = userDelagte.userHasBankDetails(userConnected.getIdUser());
            
            if(userHasBankDetails == false) {
                userDelagte.saveBankDetails(bankDetails, userConnected.getIdUser());
                model.addAttribute("successMessage", "Your bank details is save, you cash out your account");
            } else {
                model.addAttribute("errorMessage", "You already have a bank details save in your account");
            }
            
            model.addAttribute("currenciesList", currenciesDelegate.getAllCurrencies());
            model.addAttribute("ratesList", currenciesDelegate.getAllRatesForCurrency(userConnected.getCurrency()));
            
            return "user_home";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
    
    @RequestMapping(value = "/deleteBankDetails", method = RequestMethod.POST)
    public String deleteBankDetails(Model model, HttpSession session) {
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            boolean userHasBankDetails = userDelagte.userHasBankDetails(userConnected.getIdUser());
            
            if(userHasBankDetails == true) {
                userDelagte.deleteBankDetailsByUser(userConnected.getIdUser());
                model.addAttribute("successMessage", "your bank details is delete");
            } else {
                model.addAttribute("errorMessage", "You don't have a bank details save in your account");
            }
            
            model.addAttribute("currenciesList", currenciesDelegate.getAllCurrencies());
            model.addAttribute("ratesList", currenciesDelegate.getAllRatesForCurrency(userConnected.getCurrency()));
            
            return "user_home";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
    
    @RequestMapping(value = "/emailExist", method = RequestMethod.GET)
    public @ResponseBody String emailExist(@ModelAttribute(value="email") String email) {     
        if(userLoginDelegate.testEmailExist(email)) {
            return "email exist";
        } else {
            return "email not exist";
        }
    }
}
