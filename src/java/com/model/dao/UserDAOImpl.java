package com.model.dao;

import com.model.pojo.User;
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
public class UserDAOImpl implements UserDAO {
    private static Session session;
    private static Transaction transaction;
    
    
    @Override
    public User getUserById(int idUser) {
        User user = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User)session.get(User.class, idUser);
            
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
        
        return user;
    }
    
    @Override
    public List<User> getListUserById(int idUser) {
        List<User> userList = null;
        String hqlQuery = "SELECT u FROM User u WHERE u.idUser = :idUser";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            userList = session.createQuery(hqlQuery)
                    .setInteger("idUser", idUser)
                    .list();
            
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
        
        return userList;
    }
    
    @Override
    public User getUserByIdLogin(int idLogin) {
        User user = null;
        String hqlQuery = "SELECT u FROM User u WHERE u.idLogin = :idLogin";
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.createQuery(hqlQuery)
                    .setInteger("idLogin", idLogin)
                    .list().get(0);
            
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
        
        return user;
    }   

    @Override
    public void saveUser(User user) {        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            session.save(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            
            session.update(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
    }
}
