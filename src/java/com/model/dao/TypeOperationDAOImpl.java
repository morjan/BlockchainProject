package com.model.dao;

import com.model.pojo.TypeOperation;
import com.model.util.HibernateUtil;
import java.util.ArrayList;
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
public class TypeOperationDAOImpl implements TypeOperationDAO {
    private static Session session;
    private static Transaction transaction;
    
    
    @Override
    public TypeOperation getTypeOperationById(int idTypeOperation) {
        TypeOperation typeOperation = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            typeOperation = (TypeOperation)session.get(TypeOperation.class, idTypeOperation);
            session.close();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        
        return typeOperation;
    }
    
    @Override
    public List<TypeOperation> getAllTypeOperation() {
        List<TypeOperation> typeOperationLst;
        typeOperationLst = new ArrayList<>();
        String hqlQuery = "SELECT to FROM TypeOperation to";

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            typeOperationLst = session.createQuery(hqlQuery)
                    .list();
            
            session.close();
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        
        return typeOperationLst;
    }
}
