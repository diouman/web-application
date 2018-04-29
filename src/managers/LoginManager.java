package managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class LoginManager {
	private Connection connection = null;
	private static SessionFactory sessionFactory;
	
	public LoginManager()  {
//		sessionFactory = new Configuration().configure().buildSessionFactory();
		Configuration config = new Configuration().configure();
		ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		sessionFactory = config.buildSessionFactory(servReg);
	}
	
	public String validateLogin(String username, String password) throws ClassNotFoundException, SQLException { 
		connection = dbConnection.DatabaseConnection.getDBConnection();
		String sql = "select username, password from logins where username = ? and password = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		
		ResultSet rSet = pstmt.executeQuery();
		
		if(rSet.next()) {
			return "Login Successful!!!";
		}
		else {
			return "Username or Password incorrect!!!";
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public int hValidateLogin(String username,String password) {
		Session session = sessionFactory.openSession();
		String hql = "from Login where username = :username and password = :password";
		Query query = session.createQuery(hql);
		query.setString("username", username);
		query.setString("password", password);
		
		int exe = 0;
		
		java.util.List results = query.list();	
		
		if (results.size()>0) {
			exe = 1;
		}
		else {
			exe = 0;
		}
		
		session.close();
		
		return exe;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
