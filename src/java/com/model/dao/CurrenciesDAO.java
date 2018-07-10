package com.model.dao;

import com.model.pojo.Currency;
import java.util.List;

/**
 * DAO class to query the pojo object entity mapping class Currency
 * @author morjalem
 */
public interface CurrenciesDAO {
    
    /**
     * get the currency object by its id
     * @param idCurrency
     * @return 
     */
    public Currency getCurrencyById(int idCurrency);
    
    /**
     * get the list of all currency in the database
     * @return 
     */
    public List<Currency> getAllCurrencies();
    
}
