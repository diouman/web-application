package dbConnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static String username ="root";
	private static String password ="root";
	private static String dburl ="jdbc:mysql://localhost:3306/triorestaurant";
	private static Connection conn = null;
	
	public static Connection getDBConnection() throws ClassNotFoundException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl,username,password);			
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	

}
