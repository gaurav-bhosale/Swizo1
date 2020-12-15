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

@WebServlet("/SaveModifiedRestro")
public class SaveModifiedRestro extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		int restroId = (int) session.getAttribute("modidyId");
		String name = request.getParameter("Name");
		
		System.out.println(name + " " + restroId );
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Swizo_Db","root", "root");
			
			PreparedStatement ps = con.prepareStatement("update Users set Name = ? where Id = ?");
			
			ps.setString(1, name);
			ps.setInt(2, restroId);
			
			int affectedRows = ps.executeUpdate();
			
			if(affectedRows >= 1) {
				System.out.println("Item Modified Sucessfully");
				response.sendRedirect("ListAllRestro");
			}
			else {
				System.out.println("Failed to modify Item");
				response.sendRedirect("ListAllRestro");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
