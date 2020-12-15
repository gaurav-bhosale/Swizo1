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

@WebServlet("/ListMenuByRestro")
public class ListMenuByRestro extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("In doGet ListAllItemsByRestro...");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String sRestroId = request.getParameter("id");
		int restroId = Integer.parseInt(sRestroId);
		
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
	            		"	<body>" + 
	            		"		<table border=1>" + 
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
	            }
	            out.println("		</table>" +
	            		"   <a href='ListAllRestro'>Cancel</a>"+
	            		"	</body>" + 
	            		"</html>");
	        }
	        catch(Exception se) {
	            se.printStackTrace();
	        }
	}
}
