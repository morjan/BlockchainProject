package com.model.dao;

import com.model.pojo.BalanceOperation;
import java.util.HashMap;
import java.util.List;

/**
 * DAO class to query the pojo object entity mapping class BalanceOperation
 * @author lovet
 */
public interface BalanceOperationDAO {
    
    /**
     * get the history of the balance operations of a user by its id
     * @param idUser
     * @return 
     */
    public List<BalanceOperation> getAllBalanceOperationByUser(int idUser);
    
    /**
     * get the history of the balance operations of a user by its id but also a list of search criterias
     * @param idUser
     * @param searchCriterias
     * @return 
     */
    public List<BalanceOperation> getAllBalanceOperationByUserWithSearchCriterias(int idUser, HashMap<String, Object> searchCriterias);
    
    /**
     * save a balance operation
     * @param balanceOperation 
     */
    public void saveBalanceOperation(BalanceOperation balanceOperation);
}
