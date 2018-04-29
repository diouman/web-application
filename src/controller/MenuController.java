package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.jboss.weld.util.LazyValueHolder.Serializable;

import managers.MenuManager;
import model.Menu;


public class MenuController extends Serializable<Object>{
	private static final long serialVersionUID = 1L;
	
	Client client = ClientBuilder.newClient();
	static String url = "http://localhost:8080/trio_web_service/webapi/menus";
	
	
	
//Instantiating objects

	MenuManager menuManager = new MenuManager();
	Menu menu = new Menu();
	
// 
	private ArrayList<Menu> filteredMenu = new ArrayList<>();
	
	
	
//get all menus from database
	
	public ArrayList<Menu> getFilteredMenu() {
		return filteredMenu;
	}


	public void setFilteredMenu(ArrayList<Menu> filteredMenu) {
		this.filteredMenu = filteredMenu;
	}


	public ArrayList<Menu> getAllMenus() throws ClassNotFoundException, SQLException{
		return menuManager.getAllMenus();
	}
	
	@SuppressWarnings("rawtypes")
	public List hgetAllMenus() {
		return menuManager.hgetAllMenus();
	}


	@Override
	protected Object computeValue() {
		// TODO Auto-generated method stub
		return null;
	}




}
