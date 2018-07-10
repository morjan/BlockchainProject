package com.model.dao;

import com.model.pojo.CurrencyChange;

/**
 * DAO class to query the pojo object entity mapping class CurrencyChange
 * @author lovet
 */
public interface CurrencyChangeDAO {
    
    /**
     * save the currency change of a user
     * @param currencyChange 
     */
    public void saveCurrencyChange(CurrencyChange currencyChange);
}
