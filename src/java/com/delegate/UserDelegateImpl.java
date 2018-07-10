package com.delegate;

import com.model.dao.BankDetailsDAO;
import com.model.dao.CurrenciesDAO;
import com.model.dao.CurrencyChangeDAO;
import com.model.dao.DebitCardDAO;
import com.model.dao.UserDAO;
import com.model.pojo.BankDetails;
import com.model.pojo.Currency;
import com.model.pojo.CurrencyChange;
import com.model.pojo.DebitCard;
import com.model.pojo.User;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author morjalem
 */
@Service
public class UserDelegateImpl implements UserDelegate {
    @Autowired
    UserDAO userDAO;
    @Autowired
    CurrenciesDAO currenciesDAO;
    @Autowired
    CurrencyChangeDAO currencyChangeDAO;
    @Autowired
    DebitCardDAO debitCardDAO;
    @Autowired
    BankDetailsDAO bankDetailsDAO;
    
    
    @Override
    public User getUserById(int idUser) {
        return userDAO.getUserById(idUser);
    }
    
    @Override
    public User getUserByIdLogin(int idLogin) {
        return userDAO.getUserByIdLogin(idLogin);
    }

    @Override
    public void saveUser(User user, int idLogin, int idCurrency) {
        Currency currency = currenciesDAO.getCurrencyById(idCurrency);

        user.setIdLogin(idLogin);
        user.setCurrency(currency);
        user.setCredit(0);
        
        userDAO.saveUser(user);
    }
    
    @Override
    public User updateUser(User userConnected, User userUpdate) {  
        userUpdate.setIdUser(userConnected.getIdUser());
        userUpdate.setIdLogin(userConnected.getIdLogin());
        userUpdate.setFirstName(userConnected.getFirstName());
        userUpdate.setLastName(userConnected.getLastName());
        userUpdate.setBirthDate(userConnected.getBirthDate());
        
        if(!userConnected.getCurrency().equals(userUpdate.getCurrency())) {
            CurrencyChange currencyChange = new CurrencyChange();
            
            currencyChange.setIdUser(userConnected.getIdUser());
            currencyChange.setCurrencyFrom(userConnected.getCurrency());
            currencyChange.setCurrencyTo(userUpdate.getCurrency());
            currencyChange.setCreditFrom(userConnected.getCredit());
            currencyChange.setCreditTo(userUpdate.getCredit());
            currencyChange.setDateChange(new Date());
            
            currencyChangeDAO.saveCurrencyChange(currencyChange);
        }
        
        userDAO.updateUser(userUpdate);
        
        return userUpdate;
    }
    
    @Override
    public void saveDebitCard(DebitCard debitCard, int idUser) {
        debitCard.setIdUser(idUser);
        
        debitCardDAO.saveDebitCard(debitCard);
    }
    
    @Override
    public void deleteDebitCardByUser(int idUser) {
        DebitCard debitCard = debitCardDAO.getDebitCardByIdUser(idUser).get(0);
        
        debitCardDAO.deleteDebitCard(debitCard);
    }
    
    @Override
    public boolean userHasDebitCard(int idUser) {
        List<DebitCard> debitCardLst = debitCardDAO.getDebitCardByIdUser(idUser);
        
        if(!debitCardLst.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void saveBankDetails(BankDetails bankDetails, int idUser) {
        bankDetails.setIdUser(idUser);
        
        bankDetailsDAO.saveBankDetails(bankDetails);
    }
    
    @Override
    public void deleteBankDetailsByUser(int idUser) {
        BankDetails bankDetails = bankDetailsDAO.getBankDetailsByIdUser(idUser).get(0);
        
        bankDetailsDAO.deleteBankDeails(bankDetails);
    }
    
    @Override
    public boolean userHasBankDetails(int idUser) {
        List<BankDetails> bankDetailsLst = bankDetailsDAO.getBankDetailsByIdUser(idUser);
        
        if(!bankDetailsLst.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean userExistById(int idUser) {
        return !userDAO.getListUserById(idUser).isEmpty();
    }
}
