package controller;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.LinkedHashMap;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.weld.util.LazyValueHolder.Serializable;


import managers.CustomerManager;
import model.Customer;

public class CustomerController extends Serializable<Object> {
	
	Client client = ClientBuilder.newClient();
	static String url = "http://localhost:8080/trio_web_service/webapi/customers";
	
	
	private static final long serialVersionUID = 1L;

	//instantiating objects
	CustomerManager customerManager = new CustomerManager();

//variables 
	private int cId;
	private String cName;
	private String cAddress;
	private String cGender;
	private int cPhone;
	private int[] custId;
	private Boolean update;
	private String result;
	
	
	
	public CustomerController() {
			
	}


	//getter setter	
	public CustomerManager getCustomerManager() {
		return customerManager;
	}


	public void setCustomerManager(CustomerManager customerManager) {
		this.customerManager = customerManager;
	}


	
	public int getcId() {
		return cId;
	}


	public void setcId(int cId) {
		this.cId = cId;
	}


	public String getcName() {
		return cName;
	}


	public void setcName(String cName) {
		this.cName = cName;
	}


	public String getcAddress() {
		return cAddress;
	}


	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}


	public String getcGender() {
		return cGender;
	}


	public void setcGender(String gender) {
		this.cGender = gender;
	}


	public int getcPhone() {
		return cPhone;
	}


	public void setcPhone(int cPhone) {
		this.cPhone = cPhone;
	}
	
	public int[] getCustId() {
		return custId;
	}


	public void setCustId(int[] custId) {
		this.custId = custId;
	}
	
	public Boolean getUpdate() {
		return update;
	}


	public void setUpdate(Boolean update) {
		this.update = update;
	}
	
	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}
	

//retrieve all customers
//	public ArrayList<Customer> getAllCustomers() throws ClassNotFoundException, SQLException{
//		return customerManager.getAllCustomers();
//	}
//	
//	@SuppressWarnings("rawtypes")
//	public List hgetAllCustomers() {
//		return customerManager.hgetAllCustomers();
//	}

//retrive customer name and id to use in selectMenu	
//	public LinkedHashMap<String  , Object> getLists() throws ClassNotFoundException, SQLException{
//		return customerManager.getList();
//	}
	
//add customer to database	
//	public String addCustomer() throws ClassNotFoundException, SQLException {
//		int exe = customerManager.addCustomers(cName, cAddress, cGender, cPhone);	
//		
//	    if(exe>=1) {
//	    	FacesContext context = FacesContext.getCurrentInstance();        
//		    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Customer Added Successfully") );
//		    clear();
//		    return null;
//	    }
//	    else {
//	    	FacesContext context = FacesContext.getCurrentInstance();        
//		    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failure","Customer not Added") );
//		    return null;
//	    }
//		
//		
//		
//	}
//	
//
//	public String haddCustomer() {
//		Customer customer = new Customer(cName, cAddress, cGender, cPhone);
//		
//		try {
//			customerManager.haddCustomers(customer);
//			FacesContext context = FacesContext.getCurrentInstance();        
//		    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Customer Added Successfully") );
//		    clear();
//		    return null;
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//			FacesContext context = FacesContext.getCurrentInstance();        
//		    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failure","Customer not Added") );
//		    return null;
//		}
//						
//	}
	

	public void  populate(int custId) throws ClassNotFoundException, SQLException {
		
		if (disabled) {
	        disabled = false;
	    } else {
	        disabled = true;
	    }
		
		this.cId = custId;
		ArrayList<Customer> customers = customerManager.selectedCustomer(this.cId);
		this.cAddress = customers.get(0).getcAddress();
		this.cId = customers.get(0).getcId();
		this.cName = customers.get(0).getcName();
		this.cPhone = customers.get(0).getcPhone();
		this.cGender = customers.get(0).getcGender();
		
	}
	
	
