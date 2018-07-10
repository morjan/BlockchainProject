package com.model.dao;

import com.model.pojo.DebitCard;
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
public class DebitCardDAOImpl implements DebitCardDAO {
    private static Session session;
    private static Transaction transaction;

    
    @Override
    public List<DebitCard> getDebitCardByIdUser(int idUser) {
        List<DebitCard> debitCardLst = null;
        String hqlQuery = "SELECT d FROM DebitCard d WHERE d.idUser = :idUser";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            debitCardLst = session.createQuery(hqlQuery)
                    .setInteger("idUser", idUser)
                    .list();
            
            session.close();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        
        return debitCardLst;
    }

    @Override
    public void saveDebitCard(DebitCard debitCard) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            session.save(debitCard);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteDebitCard(DebitCard debitCard) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.delete(debitCard);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e.getMessage());
        }
    }
    
}
