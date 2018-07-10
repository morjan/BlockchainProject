package com.model.dao;

import com.model.pojo.BalanceOperation;
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
public class BalanceOperationDAOImpl implements BalanceOperationDAO {
    private static Session session;
    private static Transaction transaction;
    
    
    @Override
    public List<BalanceOperation> getAllBalanceOperationByUser(int idUser) {
        List<BalanceOperation> balanceOperationLst;
        balanceOperationLst = new ArrayList<>();
        String hqlQuery = "SELECT bo FROM BalanceOperation bo WHERE bo.idUser = :idUser ORDER BY bo.dateOperation DESC";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            balanceOperationLst = session.createQuery(hqlQuery)
                    .setInteger("idUser", idUser)
                    .list();
            
            session.close();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        
        return balanceOperationLst;
    }
    
    @Override
    public List<BalanceOperation> getAllBalanceOperationByUserWithSearchCriterias(int idUser, HashMap<String, Object> searchCriterias) {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria balanceOperationCriteria = session.createCriteria(BalanceOperation.class)
                .add(Restrictions.eq("idUser", idUser))
                .addOrder(Order.desc("dateOperation"));        
        
        if(searchCriterias.containsKey("typeOperation")) {
            balanceOperationCriteria.add(Restrictions.eq("typeOperation", searchCriterias.get("typeOperation")));
        }
        if(searchCriterias.containsKey("dateFrom") && searchCriterias.containsKey("dateTo")) {
            balanceOperationCriteria.add(Restrictions.between("dateOperation", searchCriterias.get("dateFrom"), searchCriterias.get("dateTo")));
        } else if(searchCriterias.containsKey("dateFrom")) {
            balanceOperationCriteria.add(Restrictions.ge("dateOperation", searchCriterias.get("dateFrom")));
        } else if(searchCriterias.containsKey("dateTo")) {
            balanceOperationCriteria.add(Restrictions.le("dateOperation", searchCriterias.get("dateTo")));
        }
        if(searchCriterias.containsKey("amountMin") && searchCriterias.containsKey("amountMax")) {
            balanceOperationCriteria.add(Restrictions.between("amountOperation", searchCriterias.get("amountMin"), searchCriterias.get("amountMax")));
        } else if(searchCriterias.containsKey("amountMin")) {
            balanceOperationCriteria.add(Restrictions.ge("amountOperation", searchCriterias.get("amountMin")));
        } else if(searchCriterias.containsKey("amountMax")) {
            balanceOperationCriteria.add(Restrictions.le("amountOperation", searchCriterias.get("amountMax")));
        }        
        
        return balanceOperationCriteria.list();
    }
    
    @Override
    public void saveBalanceOperation(BalanceOperation balanceOperation) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            session.save(balanceOperation);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            transaction.rollback();
            System.err.println(e.getMessage());
        }
    }
}
