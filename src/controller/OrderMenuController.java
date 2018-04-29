package controller;


import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.weld.util.LazyValueHolder.Serializable;


import managers.CustomerManager;
import managers.OrderLineManager;
import managers.OrderManager;
import managers.OrderMenuManager;
import model.Menu;
import model.OrderDetails;
import model.OrderMenu;

public class OrderMenuController extends Serializable<Object>{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Instantiating objects to be used 
	OrderManager orderManager = new OrderManager();
	OrderLineManager oLineManager = new OrderLineManager();
	OrderMenu menu = new OrderMenu();
	CustomerController customerController = new CustomerController();
	
//Creating variables and arraylist to be used	
	private ArrayList<OrderMenu> cart = new ArrayList<OrderMenu>();
	private String result;
	private Date date;
	private Date time;
	private int cId;
	private String strDate;	
	private String strTime;
	private double total;
	private String cName;
	private ArrayList<String> errorMessages = new ArrayList<>();

	

//Setters and Getters of variables and arraylist created
	public ArrayList<OrderMenu> getCart() {
		return cart;
	}

	public void setCart(ArrayList<OrderMenu> cart) {
		this.cart = cart;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	

	public String getcName() {
		return cName;
	}
	
	public void setcName(String cName) {
		this.cName = cName;
	}
//getter setter for manual validation
	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
		try {
			this.date = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}	

	public String getStrTime() {
		return strTime;
	}

	public void setStrTime(String strTime) {
		this.strTime = strTime;
		try {
			DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			Date convertedtime = sdf.parse(strTime);
			this.time = convertedtime;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

//Check if selected menu already added in cart	
	private int isExisting(Menu m) {
		for(int i=0; i<this.cart.size();i++) {
			if (this.cart.get(i).getMenu().getmId() == m.getmId()) {
				return i;
			}
			
		}
		
		return -1;
	}

//Add menus to cart
	public String orderBy(Menu m) {		
		
		int index = isExisting(m);
		OrderMenu orderMenu = new OrderMenu();
			if (index == -1) {
				orderMenu = new OrderMenu(m, 1,m.getmPrice());			
				this.cart.add(orderMenu);			
				calcTotal();
				
			}
			else {
				int qty = this.cart.get(index).getQty() + 1;
				this.cart.get(index).setQty(qty);
				calcSubTotal(orderMenu);
				calcTotal();
				
				
			}
			FacesContext context = FacesContext.getCurrentInstance();        
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful",   m.getmDesc() + " Successfully added") );
	      
		return "cart?face-redirect=true";
	}
	
// Calculate subtotal
	public String calcSubTotal(OrderMenu m) {
		double price =0;	
		int qty = 0;
		price = m.getMenu().getmPrice();
		qty = m.getQty();
		double subtotal = qty * price;
		m.setSubTotal(subtotal);
		calcTotal();
		return "cId?face-redirect=true";
	}

// Calculate total
	
	public String calcTotal() {
		double total =0;
		
		for(int i=0; i<cart.size(); i++) {
			total += this.cart.get(i).getSubTotal();
		}
		this.total =total; 
		return "sucess"; 
	}
	
//delete item from cart
	public String delProd(OrderMenu m) {
		
		if(this.cart.remove(m)) {
			calcTotal();
			FacesContext context = FacesContext.getCurrentInstance();        
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful",   m.getMenu().getmDesc() + " Successfully Removed") );
	        			
			return null;
		}	
		else {
			return null;
		}
		
		
	}
	
// clear inputs
	public String clearInputs() {
		cart.clear();
		setDate(null);
		setTime(null);
		setcId(0);
		setTotal(0);
		menu.setQty(0);
		menu.setSubTotal(0);
		setcName(null);
		return "success";
	}
// cancel order
	public String cancelOrder() {
		clearInputs();
		if(clearInputs().equals("success")) {
			FacesContext context = FacesContext.getCurrentInstance();        
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful", "Order has been Cancelled") );
	        return null;
		}
		else {
			FacesContext context = FacesContext.getCurrentInstance();        
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Successful", "Failure to cancel Order") );
	        return null;
		}
	}
// save Order in databse
	
	public String addOrder() throws ClassNotFoundException, SQLException {
		validate();
		
		if(errorMessages.size() > 0 ) {
			for(int i=0; i<errorMessages.size();i++) {
				FacesContext context = FacesContext.getCurrentInstance();        
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",   errorMessages.get(i)) );
			}		
	        return null;
		}
		else {
			int oId = orderManager.hgetLastOrderId() + 1 ;
			menu.setoId(oId);
			calcTotal();
		
			
//Convert java.util.date to java.sql.Date and java.sql.Time respectively		
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			java.sql.Time sqlTime = new java.sql.Time(time.getTime());

			
//save order details in database		
			orderManager.haddOrder( oId, sqlDate,sqlTime,total,cId);
		
//save order Menus in database
			for(int i=0; i<cart.size(); i++) {
				int mId = this.cart.get(i).getMenu().getmId();
				int qty = this.cart.get(i).getQty();			
				double subTotal = (qty * this.cart.get(i).getMenu().getmPrice());
				
				result = oLineManager.haddMenuOrder(mId, qty, subTotal, oId);
			}
			
			FacesContext context = FacesContext.getCurrentInstance();        
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful",   result) );
	        clearInputs();
	        return null;
		}
	}


//generating orderId
	public String Oid() throws ClassNotFoundException, SQLException {
		int oId = orderManager.getLastOrderId() + 1 ;
		
		return Integer.toString(oId);
	}

//manually validating date and time field
	public String validate() {
		errorMessages = new ArrayList<String>();
		Date currentDate = new Date();
				
		
		
		if(date == null) {
			errorMessages.add("Date cannot be left blank");
		}
		else if(date.before(currentDate)) {
			errorMessages.add("Date cannot be smaller than current date i.e, " + currentDate);
		}
		
		
		if(time == null) {
			errorMessages.add("Time cannot be left blank");
		}
		
		if(cart.size() == 0) {
			errorMessages.add("Please order atleast one menu");
		}
		
		if (errorMessages.size() > 0) {
			return null;
		}
		else {
			return "success";
		}
	}



//getCustomer Name
	public String custName() throws ClassNotFoundException, SQLException {
		CustomerManager cManager = new CustomerManager();
		return cName = cManager.custName(cId);
		
	}

	@Override
	protected Object computeValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public ArrayList<OrderDetails> hgetAllOrder() {
		OrderMenuManager orderMenuManager = new OrderMenuManager();
		ArrayList<OrderDetails> results = orderMenuManager.getOrders();
		
		return results;
	}




}
