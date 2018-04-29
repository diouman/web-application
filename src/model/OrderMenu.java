package model;



public class OrderMenu {
	
	private int mId , oId, qty,cId;
	private double subTotal,mPrice;
	
	Menu menu = new Menu();
	
	public OrderMenu(Menu m,int qty,double mPrice) {
		this.setQty(qty);
		this.menu = m;
		this.subTotal = mPrice;
		
	}
	
	public OrderMenu() {
		// TODO Auto-generated constructor stub
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}


	public int getoId() {
		return oId;
	}

	public void setoId(int oId) {
		this.oId = oId;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public double getmPrice() {
		return mPrice;
	}

	public void setmPrice(double mPrice) {
		this.mPrice = mPrice;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	
	
	
	

}
