package com.delegate;

import com.model.pojo.BalanceOperation;
import com.model.pojo.TypeOperation;
import com.model.pojo.User;
import java.util.List;

/**
 *
 * @author lovet
 */
public interface BalanceDelegate {
    /**
     * get the list of balanceOperation for a user
     * @param idUser
     * @return 
     */
    public List<BalanceOperation> getAllBalanceOperationsByUser(int idUser);
    
    /**
     * get the list of balanceOperation for a user with search parameters 
     * @param idUser
     * @param idTypeOperation
     * @param dateFrom
     * @param dateTo
     * @param amountMin
     * @param amountMax
     * @return 
     */
    public List<BalanceOperation> getAllBalanceOperationsByUserAndCriteria(int idUser, String idTypeOperation, String dateFrom, String dateTo, String amountMin, String amountMax);
    
    /**
     * get the list of type of balance operation
     * @return 
     */
    public List<TypeOperation> getAllTypeOperation();
    
    /**
     * process a balance operation for a user
     * @param userConnected
     * @param idTypeOperation
     * @param amountOperation 
     */
    public void processBalanceOperation(User userConnected, int idTypeOperation, double amountOperation);
}
