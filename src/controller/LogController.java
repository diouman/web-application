package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.jboss.weld.util.LazyValueHolder.Serializable;

import managers.LoginManager;

@ManagedBean(name = "logController")
public class LogController  extends Serializable<Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		//instantiating objects
		LoginManager loginManager = new LoginManager();
		
	//creating variables 
		private String username;
		private String password;
		private String result;
			
	//getters and setters
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}

	//clear textboxes
		public String clearInputs() {
			setPassword(null);
			setUsername(null);
			return "success";
		}
		
	//validate username and password
		
		public String login() throws ClassNotFoundException, SQLException, IOException {
			result = loginManager.validateLogin(username, password);
			
			
	        
	        if (result.equals("Login Successful!!!")) {
	        	FacesContext context = FacesContext.getCurrentInstance();        
		        context.addMessage(null, new FacesMessage("Successful",   result) );
	        	context.getExternalContext().redirect("home.xhtml");
	        	return "home.xhtml";
	        }
	        else {
	        	FacesContext context = FacesContext.getCurrentInstance();        
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failure",   result) );
		        clearInputs();
	        	return("failer");
	        }
	        
	       
			
		}
		
		public String hLogin() throws IOException {
			int exe = loginManager.hValidateLogin(username, password);
			
			if (exe == 1) {
	        	FacesContext context = FacesContext.getCurrentInstance();        
		        context.addMessage(null, new FacesMessage("Successful",   result) );
	        	context.getExternalContext().redirect("home.xhtml");
	        	return "home.xhtml";
	        }
	        else {
	        	FacesContext context = FacesContext.getCurrentInstance();        
		        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Failure",   result) );
		        clearInputs();
	        	return("failer");
	        }
		}
		@Override
		protected Object computeValue() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public String redirect() throws IOException {
			System.out.println("redirecting---------------------------- ");
			FacesContext context = FacesContext.getCurrentInstance();        
			context.getExternalContext().redirect("adminlogin.xhtml");
        	return "adminlogin.xhtml";
		}
		
}
