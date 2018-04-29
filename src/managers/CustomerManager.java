package managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import model.Customer;

public class CustomerManager {
	
	Connection connection = null;
	private static SessionFactory sessionFactory;
	
//	@SuppressWarnings("deprecation")
	public CustomerManager() {
//		sessionFactory = new Configuration().configure().buildSessionFactory();
		Configuration config = new Configuration().configure();
		ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		sessionFactory = config.buildSessionFactory(servReg);
	}
	
	
	public ArrayList<Customer> getAllCustomers() throws ClassNotFoundException, SQLException{
		
		connection = dbConnection.DatabaseConnection.getDBConnection();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		String sql = "select * from customer";
		Statement stmt = connection.createStatement();
		ResultSet rSet = stmt.executeQuery(sql);
		
		while(rSet.next()) {
			int cId = rSet.getInt("cId");
			String cName = rSet.getString("cName");
			String cAddress = rSet.getString("cAddress");
			String gender = rSet.getString("cGender");
			int phone = rSet.getInt("cPhone");
			
			Customer customer = new Customer(cId, cName, cAddress, gender, phone);
			customers.add(customer);
		}
		
		connection.close();
		stmt.close();
		rSet.close();
		
		return customers;
		
	}
	
	
	@SuppressWarnings("rawtypes")
	public List hgetAllCustomers() {
		Session session = sessionFactory.openSession();
		String hql = "from Customer";
		Query query = session.createQuery(hql);
		
		java.util.List results = query.list();	
		
		session.close();
		
		return results;
	}
		
	
	
	public LinkedHashMap<String  , Object> getList() throws ClassNotFoundException, SQLException{
		int id =0;
		String cName ="";
		
		
		LinkedHashMap<String, Object> list = new LinkedHashMap<String,Object>();
		Connection connection = dbConnection.DatabaseConnection.getDBConnection();
		
		String sql ="select * from customer";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			cName = rs.getString("cName");
			id = rs.getInt("cId");
			
			list.put(cName, id);
		}
		connection.close();
		stmt.close();
		rs.close();
		
		return list;
		
	}
	
	
	public int addCustomers(String name, String address, String gender, int phone) throws ClassNotFoundException, SQLException {
		connection = dbConnection.DatabaseConnection.getDBConnection();
		String strCus="INSERT INTO customer(cName,cAddress,cGender,cPhone) VALUES(?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(strCus);
		pstmt.setString(1,name);
		pstmt.setString(2, address);
		pstmt.setString(3, gender);
		pstmt.setInt(4, phone);
		
		int exe=pstmt.executeUpdate();
		
		connection.close();
		pstmt.close();
		
		
		return exe;
	}
	
	
	public void haddCustomers(Customer myCustomer) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(myCustomer);
		tx.commit();
		session.close();
	}

	public int updateCustomer(int cId,String address, int phone) throws ClassNotFoundException, SQLException {
		connection = dbConnection.DatabaseConnection.getDBConnection();
		String strCus="UPDATE customer SET cAddress=?,cPhone=? WHERE cId=?";
		PreparedStatement pstmt = connection.prepareStatement(strCus);
		pstmt.setString(1,address);
		pstmt.setInt(2, phone);
		pstmt.setInt(3, cId);
		
		int exe=pstmt.executeUpdate();
		
		connection.close();
		pstmt.close();
		
		
		return exe;
		
	}
	
	public void hupdateCustomer(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
//		Customer myCustomer = (Customer) session.get(Customer.class);
//		System.out.println("=------------------------ " + myCustomer.getcAddress());
		session.update(customer);	
		System.out.println("=------------------------ " + customer.getcAddress());
		tx.commit();
		session.close();
	}
	
	public ArrayList<Customer> selectedCustomer (int id) throws ClassNotFoundException, SQLException{
		connection = dbConnection.DatabaseConnection.getDBConnection();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		String sql ="select * from customer where cId = ? ";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, id);
		
		ResultSet rSet = pstmt.executeQuery();
		
		while (rSet.next()) {
			int cId = rSet.getInt("cId");
			String cName = rSet.getString("cName");
			String cAddress = rSet.getString("cAddress");
			String gender = rSet.getString("cGender");
			int phone = rSet.getInt("cPhone");
			
			Customer customer = new Customer(cId, cName, cAddress, gender, phone);
			customers.add(customer);
		}
		
		connection.close();
		pstmt.close();
		rSet.close();
		
		return customers;
	}
	
	public int deleteCustomer(int cId) throws ClassNotFoundException, SQLException {
		connection = dbConnection.DatabaseConnection.getDBConnection();
		String sql = "delete from customer where cId = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, cId);
		
		int exe=pstmt.executeUpdate();
		
		connection.close();
		pstmt.close();
		
		
		return exe;
		
		
	}
	
	public void hdeleteCustomer(int custId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Customer myCustomer = (Customer) session.get(Customer.class, custId);
		session.delete(myCustomer);		
		tx.commit();
		session.close();
	}
	
	public String custName(int cId) throws ClassNotFoundException, SQLException {
		connection = dbConnection.DatabaseConnection.getDBConnection();
		String sql = "select cName from customer where cId = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, cId);
		
		ResultSet rSet = pstmt.executeQuery();
		String cName = "";
		while (rSet.next()) {
			
			cName = rSet.getString("cName");
			
		}
		
		connection.close();
		pstmt.close();
		rSet.close();
		
		return cName;
	}

}
