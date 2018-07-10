/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.delegate.CurrenciesDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author morjalem
 */
@Controller
public class CurrencyController {
    @Autowired
    CurrenciesDelegate currenciesDelegate;
    
    @RequestMapping(value = "/convert", method=RequestMethod.GET)
    public @ResponseBody String convert(Model model,
            @ModelAttribute(value="amount") double amount,
            @ModelAttribute(value="currencyFrom") int currencyFrom, 
            @ModelAttribute(value="currencyTo") int currencyTo){
        double amountConverted;
        
        amountConverted = currenciesDelegate.amountConverted(amount, currencyFrom, currencyTo);

        return amountConverted+"";
    }
}
