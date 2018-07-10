package com.delegate;

import com.model.pojo.Currency;
import com.model.pojo.Rate;
import java.util.List;

/**
 *
 * @author morjalem
 */
public interface CurrenciesDelegate {
    
    public Currency getCurrencyById(int idCurrency);
    
    public List<Currency> getAllCurrencies();
    
    public double amountConverted(double amount, int idCurrencyFrom, int idCurrencyTo);
    
    public List<Rate> getAllRatesForCurrency(Currency currencyFrom);
}
