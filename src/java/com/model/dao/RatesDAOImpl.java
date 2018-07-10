package com.model.dao;

import com.model.pojo.Currency;
import com.model.pojo.Rate;
import com.model.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author morjalem
 */
@Repository
public class RatesDAOImpl implements RatesDAO {
    private static Session session;
//    private static Transaction transaction;
    
    @Override
    public Rate getRateForCurrencyFromAndCurrencyTo(Currency currencyFrom, Currency currencyTo) {
        Rate rate = null;
        String hqlQuery = "SELECT r FROM Rate r WHERE r.currencyFrom = :currencyFrom AND r.currencyTo = :currencyTo";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            rate = (Rate) session.createQuery(hqlQuery)
                    .setEntity("currencyFrom", currencyFrom)
                    .setEntity("currencyTo", currencyTo)
                    .list().get(0);
            
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
        
        return rate;
    }

    @Override
    public List<Rate> getAllRateForCurrencyFrom(Currency currencyFrom) {
        List<Rate> rateLst = null;
        String hqlQuery = "SELECT r FROM Rate r WHERE r.currencyFrom = :currencyFrom";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            rateLst = session.createQuery(hqlQuery)
                    .setEntity("currencyFrom", currencyFrom)
                    .list();
            
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
        
        return rateLst;
    }
}
