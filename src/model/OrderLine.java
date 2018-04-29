package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderLine {
	
	private int oLId;
	private int mId;
	private int quantity;
	private double subtotal;
	private int oId;
	
	
	public OrderLine(int oLId, int mId, int quantity, double subtotal, int oId) {
		super();
		this.oLId = oLId;
		this.mId = mId;
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.oId = oId;
	}
	
	@Id
	public int getoLId() {
		return oLId;
	}
	public void setoLId(int oLId) {
		this.oLId = oLId;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
	}
	
	
	
}
