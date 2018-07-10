package com.controller;

import com.delegate.CurrenciesDelegate;
import com.model.pojo.Currency;
import com.model.pojo.User;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author morjan
 */
@Controller
public class NavigationController {
    @Autowired
    CurrenciesDelegate currenciesDelegate;
    
        
    @RequestMapping(value = "/homePage")
    public String homePage(Model model) {
        List<Currency> currenciesList = currenciesDelegate.getAllCurrencies();
        
        model.addAttribute("currenciesList", currenciesList);
        
        return "home";
    }
    
    @RequestMapping(value = "/loginPage")
    public String loginPage(Model model) {
        
        return "login";
    }
    
    @RequestMapping(value = "/registrationPage")
    public String registrationPage(Model model) {
        List<Currency> currenciesList = currenciesDelegate.getAllCurrencies();
            
        model.addAttribute("currenciesList", currenciesList);
        
        return "registration";
    }   
    
    @RequestMapping(value = "homeUserPage")
    public String homeUserPage(Model model, HttpSession session) {
        if(session.getAttribute("user") != null) {
            User userConnected = (User) session.getAttribute("user");
            
            model.addAttribute("currenciesList", currenciesDelegate.getAllCurrencies());
            model.addAttribute("ratesList", currenciesDelegate.getAllRatesForCurrency(userConnected.getCurrency()));
            
            return "user_home";
        } else {
            model.addAttribute("errorMessage", "You are not connected");
        }
        
        return "login";
    }
}
