package com.areteans.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ListMenu")
public class ListMenu extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//session.setAttribute("list", null);
		System.out.println("In doGet ListAllItem...");
		PrintWriter out = response.getWriter();

		//int custId = (int) session.getAttribute("userId");
		String sRestroId = request.getParameter("id");
		int restroId = Integer.parseInt(sRestroId);
		session.setAttribute("selectedRestroId", restroId);
		
		try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Swizo_Db","root", "root");

	            PreparedStatement ps = con.prepareStatement("select * from Items where RestroId = ?");
	            
	            ps.setInt(1, restroId);
	            ResultSet rs = ps.executeQuery();
	            
	            out.println("<!DOCTYPE html>" + 
	            		"<html>" + 
	            		"<head>" + 
	            		"<meta charset='ISO-8859-1'>" + 
	            		"<title>List Of Items</title>" + 
	            		"</head>" + 
	            		"<style>\r\n" + 
	            		"	img {\r\n" + 
	            		"  display: block;\r\n" + 
	            		"  margin-left: auto;\r\n" + 
	            		"  margin-right: auto;\r\n" + 
	            		"  width: 50%;\r\n" + 
	            		"}\r\n" + 
	            		"	body {font-family: Arial, Helvetica, sans-serif;}\r\n" + 
	            		"\r\n" + 
	            		".navbar {\r\n" + 
	            		"  overflow: hidden;\r\n" + 
	            		"  background-color: rgb(31, 29, 29);\r\n" + 
	            		"}\r\n" + 
	            		"\r\n" + 
	            		".navbar a {\r\n" + 
	            		"  float: left;\r\n" + 
	            		"  font-size: 16px;\r\n" + 
	            		"  color: white;\r\n" + 
	            		"  text-align: center;\r\n" + 
	            		"  padding: 14px 16px;\r\n" + 
	            		"  text-decoration: none;\r\n" + 
	            		"}\r\n" + 
	            		"\r\n" + 
	            		".dropdown {\r\n" + 
	            		"  float: left;\r\n" + 
	            		"  overflow: hidden;\r\n" + 
	            		"}\r\n" + 
	            		"\r\n" + 
	            		".dropdown .dropbtn {\r\n" + 
	            		"  font-size: 16px;  \r\n" + 
	            		"  border: none;\r\n" + 
	            		"  outline: none;\r\n" + 
	            		"  color: white;\r\n" + 
	            		"  padding: 14px 16px;\r\n" + 
	            		"  background-color: inherit;\r\n" + 
	            		"  font-family: inherit;\r\n" + 
	            		"  margin: 0;\r\n" + 
	            		"}\r\n" + 
	            		"\r\n" + 
	            		".navbar a:hover, .dropdown:hover .dropbtn {\r\n" + 
	            		"  background-color: #797474;\r\n" + 
	            		"}\r\n" + 
	            		"\r\n" + 
	            		".dropdown-content {\r\n" + 
	            		"  display: none;\r\n" + 
	            		"  position: absolute;\r\n" + 
	            		"  background-color: #f9f9f9;\r\n" + 
	            		"  min-width: 160px;\r\n" + 
	            		"  box-shadow: 0px 8px 16px 0px rgba(95, 94, 94, 0.2);\r\n" + 
	            		"  z-index: 1;\r\n" + 
	            		"}\r\n" + 
	            		"\r\n" + 
	            		".dropdown-content a {\r\n" + 
	            		"  float: none;\r\n" + 
	            		"  color: rgb(99, 97, 97);\r\n" + 
	            		"  padding: 12px 16px;\r\n" + 
	            		"  text-decoration: none;\r\n" + 
	            		"  display: block;\r\n" + 
	            		"  text-align: left;\r\n" + 
	            		"}\r\n" + 
	            		"\r\n" + 
	            		".dropdown-content a:hover {\r\n" + 
	            		"  background-color: #ddd;\r\n" + 
	            		"}\r\n" + 
	            		"\r\n" + 
	            		".dropdown:hover .dropdown-content {\r\n" + 
	            		"  display: block;\r\n" + 
	            		"}\r\n" + 
	            		"	@media screen and (max-width: 650px) {\r\n" + 
	            		"	  .column {\r\n" + 
	            		"		width: 100%;\r\n" + 
	            		"		display: block;\r\n" + 
	            		"	  }\r\n" + 
	            		"	}\r\n" + 
	            		"	.fixed-footer{\r\n" + 
	            		"		width: 100%;\r\n" + 
	            		"        position: fixed;        \r\n" + 
	            		"        background: rgb(161, 160, 160);\r\n" + 
	            		"        padding: 10px 0;\r\n" + 
	            		"        color: #fff;\r\n" + 
	            		"    text-align: center;\r\n" + 
	            		"        bottom: 0;\r\n" + 
	            		"    } \r\n" + 
	            		"	\r\n" + 
	            		"		</style>"+
	            		"	<body>" +
	            		"<div class=\"navbar\">\r\n" + 
	            		"	<ul>\r\n" + 
	            		"		<li style=\"float: right;\"><a href=\"Logout\">Signout</a></li>\r\n" + 
	            		"		<li style=\"float: right;\"><a href=\"changePassword.html\">Change Password</a></li>\r\n" + 
	            		"	</ul>\r\n" + 
	            		"	  </div><br><br>" +
	            		
	            		"		<table border=1 style='border-collapse: collapse; width: 100%;'>" + 
	            		"			<tr>" + 
	            		"				<th>Name</th>" + 
	            		"				<th>Price</th>" + 
	            		"				<th>Discount</th>" + 
	            		"				<th>Category</th>" + 
	            		"			</tr>");
	            while(rs.next()) {
	            	
	            	out.println("<tr><td>" + rs.getString(2) + "</td>"+
	            		"		<td>" + rs.getFloat(5) + "</td>"+
	            		"		<td>" + rs.getFloat(6) + "</td>");
	            	if(rs.getInt(3) == 1) {
	            		out.println("<td> Veg </td>");
	            	}
	            	else {
	            		out.println("<td> Non-Veg </td>");
	            	}
	            	out.println("		<td> <a href='AddCart?itemId="+rs.getInt(1)+"&name="+rs.getString(2)+"&price="+rs.getFloat(5)+"&discount="+rs.getFloat(6)+"'> Add To Cart </a></td>");
	            }
	            out.println("		</table>" +
	            		"   <br><a href='CustomerHome'>Cancel</a>"+			
	            		"	</body>" + 
	            		"</html>");
	        }
	        catch(Exception se) {
	            se.printStackTrace();
	        }
	}
}
