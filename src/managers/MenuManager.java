package managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jboss.weld.util.LazyValueHolder.Serializable;

import model.Menu;

public class MenuManager extends Serializable<Object>{
	
private static SessionFactory sessionFactory;

	public static void main(String[] args) {
		MenuManager manager = new MenuManager();
		manager.hupdateMenu(11, "pan", 55);
	}
	
//	@SuppressWarnings("deprecation")
	public MenuManager() {
//		sessionFactory = new Configuration().configure().buildSessionFactory();
		Configuration config = new Configuration().configure();
		ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		sessionFactory = config.buildSessionFactory(servReg);
	}
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	private ArrayList<Menu> selectedMenus = new ArrayList<Menu>();
	
//getters and setters
	public ArrayList<Menu> getSelectedMenus() {
		return selectedMenus;
	}


	public void setSelectedMenus(ArrayList<Menu> selectedMenus) {
		this.selectedMenus = selectedMenus;
	}
	

//get all menus
	public ArrayList<Menu> getAllMenus() throws ClassNotFoundException, SQLException{
		
		connection = dbConnection.DatabaseConnection.getDBConnection();
		ArrayList<Menu> menus = new ArrayList<Menu>();
		
		String sql = "select * from menu";
		Statement stmt = connection.createStatement();
		ResultSet rSet = stmt.executeQuery(sql);
		
		while(rSet.next()) {
			int mId = rSet.getInt("mId");
			String mDesc =rSet.getString("mDesc");
			double mPrice = rSet.getDouble("mPrice");
			
			
			Menu menu = new Menu(mId, mDesc, mPrice);
			menus.add(menu);
			
		}
		
		connection.close();
		stmt.close();
		rSet.close();
		
		return menus;
		
	}
	
	
	@SuppressWarnings("rawtypes")
	public List hgetAllMenus() {
		Session session = sessionFactory.openSession();
		String hql = "from Menu";
		Query query = session.createQuery(hql);		
		
		java.util.List results = query.list();	
		
		session.close();
		
		return results;
	}
	
	
	public int updateMenu(int mId,String desc, double price) throws ClassNotFoundException, SQLException {
		connection = dbConnection.DatabaseConnection.getDBConnection();
		String strCus="UPDATE menu SET mDesc=?,mPrice=? WHERE mId=?";
		PreparedStatement pstmt = connection.prepareStatement(strCus);
		pstmt.setString(1,desc);
		pstmt.setDouble(2, price);
		pstmt.setInt(3, mId);
		
		int exe=pstmt.executeUpdate();
		
		connection.close();
		pstmt.close();
		
		
		return exe;
		
	}
	
	public int hupdateMenu(int mId,String mDesc, double mPrice) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("UPDATE Menu SET mDesc= :mDesc,mPrice= :mPrice WHERE mId= :mId"); 
		query.setParameter("mId",mId);
		query.setParameter("mDesc", mDesc);
		query.setParameter("mPrice", mPrice);
		int result = query.executeUpdate();
		session.close();
		
		return result;
	}
	
	
	public int deleteMenu(int mId) throws ClassNotFoundException, SQLException {
		connection = dbConnection.DatabaseConnection.getDBConnection();
		String sql = "delete from menu where mId = ?";
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, mId);
		
		int exe=pstmt.executeUpdate();
		
		connection.close();
		pstmt.close();
		
		
		return exe;
		
	}
	
	public int hdeleteMenu(int mId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("delete from Menu where mId = :mId");
		query.setParameter("mId", mId);
		int result = query.executeUpdate();
		
		session.close();
		
		return result;
	}
	
	public int addMenu(String desc, double price) throws ClassNotFoundException, SQLException {
		connection = dbConnection.DatabaseConnection.getDBConnection();
		String strCus="INSERT INTO menu(mDesc,mPrice) VALUES(?,?)";
		PreparedStatement pstmt = connection.prepareStatement(strCus);
		pstmt.setString(1,desc);
		pstmt.setDouble(2, price);
		
		int exe=pstmt.executeUpdate();
		
		connection.close();
		pstmt.close();
		
		
		return exe;
	}

// increment get Incremented mID
	
	public int getLastMenuId() throws ClassNotFoundException, SQLException {
		connection = dbConnection.DatabaseConnection.getDBConnection();
		int mId = 0;
		
		String sql = "select max(mId) as maxId from menu";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			mId = rs.getInt("maxId");
		}
		
		return mId + 1;
	}
	
// search menu by Id
	 public  ArrayList<Menu> findMenu(int id) throws SQLException, ClassNotFoundException{
	    	
		 connection = dbConnection.DatabaseConnection.getDBConnection();
	     String findStr = "select * from menu where mId=" + id;
	     Statement stmt = connection.createStatement();
	     ResultSet rs =  stmt.executeQuery(findStr);
	    	
	    while(rs.next()) {
	    	
	    	int mId= rs.getInt("mId");
	    	String desc= rs.getString("mDesc");
	    	double price = rs.getDouble("mPrice");
	    	
	    	Menu menu = new Menu(mId, desc, price);
			selectedMenus.add(menu);
	    		
	    }
	    connection.close();
	    stmt.close();
	    rs.close();
		return selectedMenus;
			
	    }
	@Override
	protected Object computeValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
