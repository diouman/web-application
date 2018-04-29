package managers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import dbConnection.DatabaseConnection;

public class OrderManager {
	
	private Connection connection = null;
	private static SessionFactory sessionFactory;
	
	public OrderManager() {
//		sessionFactory = new Configuration().configure().buildSessionFactory();
		Configuration config = new Configuration().configure();
		ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		sessionFactory = config.buildSessionFactory(servReg);
	}
	
	
	public String addOrder(int oId, Date date, Time time, double total, int cId) throws ClassNotFoundException, SQLException {
		
		connection = DatabaseConnection.getDBConnection();
		
		String sql = "insert into orders values (?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		
		pstmt.setInt(1, oId);
		pstmt.setDate(2, date);
		pstmt.setTime(3, time);
		pstmt.setDouble(4, total);
		pstmt.setInt(5, cId);
		
		int exe = pstmt.executeUpdate();
		
		if (exe >= 1) {
			return "Order has been saved successfully!";
		}
		else {
			return "Order has not been saved!!!";
		}
	}
	
	
	public int haddOrder(int oId, Date date, Time time, double total, int cId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		SQLQuery insertQuery = session.createSQLQuery("insert into orders values (?,?,?,?,?)");
		insertQuery.setParameter(0, oId);
		insertQuery.setParameter(1, date);
		insertQuery.setParameter(2, time);
		insertQuery.setParameter(3, total);
		insertQuery.setParameter(4, cId);
		
		int exe = insertQuery.executeUpdate();
		
		session.getTransaction().commit();
		session.close();
		
		if (exe >= 1) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	
	public int getLastOrderId() throws ClassNotFoundException, SQLException {
		connection = dbConnection.DatabaseConnection.getDBConnection();
		int oId = 0;
		
		String sql = "select max(oId) as maxId from orders";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			oId = rs.getInt("maxId");
		}
		
		return oId;
	}
	
	
	@SuppressWarnings("unchecked")
	public  int hgetLastOrderId() {
		Session session = sessionFactory.openSession();
		
		String sql = "select max(oId) as maxId from orders";
		SQLQuery query = session.createSQLQuery(sql);
		List<Integer> results = query.list();
		int oId = results.get(0);
		
		session.close();
		
		return oId;
	}

}
