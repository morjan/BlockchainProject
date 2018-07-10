package com.model.dao;

import com.model.pojo.Currency;
import com.model.pojo.Rate;
import java.util.List;

/**
 * DAO class to query the pojo object entity mapping class Rate
 * @author morjalem
 */
public interface RatesDAO {
    
    /**
     * get the rate between two currencies
     * @param currencyFrom
     * @param currencyTo
     * @return 
     */
    public Rate getRateForCurrencyFromAndCurrencyTo(Currency currencyFrom, Currency currencyTo);
    
    /**
     * get the list of rates for all currencies in the database
     * @param currencyFrom
     * @return 
     */
    public List<Rate> getAllRateForCurrencyFrom(Currency currencyFrom);
}