//update customer
//	public String updateCustomer() throws ClassNotFoundException, SQLException {
//		
//		int exe = customerManager.updateCustomer(this.cId,this.cAddress,this.cPhone);
//		if(exe >=1) {
//			FacesContext context = FacesContext.getCurrentInstance();        
//		    context.addMessage(null, new FacesMessage("Success","Customer Updated Successfully") );
//		        
//			clear();
//			return null;
//		}
//		else {
//			return null;
//		}
//		
//		
//	}
//	
//	public String hupdateCustomer() {
//		Customer myCustomer = new Customer(cId,cName, cAddress, cGender, cPhone);
//		try {
//			customerManager.hupdateCustomer(myCustomer);
//			FacesContext context = FacesContext.getCurrentInstance();        
//		    context.addMessage(null, new FacesMessage("Success","Customer Updated Successfully") );
//		        
//			clear();
//			return null;
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//			FacesContext context = FacesContext.getCurrentInstance();        
//		    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failure","Customer not updated") );
//		    return null;
//		}
//		
//	}

//return that customer whose id is selected 
	public ArrayList<Customer> getSelectedCustomer() throws ClassNotFoundException, SQLException{
		return customerManager.selectedCustomer(cId);
	}

//delete that customer whose id is selected
//	public String deleteCustomer() throws ClassNotFoundException, SQLException {
//		int deleted =0;
//		for(int i = 0 ; i<custId.length; i++) {
//			deleted = customerManager.deleteCustomer(custId[i]);
//		}
//		
//
//		if (deleted>=1) {
//			FacesContext context = FacesContext.getCurrentInstance();        
//	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful",  " Customer successfully deleted") );
//	        
//			return null;
//		}
//		else {
//			FacesContext context = FacesContext.getCurrentInstance();        
//		    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failure","Customer Not Deleted") );
//		        
//			return null;
//		}
//		
//	}
	
//	public String hdeleteCustomer() {
////		Customer myCustomer = new Customer(cName, cAddress, cGender, cPhone);
//		try {
//			System.out.println("---------------------- customer id" + custId[0]);
//			customerManager.hdeleteCustomer(custId[0]);
//			FacesContext context = FacesContext.getCurrentInstance();        
//	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Successful",  " Customer successfully deleted") );
//	             
//			clear();
//			return null;
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.getMessage();
//			FacesContext context = FacesContext.getCurrentInstance();        
//		    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failure","Customer Not Deleted") );
//		    return null;
//		}
//		
//	}


//clear textboxes
	public void clear() {
		setcAddress(null);
		setcPhone(0);
		setcName(null);
		setcGender(null);
		setcId(0);
		
		if (disabled) {
	        disabled = false;
	    } else {
	        disabled = true;
	    }
	}


