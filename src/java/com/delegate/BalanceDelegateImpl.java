package com.delegate;

import com.model.dao.BalanceOperationDAO;
import com.model.dao.TypeOperationDAO;
import com.model.dao.UserDAO;
import com.model.pojo.BalanceOperation;
import com.model.pojo.TypeOperation;
import com.model.pojo.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lovet
 */
@Service
public class BalanceDelegateImpl implements BalanceDelegate {
    @Autowired
    UserDAO userDAO;
    @Autowired
    BalanceOperationDAO balanceOperationDAO;
    @Autowired
    TypeOperationDAO typeOperationDAO;
    
    @Override
    public List<BalanceOperation> getAllBalanceOperationsByUser(int idUser) {
        return balanceOperationDAO.getAllBalanceOperationByUser(idUser);
    }
    
    @Override
    public List<BalanceOperation> getAllBalanceOperationsByUserAndCriteria
        (int idUser, String idTypeOperation, String dateFrom, String dateTo, String amountMin, String amountMax) {
            HashMap<String, Object> searchCriterias = new HashMap<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            if(!idTypeOperation.isEmpty() && (idTypeOperation.equals("1") || idTypeOperation.equals("2"))) {
                TypeOperation typeOperation = typeOperationDAO.getTypeOperationById(Integer.valueOf(idTypeOperation));
                
                searchCriterias.put("typeOperation", typeOperation);
            }
            if(!dateFrom.isEmpty()) {
                try {
                    searchCriterias.put("dateFrom", dateFormat.parse(dateFrom));
                } catch (ParseException ex) {
                    Logger.getLogger(BalanceDelegateImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(!dateTo.isEmpty()) {
                try {
                    searchCriterias.put("dateTo", dateFormat.parse(dateTo));
                } catch (ParseException ex) {
                    Logger.getLogger(BalanceDelegateImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(!amountMin.isEmpty()) {
                searchCriterias.put("amountMin", Double.valueOf(amountMin));
            }
            if(!amountMax.isEmpty()) {
                searchCriterias.put("amountMax", Double.valueOf(amountMax));
            }
            
            return balanceOperationDAO.getAllBalanceOperationByUserWithSearchCriterias(idUser, searchCriterias);
    }
    
    @Override
    public List<TypeOperation> getAllTypeOperation() {
        return typeOperationDAO.getAllTypeOperation();
    }
    
    @Override
    public void processBalanceOperation(User userConnected, int idTypeOperation, double amountOperation) {
        TypeOperation typeOperation = typeOperationDAO.getTypeOperationById(idTypeOperation);
        Date dateOperation = new Date();
        BalanceOperation newBalanceOperation = new BalanceOperation();
        
        newBalanceOperation.setIdUser(userConnected.getIdUser());
        newBalanceOperation.setTypeOperation(typeOperation);
        newBalanceOperation.setCurrencyOperation(userConnected.getCurrency());
        newBalanceOperation.setDateOperation(dateOperation);
        
        if(idTypeOperation == 1) {
            newBalanceOperation.setAmountOperation(amountOperation);
            userConnected.setCredit(userConnected.getCredit() + amountOperation);
            
            userDAO.updateUser(userConnected);
        }
        else if(idTypeOperation == 2) {
            newBalanceOperation.setAmountOperation(amountOperation * (-1));
            userConnected.setCredit(userConnected.getCredit() - amountOperation);
            
            userDAO.updateUser(userConnected);
        }
        
        balanceOperationDAO.saveBalanceOperation(newBalanceOperation);
    }
}