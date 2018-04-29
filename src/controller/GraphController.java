package controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import managers.GraphManager;
import model.Graph;

@SuppressWarnings("serial")
public class GraphController implements Serializable{
	
	 private BarChartModel barModel;
	 private PieChartModel pieModel1;
	 private LineChartModel lineModel2;
	 
	GraphManager managers = new GraphManager();
	
	 
	 @PostConstruct
	    public void init(){
	        createBarModels();
	    }
	 
	    public BarChartModel getBarModel() {
	        return barModel;
	    }
	    
	    public PieChartModel getPieModel1() {
	        return pieModel1;
	    }
	    
	    public LineChartModel getLineModel2() {
	        return lineModel2;
	    }
	    
	    private BarChartModel initBarModel() throws ClassNotFoundException, SQLException {
	        BarChartModel model = new BarChartModel();
	        ArrayList<Graph> bestCust = managers.bestCustomer();
	 
	        ChartSeries boys = new ChartSeries();
	        boys.setLabel("Customers");
	        for (int i = 0; i < bestCust.size(); i++) {
	        	boys.set(bestCust.get(i).getName(), bestCust.get(i).getBestCust());
	       	 
			}
	      
	    
	        model.addSeries(boys);
	        	         
	        return model;
	    }
	     
	    private void createBarModels() {
	        try {
				createBarModel();
				createPieModel1();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	    }
	    
	    
	    private void createBarModel() throws ClassNotFoundException, SQLException {
	        barModel = initBarModel();
	         
	        barModel.setTitle("Best Customer");
	        barModel.setLegendPosition("ne");
	         
	        Axis xAxis = barModel.getAxis(AxisType.X);
	        xAxis.setLabel("Customer Name");
	         
	        Axis yAxis = barModel.getAxis(AxisType.Y);
	        yAxis.setLabel("Number of sales per customer");
	        yAxis.setMin(0);
	        
	        
	        lineModel2 = initCategoryModel();
	        lineModel2.setTitle("Best Selling Menus");
	        lineModel2.setLegendPosition("e");
	        lineModel2.setShowPointLabels(true);
	        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Menu"));
	        yAxis = lineModel2.getAxis(AxisType.Y);
	        yAxis.setLabel("Count");
	        yAxis.setMin(0);

	    }
	    
	    private LineChartModel initCategoryModel() throws ClassNotFoundException, SQLException {
	        LineChartModel model = new LineChartModel();
	        
	        ArrayList<Graph> best = managers.bestSellingMenu();
	        ChartSeries boys = new ChartSeries();
	        boys.setLabel("Menus");
	        
	        for (int i = 0; i < best.size(); i++) {
	        	 boys.set(best.get(i).getMenu(), best.get(i).getTotalSold());
	       	 
			}    
	 
	        model.addSeries(boys);
	       	         
	        return model;
	    }
	 
	    
	    
	    private void createPieModel1() throws ClassNotFoundException, SQLException {
	        pieModel1 = new PieChartModel();
	        
	        ArrayList<Graph> rev = managers.revenuePerCustomer();
	   	 
	        
	        for (int i = 0; i < rev.size(); i++) {
	        	 pieModel1.set(rev.get(i).getName(), rev.get(i).getTotalPaid());
	       	 
			}	              
	         
	        pieModel1.setTitle("Revenue per customer");
	        pieModel1.setLegendPosition("w");
	    }
	     

}
