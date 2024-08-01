package pageObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtility {
	
	 private static final String JDBC_URL = "jdbc:sqlserver://USTR-MVM:3868/Civil Registry System";
	    private static final String JDBC_USER = "PSATestTeam";
	    private static final String JDBC_PASSWORD = "PSATestTeam*Live";

	    public static void getConnection() throws SQLException {
	        Connection con= DriverManager.getConnection("jdbc:sqlserver://USTR-MVM:3868/Civil Registry System", "PSATestTeam", "PSATestTeam*Live");
	        if(con.isClosed()) {
	        	System.out.println("Jdbc not connected");
	        }
	        else {
	        	System.out.println("Jdbc is connected");
	        }
	    }
}
//	    public static ResultSet executeQuery(String query) throws SQLException {
//	        Connection connection = getConnection();
//	        Statement statement = connection.createStatement();
//	        return statement.executeQuery(query);
//	    }
//
//	    public static void closeConnection(Connection connection) {
//	        if (connection != null) {
//	            try {
//	                connection.close();
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	    }
//	}


