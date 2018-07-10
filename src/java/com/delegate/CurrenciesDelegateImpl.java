package com.delegate;

import com.model.dao.CurrenciesDAO;
import com.model.dao.RatesDAO;
import com.model.pojo.Currency;
import com.model.pojo.Rate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author morjalem
 */
@Service
public class CurrenciesDelegateImpl implements CurrenciesDelegate {
    @Autowired 
    CurrenciesDAO currenciesDAO;
    @Autowired 
    RatesDAO ratesDAO;
    
    @Override
    public Currency getCurrencyById(int idCurrency) {
        return currenciesDAO.getCurrencyById(idCurrency);
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currenciesDAO.getAllCurrencies();
    }

    @Override
    public double amountConverted(double amount, int idCurrencyFrom, int idCurrencyTo) {
        Currency currencyFrom = currenciesDAO.getCurrencyById(idCurrencyFrom);
        Currency currencyTo = currenciesDAO.getCurrencyById(idCurrencyTo);
        Rate rate = ratesDAO.getRateForCurrencyFromAndCurrencyTo(currencyFrom, currencyTo);        
        double amountConverted = amount * rate.getRate();
        String amountConvertedStr = String.format("%.2f", amountConverted);
        double amountConverted2Digits = Double.valueOf(amountConvertedStr);
        
        return amountConverted2Digits;
    }    

    @Override
    public List<Rate> getAllRatesForCurrency(Currency currencyFrom) {
        return ratesDAO.getAllRateForCurrencyFrom(currencyFrom);
    }
}
