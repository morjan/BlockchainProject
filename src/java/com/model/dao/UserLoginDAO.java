package com.model.dao;

import com.model.pojo.UserLogin;
import java.util.List;

/**
 * DAO class to query the pojo object entity mapping class UserLogin
 * @author lovet
 */
public interface UserLoginDAO {
    
    /**
     * get the UserLogin object for a User with the idLogin
     * @param idLogin
     * @return UserLogin
     */
    public UserLogin getLoginByIdLogin(int idLogin);
    
    /**
     * get the UserLogin object for a User with the email
     * @param email
     * @return List<UserLogin>
     */
    public List<UserLogin> getLoginByEmail(String email);
    
    /**
     * get the UserLogin object for a User with the email and password
     * @param email
     * @param password
     * @return List<UserLogin>
     */
    public List<UserLogin> getLoginByEmailAndPassword(String email, String password);
    
    /**
     * save a UserLogin object
     * @param userLogin
     * @return UserLogin
     */
    public UserLogin saveLogin(UserLogin userLogin);
    
    /**
     * update a UserLogin object
     * @param userLogin 
     */
    public void updateUserLogin(UserLogin userLogin);
}
