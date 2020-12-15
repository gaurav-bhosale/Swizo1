package com.areteans.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DeleteItem")
public class DeleteItem extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		String itemsId = request.getParameter("id");
		int itemId = Integer.parseInt(itemsId);
		int restroId = (int) session.getAttribute("userId");
		System.out.println(itemsId + " " + restroId);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Swizo_Db","root", "root");
			
			PreparedStatement ps = con.prepareStatement("delete from Items where Id = ? and RestroId = ?");
			
			ps.setInt(1, itemId);
			ps.setInt(2, restroId);
			
			int affectedRows = ps.executeUpdate();
			if(affectedRows >= 1) {
				System.out.println("Item deleted sucessfully");
				response.sendRedirect("ListAllItem");
			}
			else {
				System.out.println("Failed to delete Item");
				response.sendRedirect("ListAllItem");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
