package com.model.dao;

import com.model.pojo.Currency;
import com.model.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

/**
 *
 * @author morjalem
 */
@Repository
public class CurrenciesDAOImpl implements CurrenciesDAO {
    private static Session session;
    private static Transaction transaction;
    
    @Override
    public Currency getCurrencyById(int idCurrency) {
        Currency currency = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            currency = (Currency)session.get(Currency.class, idCurrency);
            
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
        
        return currency;
    }
    
    @Override
    public List<Currency> getAllCurrencies() {
        List<Currency> currenciesList = null;
        String hqlQuery = "SELECT c FROM Currency c";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            currenciesList = session.createQuery(hqlQuery).list();
            
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
        
        return currenciesList;  
    }
}
