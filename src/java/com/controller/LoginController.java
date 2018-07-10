package com.controller;

import com.delegate.CurrenciesDelegate;
import com.delegate.UserDelegate;
import com.delegate.UserLoginDelegate;
import com.model.pojo.User;
import com.model.pojo.UserLogin;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author morjan
 */
@Controller
public class LoginController {
    @Autowired
    UserDelegate userDelegate;
    @Autowired
    UserLoginDelegate userLoginDelegate;
    @Autowired
    CurrenciesDelegate currenciesDelegate;
    
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, HttpSession session, HttpServletRequest request) {
        if(request.getParameter("email") != null && request.getParameter("password") != null) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            if(!email.isEmpty() && !password.isEmpty()) {
                List<UserLogin> userLoginLst = userLoginDelegate.getLoginByEmailAndPassword(email, password);

                if(!userLoginLst.isEmpty()) {
                    UserLogin userLogin = userLoginLst.get(0);
                    User userConnected = userDelegate.getUserByIdLogin(userLogin.getIdLogin());
                    
                    session.setAttribute("user", userConnected);
                    model.addAttribute("currenciesList", currenciesDelegate.getAllCurrencies());
                    model.addAttribute("ratesList", currenciesDelegate.getAllRatesForCurrency(userConnected.getCurrency()));
            
                    return "user_home";
                } else {
                    model.addAttribute("errorMessage", "wrong email or password");
                }
            } else {
                model.addAttribute("errorMessage", "empty inputs");
            }
        } else {
            model.addAttribute("errorMessage", "missing inputs");
        }
        
        return "login";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session ) {
        session.setAttribute("user", null);
        session.invalidate();
        
        return "login";
    }
}