//custom validation

	public void validateTel(FacesContext context, UIComponent uiComponent, Object value)throws ValidatorException {
		int tel = ((Integer) value).intValue();		
		int length = (int) Math.log10(tel) + 1;
			
		if(length <= 6 ) {
			FacesMessage message = new FacesMessage("Telephone number cannot consist of less than 7 digits");
			throw new ValidatorException(message);
		}
		else if(length >= 9) {
			FacesMessage message = new FacesMessage("Telephone number cannot consist of more than 8 digits");
			throw new ValidatorException(message);
		}

	}
	
	public void validateAddress(FacesContext context, UIComponent uiComponent, Object value)throws ValidatorException {
		String address = value.toString();
		if(!address.matches("^[a-zA-Z_ ]+")) {
			FacesMessage message = new FacesMessage("Address cannot have special characters");
			throw new ValidatorException(message);
		}
	}


	@Override
	protected Object computeValue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public ArrayList<Customer> getCustomers() {
		ArrayList<Customer> customers = client
				.target(url)
				.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<ArrayList<Customer>>() {});
		return customers;
	}
	
	public LinkedHashMap<String  , Object> getList() {		
		
		LinkedHashMap<String, Object> list = new LinkedHashMap<String,Object>();
		ArrayList<Customer> customers = this.getCustomers();
		
		for (int i = 0; i < customers.size(); i++) {
			list.put(customers.get(i).getcName(), customers.get(i).getcId());
		}
		
		return list;
		
	}
	
	public String addCustomer() {
		Customer customer = new Customer(cName, cAddress, cGender, cPhone);
		Response response = client
				.target(url)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(customer, MediaType.APPLICATION_JSON));
		
		FacesContext context = FacesContext.getCurrentInstance();
		if(response.getStatus() == 200) {	        
		    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Customer Added Successfully") );
		    clear();
		    return null;
	    }
	    else {	    	      
		    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failure","Customer not Added") );
		    return null;
	    }
		
		   
	}
	
	public  String updateCustomer() {
//		
//		cAddress = ((Customer) event.getObject()).getcAddress();
//	  	cPhone = ((Customer) event.getObject()).getcPhone();
//	  	cName = ((Customer) event.getObject()).getcName();
//	  	cGender = ((Customer) event.getObject()).getcGender();
//	  	cId = ((Customer) event.getObject()).getcId();
		
		
	  	System.out.println("id------------------------------" + cId );
		Customer customer = new Customer(cName, cAddress, cGender, cPhone);
		
		Response response = client
				.target(url)
				.path("{custId}")
				.resolveTemplate("custId", cId)				
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(customer, MediaType.APPLICATION_JSON));
		FacesContext context = FacesContext.getCurrentInstance(); 
		
		if(response.getStatus() == 200) {			       
		    context.addMessage(null, new FacesMessage("Success","Customer Updated Successfully") );
		        
			clear();
			return null;
		}
		else {
			context.addMessage(null, new FacesMessage("Failure","Customer not Updated!!!") );
			  
			return null;
		}
		
	}
	
	public String deleteCustomer(int cuId) {
		Response response = client
				.target(url)
				.path("/{custId}")
				.resolveTemplate("custId", cuId)				
				.request(MediaType.APPLICATION_JSON)
				.delete();
		
		System.out.println("chosen customer id---------------------------- " + cuId);
		
		FacesContext context = FacesContext.getCurrentInstance(); 		
		if(response.getStatus() == 200) {			       
		    context.addMessage(null, new FacesMessage("Success","Customer deleted Successfully") );
		        
			clear();
			return null;
		}
		else {
			context.addMessage(null, new FacesMessage("Failure","Customer not deleted!!!") );
			  
			return null;
		}

		
	}
	
	
//	  public void onRowEdit(RowEditEvent event) {
//		  
//		  	FacesMessage msg = new FacesMessage("Car Edited", ((Customer) event.getObject()).getcAddress());
//	        FacesContext.getCurrentInstance().addMessage(null, msg);
//		  	
////		  	cAddress = ((Customer) event.getObject()).getcAddress();
////		  	cPhone = ((Customer) event.getObject()).getcPhone();
////		  	cName = ((Customer) event.getObject()).getcName();
////		  	cGender = ((Customer) event.getObject()).getcGender();
//		  	cId = ((Customer) event.getObject()).getcId();
//		  	
//		  	System.out.println("address=--------------------- " + cAddress);
//		  	
//		  	this.updateCustomer();
//		  	
//	       
//	    }
//	     
//	    public void onRowCancel(RowEditEvent event) {
//	        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Customer) event.getObject()).getcAddress());
//	        FacesContext.getCurrentInstance().addMessage(null, msg);
//	    }
	
	
	private boolean disabled;

	public boolean isDisabled() {
	    return disabled;
	}

	public void setDisabled(boolean disabled) {
	    this.disabled = disabled;
	}

	public void anyMethod() {
	    if (disabled) {
	        disabled = false;
	    } else {
	        disabled = true;
	    }
	}
	     

	
	
	

}
