package com.model.dao;

import com.model.pojo.BankDetails;
import java.util.List;

/**
 * DAO class to query the pojo object entity mapping class BankDetails
 * @author lovet
 */
public interface BankDetailsDAO {
    
    /**
     * get the bank details of a user by its id
     * @param idUser
     * @return 
     */
    public List<BankDetails> getBankDetailsByIdUser(int idUser);
    
    /**
     * save the bank details of a user
     * @param bankDeatils 
     */
    public void saveBankDetails(BankDetails bankDeatils);
    
    /**
     * delete the bank details of a users
     * @param bankDetails 
     */
    public void deleteBankDeails(BankDetails bankDetails);
}
