package lan.dev.data.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lan.dev.util.Constants;

public class MySQLDriver {
    private static MySQLDriver instance;
    private MySQLDriver() {
        
    }

    public static MySQLDriver getInstance() {
        if (instance == null) {
            synchronized (MySQLDriver.class) { 
                if (instance == null) {
                    instance = new MySQLDriver();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection(Constants.BD_URL, Constants.USER, Constants.PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); 
        }
        return conn;
    }
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Replace with logging
            }
        }
    }
}
