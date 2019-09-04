package com.bimalabogati.util;

import java.sql.*;
public class ConnectionUtil {
	// static method for creating the connection inside the Connection class
	// we want to make this static because we want to have only one method to create connection in entire application
    private static Connection conn = null; 
    
    public static Connection getConnection() {
        
        try {
        	
            String[] creds = System.getenv("DBPROPS").split(";");
            
            System.out.println("=======< Before Connection to the the DataBase ! >=======");
            Driver driver = new oracle.jdbc.OracleDriver();
            DriverManager.registerDriver(driver);
           // Connection conn = DriverManager.getConnection(url, user, password);
           // Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection(creds[0],creds[1],creds[2]);
//			DriverManager.getConnection(creds[0],creds[1],creds[2]);earlier had not saved stupid me !! 
            
            
            System.out.println("=======< After Connection to the DataBase >=======");
            DriverManager.deregisterDriver(driver);

            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return conn;
    }
}





/*
 * import java.sql.Connection;
 
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				/*
				 * In the old days, JDBC would require you to manually
				 * add the oracle ojdbc driver class at runtime.
				 * Class.forName will explicityly serach and load the
				 * detailed class at runtime, as opposed to Java
				 * naturally loading the class itself.
				 */
/*			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			String[] creds= System.getenv("DBPROPS").split(";");
			conn = DriverManager.getConnection(
					creds[0],
					creds[1],
					creds[2]
					);
			System.out.println("=====<CONNECTED>====");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
*/
