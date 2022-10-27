package dataaccess;

// Based on the Week 7 slides
import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    private ConnectionPool() {
        try {
            // Access to context.xml
            InitialContext initalContext = new InitialContext();
            // Look for the environment variable with the name jdbc/userdb
            dataSource = (DataSource) initalContext.lookup("java:/comp/env/jdbc/userdb");
            
        } catch (NamingException ex) {
            System.out.println(ex);
        }
    }
    
    // Make sure that only one connection pool can exist at any time
    public static synchronized ConnectionPool getInstance() {
        // Create an instance of a connection pool if none exist
        if (pool == null) {
            pool = new ConnectionPool();
        }
           return pool;
       }
    
    // Connect to database
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    // Return connection to pool
    public void freeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
