package com.model.dao;

import com.model.pojo.CurrencyChange;
import com.model.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lovet
 */
@Repository
public class CurrencyChangeDAOImpl implements CurrencyChangeDAO {
    private static Session session;
    private static Transaction transaction;
    
    
    @Override
    public void saveCurrencyChange(CurrencyChange currencyChange) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            session.save(currencyChange);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e.getMessage());
        }
    }
}
