package com.delegate;

import com.model.pojo.BankDetails;
import com.model.pojo.DebitCard;
import com.model.pojo.User;

/**
 *
 * @author morjalem
 */
public interface UserDelegate {
    
    public User getUserById(int idUser);
    
    public User getUserByIdLogin(int idLogin);
        
    public void saveUser(User user, int idLogin, int idCurrency);
    
    public User updateUser(User userConnected,User userUpdate);
    
    public void saveDebitCard(DebitCard debitCard, int idUser);
    
    public void deleteDebitCardByUser(int idUser);
    
    public boolean userHasDebitCard(int idUser);
    
    public void saveBankDetails(BankDetails bankDetails, int idUser);
    
    public void deleteBankDetailsByUser(int idUser);
        
    public boolean userHasBankDetails(int idUser);
    
    public boolean userExistById(int idUser);   
}
