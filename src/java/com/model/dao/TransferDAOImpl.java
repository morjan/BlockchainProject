package com.model.dao;

import com.model.pojo.Transfer;
import com.model.pojo.User;
import com.model.util.HibernateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lovet
 */
@Repository
public class TransferDAOImpl implements TransferDAO {
    private static Session session;
    private static Transaction transaction;

    
    @Override
    public void saveTransfer(Transfer transfer) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            session.save(transfer);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Transfer> getAllTransfersByUser(User user) {
        List<Transfer> transferLst = new ArrayList<>();
        String hqlQuery = "SELECT t FROM Transfer t WHERE (t.userSender = :user OR t.userRecipient = :user) ORDER BY t.dateTransfer DESC";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transferLst = session.createQuery(hqlQuery)
                    .setEntity("user", user)
                    .list();
            
            session.close();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        
        return transferLst;
    }

    @Override
    public List<Transfer> getTransferByUserForSearch(User user, HashMap<String, Object> searchCriterias) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria transferCriteria = session.createCriteria(Transfer.class)
                .addOrder(Order.desc("dateTransfer"));
        
        if(searchCriterias.containsKey("transferStatus")) {
            if((int)searchCriterias.get("transferStatus") == 1) {
                transferCriteria.add(Restrictions.eq("userSender", user));
            } else if ((int)searchCriterias.get("transferStatus") == 2) {
                transferCriteria.add(Restrictions.eq("userRecipient", user));
            }
        } else {
            transferCriteria.add(
                Restrictions.or(
                    Restrictions.eq("userSender", user),
                    Restrictions.eq("userRecipient", user)
                ));
        }
        if(searchCriterias.containsKey("contact")) {
            transferCriteria.add(
                Restrictions.or(
                    Restrictions.eq("userSender", searchCriterias.get("contact")),
                    Restrictions.eq("userRecipient", searchCriterias.get("contact"))
            ));
        }
        if(searchCriterias.containsKey("dateFrom") && searchCriterias.containsKey("dateTo")) {
            transferCriteria.add(Restrictions.between("dateTransfer", searchCriterias.get("dateFrom"), searchCriterias.get("dateTo")));
        } else if(searchCriterias.containsKey("dateFrom")) {
            transferCriteria.add(Restrictions.ge("dateTransfer", searchCriterias.get("dateFrom")));
        } else if(searchCriterias.containsKey("dateTo")) {
            transferCriteria.add(Restrictions.le("dateTransfer", searchCriterias.get("dateTo")));
        }
        
        return transferCriteria.list();
    }
}
