<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, model.Menu"%>
<%@ page errorPage="errorDisplay.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:useBean id="menuBean" class="managers.MenuManager" scope="session"></jsp:useBean>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Menu</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery-ui-1.12.1.custom/jquery-ui.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/dock/js/interface.js"></script>

<link href="${facesContext.externalContext.requestContextPath}/resources/css/style.css"  rel="stylesheet" type="text/css"/>

<script type="text/javascript">
	$(document).ready(function(){
		
		$( "#tabs" ).tabs();
		
		
		
	});
</script>

</head>

<body>

<%
    int menuId = 0;
	ArrayList<Menu> menus = menuBean.getAllMenus();
	ArrayList<Menu> searchmenu = null;	
	ArrayList<Menu> selectedMenus = new ArrayList<Menu>();
	Boolean submit = Boolean.parseBoolean(request.getParameter("isSubmitted"));
	String[] chkDel = null;
	String desc,addDesc,strPrice,strRadMenu= null;
	double price,addPrice = 0;
	
	
	int exe = 0, radMenu = 0;
	String btnedit = request.getParameter("edit");
	String update = request.getParameter("update");
	String delete = request.getParameter("delete");
	String add = request.getParameter("add");
	String addNewMenu = request.getParameter("addMenu");
	String cancel = request.getParameter("cancel");
	String search = request.getParameter("search");
	
	
	if(submit){
		
		if("Add".equals(add)){
			addDesc = request.getParameter("addDesc");
			strPrice = request.getParameter("addPrice");
			
			
			if((addDesc == null) || (addDesc.equals(""))|| (strPrice == null) || (strPrice.equals(null))){
				out.println("<FONT color= red> Description and Price cannot be left empty </FONT> ");	
				
			}
			
			else{
				addPrice = Double.parseDouble(strPrice);
				exe = menuBean.addMenu(addDesc, addPrice);
				
				if(exe >=1){
					out.print("Added Successful");	
					menus = menuBean.getAllMenus();
				}
				else{
					out.print("<FONT color=red> Not Addedd </FONT>");	
				}
			}
			
			
		}
		
		else if ("Edit".equals(btnedit)){
			strRadMenu = request.getParameter("radedit");
			
			if((strRadMenu == null) ||  (strRadMenu.equals(null))){
				out.println("<FONT color=red> Please choose a record to edit </font>");
			}
			else{
				for (int i = 0; i < menus.size(); i++){
					radMenu = Integer.parseInt(strRadMenu);
					if(radMenu == menus.get(i).getmId()){
						selectedMenus.add(0, menus.get(i));
						session.setAttribute("selectedMenus", selectedMenus);
					}
				}
			}
			
		}
		
		else if ("Update".equals(update)){
			desc = request.getParameter("desc");
			price = Double.parseDouble(request.getParameter("price"));
			ArrayList<Menu> sessionMenu = (ArrayList<Menu>) session.getAttribute("selectedMenus");
			
			for(int i =0; i< sessionMenu.size();i++){
				exe = menuBean.updateMenu(sessionMenu.get(0).getmId(), desc, price);
			}
			
			if(exe >=1){
				out.println("Menu Updated");
				menus = menuBean.getAllMenus();
			}else{
				out.println("<FONT color=red> Menu not Updated </font>");
			}
			
		}
		
		else if ("Delete".equals(delete)){
			chkDel = request.getParameterValues("chkdelete");
			
			if((chkDel == null) || (chkDel.length == 0)){
				out.println("<FONT color=red> Please select atleast one record to delete </font>");
			}
			else{
				for (int i = 0; i< chkDel.length; i++){
					int mId = Integer.parseInt(chkDel[i]);
					
					exe = menuBean.deleteMenu(mId);
				}
				
				if(exe >=1){
					out.println("Menu Deleted");
					menus = menuBean.getAllMenus();
				}else{
					out.println("<FONT color=red> Menu not Deleted </font>");
				}
			}
			
			
		}
		
		else if ("Search".equals(search)){
			try{
				menuId =  Integer.parseInt(request.getParameter("menuId"));
			}
			
			catch(Exception e){
				out.println(e.getMessage());
			}
			
			searchmenu = menuBean.findMenu(menuId);
			
			if(searchmenu.size() == 0){
				out.println("<FONT color=red> Record not found </font>");
			}
		}
	}
%>


