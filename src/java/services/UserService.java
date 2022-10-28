package services;

import models.User;
import dataaccess.UserDB;
import java.util.*;

public class UserService {

    // Default constructor
    public UserService() {

    }

    // Retrieve a specific user
    public static User getUser(String email) {
        User user;
        try {
            user = new UserDB().findUser(email);
        } catch (Exception ex) {
            // Return null if the database could not be reached
            System.out.println(ex);
            user = null;
        }
        return user;
    }

    // Returns an ArrayList of all the users from the database and their roles
    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users;

        try {
            // Creates an ArrayList of users based on the data from userdb user table
            // The users are missing their role names
            users = new UserDB().getAllUsers();

            // User RoleService to determine the users role name
            for (User user : users) {
                // Determine the users role name using their role number 
                int userRoleNumber = user.getRole().getRoleID();
                String roleName = RoleService.findRoleName(userRoleNumber);

                // Set the users role name 
                user.getRole().setRoleName(roleName);
            }

        } catch (Exception ex) {
            // Return null if the database could not be reached
            System.out.println(ex);
            users = null;
        }

        return users;
    }

    // Determine if the email already exists in the database
    public static boolean inValidEmail(String email) {
        String matchingEmail = null;

        try {
            // find the matching email
            matchingEmail = new UserDB().findEmail(email);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return email.equals(matchingEmail);
    }

    // Add a new user to the database
    public static void addUser(String email, String firstName, String lastName, String password, int roleID) {
        try {
            new UserDB().insertNewUser(email, firstName, lastName, password, roleID);
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    // Delete a user from the database
     public static void deleteUser(String email) {
       try {
           new UserDB().deleteUser(email);
           
       } catch (Exception ex) {
           System.out.println(ex);
       }
    }
    
    // Update a current user in the database
    public static void updateUser(String email, String firstName, String lastName, String password, int roleID) {
       try {
           new UserDB().updateUser(email, firstName, lastName, password, roleID);
       } catch (Exception ex) {
           System.out.println(ex);
       }
        
    }

}
