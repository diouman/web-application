package managers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Customer;
import model.Graph;

public class GraphManager {
	
	Connection connection = null;
	
	public ArrayList<Graph> bestSellingMenu() throws ClassNotFoundException, SQLException{
		
		connection = dbConnection.DatabaseConnection.getDBConnection();
		ArrayList<Graph> graphs = new ArrayList<Graph>();
		
		String sql = "SELECT sum(quantity) as TotalQuantity ,mDesc  from orderline o \r\n" + 
				"inner join menu m \r\n" + 
				"on o.mId=m.mId\r\n" + 
				"GROUP by o.mId";
		Statement stmt = connection.createStatement();
		ResultSet rSet = stmt.executeQuery(sql);
		
		while(rSet.next()) {
			int sum = rSet.getInt("TotalQuantity");
			String menu = rSet.getString("mDesc");
			
			Graph graph = new Graph(sum, menu);
			graphs.add(graph);
		}
		
		connection.close();
		stmt.close();
		rSet.close();
		
		return graphs;
		
	}
	
	
public ArrayList<Graph> bestCustomer() throws ClassNotFoundException, SQLException{
		
		connection = dbConnection.DatabaseConnection.getDBConnection();
		ArrayList<Graph> graphs = new ArrayList<Graph>();
		
		String sql = "select cName , count(o.cId) as c from customer c\r\n" + 
				"inner join orders o \r\n" + 
				"on o.cId=c.cId\r\n" + 
				"GROUP by o.cId";
		Statement stmt = connection.createStatement();
		ResultSet rSet = stmt.executeQuery(sql);
		
		while(rSet.next()) {
			int sum = rSet.getInt("c");
			String cName = rSet.getString("cName");
			
			Graph graph = new Graph(cName,sum);
			graphs.add(graph);
		}
		
		connection.close();
		stmt.close();
		rSet.close();
		
		return graphs;
		
	}


public ArrayList<Graph> revenuePerCustomer() throws ClassNotFoundException, SQLException{
	
	connection = dbConnection.DatabaseConnection.getDBConnection();
	ArrayList<Graph> graphs = new ArrayList<Graph>();
	
	String sql = 
			"select cName , sum(total) as total from customer c\r\n" + 
			"inner join orders o \r\n" + 
			"on o.cId=c.cId\r\n" + 
			"GROUP by o.cId";
	Statement stmt = connection.createStatement();
	ResultSet rSet = stmt.executeQuery(sql);
	
	while(rSet.next()) {
		double sum = rSet.getInt("total");
		String cName = rSet.getString("cName");
		
		Graph graph = new Graph(cName,sum);
		graphs.add(graph);
	}
	
	connection.close();
	stmt.close();
	rSet.close();
	
	return graphs;
	
}
	

}
