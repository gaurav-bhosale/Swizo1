package com.areteans.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SaveModifiedItem")
public class SaveModifiedItem extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		int itemId = (int) session.getAttribute("modidyId");
		int restroId = (int) session.getAttribute("userId");
		String name = request.getParameter("Name");
		String sCategory = request.getParameter("CategoryId");
		int categoryId = Integer.parseInt(sCategory);
		String sPrice = request.getParameter("Price");
		float price = Float.parseFloat(sPrice);
		String sDiscount = request.getParameter("Discount");
		float discount = Float.parseFloat(sDiscount);
		
		System.out.println(itemId + " " + name + " " + categoryId + " " +restroId + " " + price + " " + discount);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Swizo_Db","root", "root");
			
			PreparedStatement ps = con.prepareStatement("update Items set Name = ?, CategoryId =?, Price = ?, Discount = ? where Id = ? and RestroId = ?");
			
			ps.setString(1, name);
			ps.setInt(2, categoryId);
			ps.setFloat(3, price);
			ps.setFloat(4, discount);
			ps.setInt(5, itemId);
			ps.setInt(6, restroId);
			
			int affectedRows = ps.executeUpdate();
			
			if(affectedRows >= 1) {
				System.out.println("Item Modified Sucessfully");
				response.sendRedirect("ListAllItem");
			}
			else {
				System.out.println("Failed to modify Item");
				response.sendRedirect("ListAllItem");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
