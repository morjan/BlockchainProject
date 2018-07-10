package com.model.dao;

import com.model.pojo.DebitCard;
import java.util.List;

/**
 * DAO class to query the pojo object entity mapping class DebitCard
 * @author lovet
 */
public interface DebitCardDAO {
    
    /**
     * get the debit card of a user
     * @param idUser
     * @return 
     */
    public List<DebitCard> getDebitCardByIdUser(int idUser);
    
    /**
     * save a debit card
     * @param debitCard 
     */
    public void saveDebitCard(DebitCard debitCard);
    
    /**
     * delete a debit card
     * @param debitCard 
     */
    public void deleteDebitCard(DebitCard debitCard);
    
}
