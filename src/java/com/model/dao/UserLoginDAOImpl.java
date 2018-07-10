package com.model.dao;

import com.model.pojo.UserLogin;
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
public class UserLoginDAOImpl implements UserLoginDAO {
    private static Session session;
    private static Transaction transaction;
    
    
    @Override
    public UserLogin getLoginByIdLogin(int idLogin) {
        UserLogin userLogin = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            userLogin = (UserLogin)session.get(UserLogin.class, idLogin);
            
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
        
        return userLogin;
    }
    
    @Override
    public List<UserLogin> getLoginByEmail(String email) {
        List<UserLogin> userLoginList = null;
        String hqlQuery = "SELECT l FROM UserLogin l WHERE l.email = :email";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            userLoginList = session.createQuery(hqlQuery)
                    .setString("email", email)
                    .list();
            
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
        
        return userLoginList;
    }
    
    @Override
    public List<UserLogin> getLoginByEmailAndPassword(String email, String password) {
        List<UserLogin> userLoginList = null;
        String hqlQuery = "SELECT l FROM UserLogin l WHERE l.email = :email AND l.password = :password";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            userLoginList = session.createQuery(hqlQuery)
                    .setString("email", email)
                    .setString("password", password)
                    .list();
            
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
        
        return userLoginList;
    }
    
    @Override
    public UserLogin saveLogin(UserLogin userLogin) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            session.save(userLogin);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
        
        return userLogin;
    }
    
    @Override
    public void updateUserLogin(UserLogin userLogin) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            session.update(userLogin);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
    }
}
