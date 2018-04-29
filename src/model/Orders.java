package model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Orders {

	private int oId;
	private Date date;
	private Time time;
	private double total;
	private int cId;
	
	public Orders(int oId, Date date, Time time, double total, int cId) {
		super();
		this.oId = oId;
		this.date = date;
		this.time = time;
		this.total = total;
		this.cId = cId;
	}
	
	
	@Id
	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
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
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	
	
	
}
