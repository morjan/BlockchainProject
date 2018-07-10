package com.model.dao;

import com.model.pojo.User;
import java.util.List;

/**
 * DAO class to query the pojo object entity mapping class User
 * @author morjalem
 */
public interface UserDAO {
    
    /**
     * get a user by its id
     * @param idUser
     * @return User object
     */
    public User getUserById(int idUser);
    
    /**
     * get a user by its id and return a list (more easy for test)
     * @param idUser
     * @return List<User>
     */
    public List<User> getListUserById(int idUser);
    
    /**
     * get a user by its idLogin
     * @param idLogin
     * @return 
     */
    public User getUserByIdLogin(int idLogin);    
    
    /**
     * save a user
     * @param user 
     */
    public void saveUser(User user);
    
    /**
     * update a user
     * @param user 
     */
    public void updateUser(User user);
}
