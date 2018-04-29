package model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class OrderDetails {
	private int oId;
	private String cName;
	private Date date;
	private Time time;
	private double total;
	private ArrayList<String> menu;
	private ArrayList<Integer> qty;
	private ArrayList<Double> unitPrice;
	private ArrayList<Double> subTotal;
	
	
	public OrderDetails(int oId, String cName, Date date, Time time, double total, ArrayList<String> menu,
			ArrayList<Integer> qty, ArrayList<Double> unitPrice, ArrayList<Double> subTotal) {
		super();
		this.oId = oId;
		this.cName = cName;
		this.date = date;
		this.time = time;
		this.total = total;
		this.menu = menu;
		this.qty = qty;
		this.unitPrice = unitPrice;
		this.subTotal = subTotal;
	}


	public int getoId() {
		return oId;
	}


	public void setoId(int oId) {
		this.oId = oId;
	}


	public String getcName() {
		return cName;
	}


	public void setcName(String cName) {
		this.cName = cName;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Time getTime() {
		return time;
	}


	public void setTime(Time time) {
		this.time = time;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public ArrayList<String> getMenu() {
		return menu;
	}


	public void setMenu(ArrayList<String> menu) {
		this.menu = menu;
	}


	public ArrayList<Integer> getQty() {
		return qty;
		
	}


	public void setQty(ArrayList<Integer> qty) {
		this.qty = qty;
	}


	public ArrayList<Double> getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(ArrayList<Double> unitPrice) {
		this.unitPrice = unitPrice;
	}


	public ArrayList<Double> getSubTotal() {
		return subTotal;
	}


	public void setSubTotal(ArrayList<Double> subTotal) {
		this.subTotal = subTotal;
	}
	
	
	
	
	
	
	
	
	
	
	

}
