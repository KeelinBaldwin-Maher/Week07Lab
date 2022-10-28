package services;

import models.User;
import dataaccess.UserDB;
import java.util.*;

public class UserService {

    // Default constructor
    public UserService() {

    }

    // Retrieve a specific user
    public static User getUser() {
        return null;
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

}
