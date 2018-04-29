package model;

public class Graph {
	
	private String name;
	private double totalPaid;
	private String menu;
	private int totalSold;
	private int bestCust;
	
	
	
	
	
	
	
	public Graph(int totalSold,String menu ) {
		super();
		this.menu = menu;
		this.totalSold = totalSold;
	}


	public Graph(String name, int bestCust) {
		super();
		this.name = name;
		this.bestCust = bestCust;
	}


	public Graph(String name, double totalPaid) {
		super();
		this.name = name;
		this.totalPaid = totalPaid;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTotalPaid() {
		return totalPaid;
	}
	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public int getTotalSold() {
		return totalSold;
	}
	public void setTotalSold(int totalSold) {
		this.totalSold = totalSold;
	}
	public int getBestCust() {
		return bestCust;
	}
	public void setBestCust(int bestCust) {
		this.bestCust = bestCust;
	}
	
	

}
