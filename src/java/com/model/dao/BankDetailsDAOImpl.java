package com.model.dao;

import com.model.pojo.BankDetails;
import com.model.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lovet
 */
@Repository
public class BankDetailsDAOImpl implements BankDetailsDAO {
    private static Session session;
    private static Transaction transaction;
    
    @Override
    public List<BankDetails> getBankDetailsByIdUser(int idUser) {
        List<BankDetails> bankDetailsLst  = null;
        String hqlQuery = "SELECT b FROM BankDetails b WHERE b.idUser = :idUser";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            bankDetailsLst = session.createQuery(hqlQuery)
                    .setInteger("idUser", idUser)
                    .list();
            
            session.close();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        
        return bankDetailsLst;        
    }
    
    @Override
    public void saveBankDetails(BankDetails bankDeatils) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            session.save(bankDeatils);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public void deleteBankDeails(BankDetails bankDetails) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.delete(bankDetails);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e.getMessage());
        }
    }
}
