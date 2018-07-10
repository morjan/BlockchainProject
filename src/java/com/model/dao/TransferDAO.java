package com.model.dao;

import com.model.pojo.Transfer;
import com.model.pojo.User;
import java.util.HashMap;
import java.util.List;

/**
 * DAO class to query the pojo object entity mapping class Transfer
 * @author lovet
 */
public interface TransferDAO {
    
    /**
     * save a transfer between two user application
     * @param transfer 
     */
    public void saveTransfer(Transfer transfer);
    
    /**
     * get the history of transfers made by a user
     * @param user
     * @return 
     */
    public List<Transfer> getAllTransfersByUser(User user);
    
    /**
     * get the history of transfers made by a user with search criterias
     * @param user
     * @param searchCriterias
     * @return 
     */
    public List<Transfer> getTransferByUserForSearch(User user, HashMap<String, Object> searchCriterias);
}
