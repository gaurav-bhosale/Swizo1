package com.areteans.servlet;

import java.io.IOException;
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

@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		float total = (float) session.getAttribute("total");
		int restroId = (int) session.getAttribute("selectedRestroId");
		int custId = (int) session.getAttribute("userId");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Swizo_Db","root", "root");

            PreparedStatement ps = con.prepareStatement("insert into Orders (CustomerId, RestroId, Total) values (?,?,?)");
            ps.setInt(1, custId);
            ps.setInt(2, restroId);
            ps.setFloat(3, total);
            
            int affectedRows = ps.executeUpdate();
            if(affectedRows >= 1) {
            	System.out.println("Order Placed  sucessfully...");
            	session.setAttribute("list", null);
            	response.sendRedirect("CustomerHome");
            }
            else {
            	System.out.println("Order Placing failed...");
            	session.setAttribute("list", null);
            	response.sendRedirect("CustomerHome");
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
