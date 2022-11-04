package services;

import models.*;
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
            matchingEmail = new UserDB().findMatchingEmail(email);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        return email.equals(matchingEmail);
    }

    // Add a new user to the database
    public static void addUser(String email, String firstName, String lastName, String password, int roleID) {
        try {
            // Create a new user to be passed on to the database
            User user = new User(email, firstName, lastName, password);

            // Use the roleID to find the correct role
            Role role = RoleService.getRole(roleID);
            // Set the user's role
            user.setRole(role);
            //user.setRole(new Role(roleID, RoleService.findRoleName(roleID)));

            new UserDB().insertNewUser(user);

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
            // Get the user that already exists in the database using UserDB
            UserDB userDB = new UserDB();
            User user = userDB.findUser(email);

            // Set the new user data
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);

            // Determine if the role has changed
            if (roleID != user.getRole().getRoleId()) {
                // Set the new role from the role table through RoleService
                Role role = RoleService.getRole(roleID);
                user.setRole(role);
            }

            // Send the updated user to be saved to the database
            new UserDB().updateUser(user);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
