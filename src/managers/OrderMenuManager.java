package managers;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;



import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import model.OrderDetails;



public class OrderMenuManager {
	
private static SessionFactory sessionFactory;
	
	public OrderMenuManager() {
//		sessionFactory = new Configuration().configure().buildSessionFactory();
		Configuration config = new Configuration().configure();
		ServiceRegistry servReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		sessionFactory = config.buildSessionFactory(servReg);
	}
	
	
	public ArrayList<OrderDetails> getOrders() {
		Session session = sessionFactory.openSession();
		SQLQuery sqlQuery = session.createSQLQuery("select o.oId,c.cName,o.date,o.time,o.total,m.mDesc,od.quantity,m.mPrice,od.subTotal from orders o \r\n" + 
				"inner JOIN orderline od\r\n" + 
				"on o.oId = od.oId\r\n" + 
				"inner join menu m \r\n" + 
				"on m.mId = od.mId\r\n" + 
				"inner join customer c \r\n" + 
				"on c.cId = o.cId \r\n" + 
				"order by o.oId ASC;");
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = sqlQuery.list();
		
		ArrayList<OrderDetails> orderDetails = new ArrayList<>();
		OrderDetails oDetails;
		
		int oId;
		String cName;
		Date date;
		Time time;
		double total;
		ArrayList<String> menu;
		ArrayList<Integer> qty;
		ArrayList<Double> unitPrice;
		ArrayList<Double> subTotal;
		
		
		for (int i = 0; i < results.size(); i++) {
			menu = new ArrayList<>();
			qty= new ArrayList<>();
			unitPrice = new ArrayList<>();
			subTotal = new ArrayList<>();
			
			int currentOrderId = (int) results.get(i)[0];
			oId = currentOrderId;
			cName = (String) results.get(i)[1];
			date = (Date) results.get(i)[2];
			time = (Time) results.get(i)[3];
			total = (Double) results.get(i)[4];
			menu.add((String)results.get(i)[5]);
			qty.add((int)results.get(i)[6]);
			unitPrice.add((Double) results.get(i)[7]);
			subTotal.add((Double) results.get(i)[8]);
			
			for (int j = i ; j < results.size(); j++) {
				System.out.println("---------------------- " + currentOrderId + "       " + (int)results.get(j)[0] );
				
				if ((int)results.get(j)[0] == currentOrderId) {
					System.out.println("same ---------------------- " + currentOrderId + "       " + (int)results.get(j)[0] );
					menu.add((String)results.get(j)[5]);
					qty.add((int)results.get(j)[6]);
					unitPrice.add((Double) results.get(j)[7]);
					subTotal.add((Double) results.get(j)[8]);
					
					results.remove(j);
				}
			}
			
			
			
			oDetails = new OrderDetails(oId, cName, date, time, total, menu, qty, unitPrice, subTotal);
			orderDetails.add(oDetails);			
			
		}
		
		
		
//		for (Object[] result : results) {			
//			System.out.println(result[0] + " " + result[1] + " - " + result[2] + " " + result[3] + " " + result[4] + " - " + result[5] + " " + result[6] + " " + result[7] + " - " + result[8]);
//				
//			oDetails = new OrderDetails((int)result[0], result[1].toString(), (Date)result[2], (Time)result[3], (Double)result[4], result[5].toString(),(int) result[6], (Double)result[7], (Double)result[8]);
//			orderDetails.add(oDetails);
//		}
//		
//		session.getTransaction().commit();
		session.close();
		return orderDetails;
		
	}
	
	

}
