package managers;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;


import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class OrderLineManager {
	
	private Connection connection = null;
	private static SessionFactory sessionFactory;
	
	public OrderLineManager() {
//		sessionFactory = new Configuration().configure().buildSessionFactory();
		Configuration config = new Configuration().configure();
		ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		sessionFactory = config.buildSessionFactory(servReg);
	}
	


	
	public String addMenuOrder(int mId, int qty, double subTotal, int oId) throws ClassNotFoundException, SQLException {
		
		connection = dbConnection.DatabaseConnection.getDBConnection();
		
		String sql = "insert into orderline(mId,quantity,subTotal,oId) values(?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		
		pstmt.setInt(1, mId);
		pstmt.setInt(2, qty);
		pstmt.setDouble(3, subTotal);
		pstmt.setInt(4, oId);
		
		int exe = pstmt.executeUpdate();
		
		if (exe >= 1) {
			return "Order Menu has been saved successfully!";
		}
		else {
			return "Order Menu has not been saved!!!";
		}
		
		
	}
	
	public String haddMenuOrder(int mId, int qty, double subTotal, int oId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		SQLQuery insertQuery = session.createSQLQuery("insert into orderline(mId,quantity,subTotal,oId) values(?,?,?,?)");
		insertQuery.setParameter(0, mId);
		insertQuery.setParameter(1, qty);
		insertQuery.setParameter(2, subTotal);
		insertQuery.setParameter(3, oId);
		
		
		int exe = insertQuery.executeUpdate();
		
		session.getTransaction().commit();
		session.close();
		
		if (exe >= 1) {
			return "Order Menu has been saved successfully!";
		}
		else {
			return "Order Menu has not been saved!!!";
		}
	}
	

}