<form action="menu.jsp" method="post">
	<div id="logo">
		<div id="bolom">
			<img src="../resources/icon/2Restoran.png" alt="Chef" width="150px" />
				
		</div>
			
		<div id="text">
			<img src="../resources/icon/text.png" alt="Trio Restaurant" />
				
		</div>
	</div>
	
	<div>
		<a href="login.xhtml"><img src="../resources/icon/56805.png" alt="Trio Restaurant" style="width:50px; height:50px; float:right; margin-top:-90px;"/></a>
		
	</div>
	
	<div id="tabs">
	  <ul>
	    <li><a href="#tabs-1">Menu</a></li>
	    
	  </ul>
	  <div id="tabs-1">	  	

	  	<input type="hidden" name="isSubmitted" value="true"/>
	  	<div style="float:right">
		  	Enter Menu Id:
			<input type="text" name="menuId" />
			<input class="button" type="submit" value="Search" name="search" />
		</div>
		<br></br>
		<div id="scroll">
			<table class="tblMenu"  >			
					<tr>
						<th>Menu ID</th>    				
						<th>Description</th>
						<th >Price</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
					<%
					if (("Search".equals(search)) && (searchmenu.size() != 0)){ %>
						<tr >
							<td><%=searchmenu.get(0).getmId() %> </td>
							<td><%=searchmenu.get(0).getmDesc() %></td>
							<td><%=searchmenu.get(0).getmPrice() %> </td>
							<td><input type="radio" name="radedit" value="<%=searchmenu.get(0).getmId() %>"/></td>
							<td><input type="checkbox" name="chkdelete" value="<%=searchmenu.get(0).getmId() %>"/></td>
						</tr>
						
					<%
						searchmenu.clear();
					} 
					
					else{ 
					
						for(int i=0;i<menus.size();i++){
							Menu menu = menus.get(i);
					%>
					
					<tr >
						<td><%=menu.getmId() %> </td>
						<td><%=menu.getmDesc() %></td>
						<td><%=menu.getmPrice() %> </td>
						<td><input type="radio" name="radedit" value="<%=menu.getmId() %>"/></td>
						<td><input type="checkbox" name="chkdelete" value="<%=menu.getmId() %>"/></td>			</tr>
					<% } 
					}%>
					
			</table>
		</div>
		<br />
		<div style=" width:20%; margin-left: auto; margin-right: auto; padding: 5px;">
			<input class="button" type="submit" name="addMenu" value="Add New Menu"/>
			<input class="button" type="submit" name="edit"  value="Edit"/>
			<input class="button" type="submit" name="delete"  value="Delete"/>
		</div>
		
	  
	  
	  <table id="selectedMenu" style="width: 95%; margin-left: auto; margin-right: auto;" >
			<tr>
				<th>Menu ID</th>    				
				<th>Description</th>
				<th >Price</th>
				
				
			</tr>
			
			<%
			if("Edit".equals(btnedit)){				
					for(int i=0;i<selectedMenus.size();i++){
			%>
			
			<tr >
				<td><%=selectedMenus.get(i).getmId()%></td>
				<td><input type="text" name="desc" value="<%=selectedMenus.get(i).getmDesc()%>" /></td>
				<td><input type="text" name="price" value="<%=selectedMenus.get(i).getmPrice()%>" /> </td>
			</tr>
			<%
					}
				}
			
			
			else if ("Add New Menu".equals(addNewMenu)){			
			
			%>
			<tr>
				<td><%= menuBean.getLastMenuId() %></td>
				<td><input type="text" name="addDesc" /></td>
	  			<td><input type="text" name="addPrice" /></td>	
	  		</tr>
			
			<%}
			else if ("Cancel".equals(cancel)){				
				searchmenu = null;
			%>
			<tr></tr>
			<%} %>
		</table>
		
		<div style="width:18%;margin-left: auto; margin-right: auto;padding: 5px">
		
			<% if("Edit".equals(btnedit)){ %>
				<input  class="button" type="submit" name="add" value="Add"  disabled="disabled"/>
				<input  class="button" type="submit" name="update"  value="Update"/>
				
			<%}
			else if ("Add New Menu".equals(addNewMenu)){%>
				<input  class="button" type="submit" name="add" value="Add" />
				<input  class="button" type="submit" name="update"  value="Update" disabled="disabled"/>		
				
			<%} 
			else {%> 
				<input  class="button" type="submit" name="add" value="Add"  disabled="disabled"/>
				<input  class="button" type="submit" name="update"  value="Update" disabled="disabled"/>		
				
			<%} %>
			<input class="button" type="submit" name="cancel"  value="Cancel"/>
		
		</div>	
		
		
	 
	 
	  </div>
	
	</div>
	<div style="width: 17.9%;background-color:#E5E5E5; margin-left: auto;margin-right: auto;margin-top:40px">
		<a href="home.xhtml"><img src="resources/icon/home.png" style="width:50px;height: auto; padding:2px;"/></a>
		<a href="customer.xhtml"><img src="resources/icon/customer.png" style="width:50px;height: auto; padding:2px;"/></a>
		<a href="menu.jsp"><img src="resources/icon/food.png" style="width:50px;height: auto; padding:2px;"/></a>
		<a href="order.xhtml"><img src="resources/icon/Cart.png" style="width:50px;height: auto; padding:2px;"/></a>
		
	</div>
	
	
	
</form>
</body>
</html>