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

    public void insertNewUser(User user)
            throws Exception {
        // Instantiate EntityManager
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        // Instantitate EntityTransaction so DML can be executed
        EntityTransaction trans = em.getTransaction();

        try {
            
           user.getRole().getUserList().add(user);
           
           // Transaction
           trans.begin();
           
           // Insert the user into the user table
           em.persist(user);
           
           // Roles have multiple users so this new user can be added to the respective role list
           // update the role table
           em.merge(user.getRole().getUserList().add(user));
           
        } catch (Exception ex) {
            // Rollback if there is an error
            trans.rollback();
            
        } finally {
            em.close();
        }
    }

    public void updateUser(String email, String firstName, String lastName, String password, int roleID)
            throws Exception {
        // SQL statement to update user based on email
        String updateUser
                = "UPDATE user "
                + "SET "
                + "first_name = ?, "
                + "last_name = ?, "
                + "password = ?, "
                + "role = ? "
                + "WHERE email = ? ;";

        try {
            ps = connection.prepareStatement(updateUser);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, password);
            ps.setInt(4, roleID);
            ps.setString(5, email);
            // Execute UPDATE;
            ps.executeUpdate();

        } finally {
            close();
        }
    }

    public void deleteUser(String email) throws Exception{
        // SQL statement to delete user based on email
        String deletUser = 
                "DELETE FROM user "
                + "WHERE email = ?;";

        try {
            ps = connection.prepareStatement(deletUser);
            ps.setString(1, email);
            // Execute DELETE;
            ps.executeUpdate();

        } finally {
            close();
        }
        
    }
    
}
