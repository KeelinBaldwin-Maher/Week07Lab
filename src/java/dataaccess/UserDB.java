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
        String selectAllFromUser = 
                "SELECT * " + 
                "FROM user";
    
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
            // ensure that the connection, prepared statement and
            // the result set are closed
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            connectionPool.freeConnection(connection);
        }

        return users;
    }

}
