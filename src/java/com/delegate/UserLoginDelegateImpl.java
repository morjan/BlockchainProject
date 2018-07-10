package com.delegate;

import com.model.dao.UserLoginDAO;
import com.model.pojo.UserLogin;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lovet
 */
@Service
public class UserLoginDelegateImpl implements UserLoginDelegate {
    @Autowired
    UserLoginDAO userLoginDAO;
    
    
    @Override
    public UserLogin getLoginByIdLogin(int idLogin) {
        return userLoginDAO.getLoginByIdLogin(idLogin);
    }
    @Override
    public List<UserLogin> getLoginByEmailAndPassword(String email, String password) {
        return userLoginDAO.getLoginByEmailAndPassword(email, password);
    }
    
    @Override
    public List<UserLogin> getIdLoginByEmail(String email) {
        return userLoginDAO.getLoginByEmail(email);
    }
    
    @Override
    public UserLogin saveLoginForUserApplication(UserLogin userLogin) {
        userLogin.setIdRole(1);
        
        return userLoginDAO.saveLogin(userLogin);
    }
    
    @Override
    public void updateUserLoginEmail(UserLogin userLogin, String newEmail) {
        userLogin.setEmail(newEmail);
        
        userLoginDAO.updateUserLogin(userLogin);
    }
    
    @Override
    public void udpdateUserLoginPassword(UserLogin userLogin, String newPassword) {
        userLogin.setPassword(newPassword);
        
        userLoginDAO.updateUserLogin(userLogin);
    }
    
    @Override
    public boolean testEmailExist(String email) {
        return !userLoginDAO.getLoginByEmail(email).isEmpty();
    }
}
