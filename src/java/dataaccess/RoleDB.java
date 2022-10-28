package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import models.Role;

public class RoleDB {
    // Connect to the database

    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();
    // Used to send queries and updates to database
    PreparedStatement ps = null;
    // Used to read query results
    ResultSet rs = null;

    // Retrieve all rows from userdb role table
    public ArrayList<Role> getAllRoleData() throws Exception {
        // SQL statement to retrieve all roles
        String selectAllRoleData
                = "SELECT * "
                + "FROM role;";

        // ArrayList to hold retrieved roles
        ArrayList<Role> roles = new ArrayList<>();

        try {
            ps = connection.prepareStatement(selectAllRoleData);
            // Execute SELECT * FROM user;
            rs = ps.executeQuery();

            // Iterate through the retrieved rows
            while (rs.next()) {
                // Place row data into variables
                int roleID = rs.getInt(1);
                String roleName = rs.getString(2);
                // Insantiate a role
                Role role = new Role(roleID, roleName);
                // Add role to roles ArrayList
                roles.add(role);
            }

        } finally {
            // ensure that the connection, prepared statement and
            // the result set are closed
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            connectionPool.freeConnection(connection);
        }

        return roles;
    }

    // Retrieve all role names from userdb role table
    public ArrayList<String> getAllRoleNames() throws Exception {
        // SQL statement to retrieve all role names
        String selectAllRoleNames
                = "SELECT role_name "
                + "FROM role;";

        // ArrayList to hold retrieved role names
        ArrayList<String> roleNames = new ArrayList<>();

        try {
            ps = connection.prepareStatement(selectAllRoleNames);
            // Execute SELECT statement
            rs = ps.executeQuery();

            // Iterate through the retrieved rows
            while (rs.next()) {
                // Place role names in ArrayList
                roleNames.add(rs.getString(1));
            }

        } finally {
            // ensure that the connection, prepared statement and
            // the result set are closed
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            connectionPool.freeConnection(connection);
        }
        return roleNames;
    }

    
    
    public String getRoleName(int roleID) throws Exception{
        // SQL statement to retrieve role name
        String selectRoleName
                = "SELECT role_name FROM role "
                 + "WHERE role_id = ?;";

        // String to hold retrieved name
        String roleName;
        
        try {
            // Select the role name that matches the roleID
            ps = connection.prepareStatement(selectRoleName);
            ps.setInt(1, roleID);
            // Execute SELECT statement
            rs = ps.executeQuery();
            
            // Setup iterator
            rs.next();
            // Set roleName to the retireved name
            roleName = rs.getString(1);
                       
        } finally {
            // ensure that the connection, prepared statement and
            // the result set are closed
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            connectionPool.freeConnection(connection);
        }

        return roleName;
    }

}
