
package com.delegate;

import com.model.pojo.UserLogin;
import java.util.List;

/**
 *
 * @author lovet
 */
public interface UserLoginDelegate {
    
    public UserLogin getLoginByIdLogin(int idLogin);
    
    public List<UserLogin> getLoginByEmailAndPassword(String email, String password);
    
    public List<UserLogin> getIdLoginByEmail(String email);
    
    public UserLogin saveLoginForUserApplication(UserLogin userLogin);
    
    public void updateUserLoginEmail(UserLogin userLogin, String newEmail);
    
    public void udpdateUserLoginPassword(UserLogin userLogin, String newPassword);
    
    public boolean testEmailExist(String email);
}
