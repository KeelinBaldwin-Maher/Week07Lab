package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Based on Week 7 demo 
public class DBUtil {

    // Close prepared statement
    public static void closePreparedStatement(Statement ps) {
        try {
            // Only close prepared statement if argument is valid
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    // Close result set
    public static void closeResultSet(ResultSet rs) {
        try {
             // Only close result set if argument is valid
             if (rs != null) {
                 rs.close();
             }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
