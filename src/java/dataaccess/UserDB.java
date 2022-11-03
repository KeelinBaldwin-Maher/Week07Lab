package dataaccess;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.*;

public class UserDB {

    public ArrayList<User> getAllUsers() throws Exception {
        // Instantiate EntityManager
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            // Find all of the users in the database
            ArrayList<User> users = (ArrayList) em.createNamedQuery("User.findAll", User.class).getResultList();
            // Return list of users
            return users;

        } finally {
            em.close();
        }

    }

    public String findMatchingEmail(String email) throws Exception {
        // Instantiate EntityManager
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            // Find the user based on their email, if one exists
            User user = em.find(User.class, email);
            // Return the users email
            return user.getEmail();

        } finally {
            em.close();
        }

    }

    public User findUser(String email) throws Exception {
        // Instantiate EntityManager
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            // Find the user based on their email
            User user = em.find(User.class, email);
            // Return the users
            return user;

        } finally {
            em.close();
        }
    }

    public void insertNewUser(User user) throws Exception {
        // Instantiate EntityManager
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        // Instantitate EntityTransaction so DML can be executed
        EntityTransaction trans = em.getTransaction();

        try {
           // Transaction
           trans.begin();
           
           // Insert the user into the user table
           em.persist(user);
           
           // Roles have multiple users so this new user can be added to the respective role list
           // update the role table
           em.merge(user.getRole().getUserList().add(user));
           
           trans.commit();
           
        } catch (Exception ex) {
            // Rollback if there is an error
            trans.rollback();
            
        } finally {
            em.close();
        }
    }

    public void updateUser(User user) throws Exception {
        // Instantiate EntityManager
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        // Instantitate EntityTransaction so DML can be executed
        EntityTransaction trans = em.getTransaction();

        try {
           // Transaction
           trans.begin();
           
           // update the user into the user table
           em.merge(user);
           
           trans.commit();

        } catch (Exception ex) {
            // Rollback if there is an error
            trans.rollback();
            
        } finally {
            em.close();
        }
    }

    public void deleteUser(String email) throws Exception{
   // Instantiate EntityManager
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        // Instantitate EntityTransaction so DML can be executed
        EntityTransaction trans = em.getTransaction();

        try {
            // Find the user based on their email
            User user = em.find(User.class, email);
            
           // Transaction
           trans.begin();
           
           // Insert the user into the user table
           em.persist(user);
           
           // Roles have multiple users so this user can be deleted from the respective role list
           // update the role table
          em.remove(em.merge(user.getRole().getUserList().add(user)));
           
           trans.commit();
           
        } catch (Exception ex) {
            // Rollback if there is an error
            trans.rollback();
            
        } finally {
            em.close();
        }
    }
    
}
