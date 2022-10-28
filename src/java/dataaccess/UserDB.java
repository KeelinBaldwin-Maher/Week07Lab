package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import models.*;

public class UserDB {

    // Connect to the database
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();
    // Used to send queries and updates to database
    PreparedStatement ps = null;
    // Used to read query results
    ResultSet rs = null;

    public ArrayList<User> getAllUsers() throws Exception {
        // SQL statement to retrieve all users
        String selectAllFromUser
                = "SELECT * "
                + "FROM user";

        // ArrayList to hold retrieved users
        ArrayList<User> users = new ArrayList<>();

        try {
            ps = connection.prepareStatement(selectAllFromUser);
            // Execute SELECT * FROM user;
            rs = ps.executeQuery();

            // Iterate through the retrieved rows
            while (rs.next()) {
                // Place row data into variables
                String email = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String password = rs.getString(4);
                Role role = new Role(rs.getInt(5));
                // Insantiate a user
                User user = new User(email, firstName, lastName, password, role);
                // Add user to users ArrayList
                users.add(user);
            }

        } finally {
            close();
        }

        return users;
    }

    public String findEmail(String email) throws Exception {
        // SQL statement to see if there is a matching email
        String findEmail
                = "SELECT email "
                + "FROM user "
                + "WHERE email = ? ;";

        String matchingEmail = null;

        try {
            ps = connection.prepareStatement(findEmail);
            ps.setString(1, email);
            // Execute SELECT;
            rs = ps.executeQuery();

            // Iterate through the retrieved row
            while (rs.next()) {
                matchingEmail = rs.getString(1);
            }

        } finally {
            close();
        }

        return matchingEmail;
    }

    public void insertNewUser(String email, String firstName, String lastName, String password, int roleID)
            throws Exception {
        // SQL statement for insertNewUser into user table
        String insert
                = "INSERT INTO user "
                + "VALUES (?, ?, ?, ?, ?)  ;";

        try {
            ps = connection.prepareStatement(insert);
            ps.setString(1, email);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, password);
            ps.setInt(5, roleID);
            // Execute INSERT
            ps.executeUpdate();

        } finally {
            close();
        }
    }

    public User findUser(String email) throws Exception {
        // SQL statement to find user based on email
        String findUser
                = "SELECT * "
                + "FROM user "
                + "WHERE email = ? ;";

        User user = null;

        try {
            ps = connection.prepareStatement(findUser);
            ps.setString(1, email);
            // Execute SELECT;
            rs = ps.executeQuery();

            // Iterate through the retrieved row
            while (rs.next()) {
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String password = rs.getString(4);
                Role role = new Role(rs.getInt(5));
                // Create user
                user = new User(email, firstName, lastName, password, role);
            }

        } finally {
            close();
        }

        return user;
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
    
    private void close() {
        // ensure that the connection, prepared statement and
        // the result set are closed
        DBUtil.closePreparedStatement(ps);
        connectionPool.freeConnection(connection);
        if (rs != null) {
            DBUtil.closeResultSet(rs);
        }
    }
    
}
