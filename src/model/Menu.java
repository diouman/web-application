package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Menu {
	
	private int mId;
	private String mDesc;
	private double mPrice;
	private boolean mDiscount;
	
	public Menu(int mId, String mDesc, double mPrice) {
		super();
		this.mId = mId;
		this.mDesc = mDesc;
		this.mPrice = mPrice;
		
	}
	public Menu() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getmDesc() {
		return mDesc;
	}
	public void setmDesc(String mDesc) {
		this.mDesc = mDesc;
	}
	public double getmPrice() {
		return mPrice;
	}
	public void setmPrice(double mPrice) {
		this.mPrice = mPrice;
	}
	public boolean ismDiscount() {
		return mDiscount;
	}
	public void setmDiscount(boolean mDiscount) {
		this.mDiscount = mDiscount;
	}
	
	
	
	
	

}
