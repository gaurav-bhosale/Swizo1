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

@WebServlet("/DeleteRestro")
public class DeleteRestro extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String sRestroId = request.getParameter("id");
		int restroId = Integer.parseInt(sRestroId);
		System.out.println("============>" + restroId);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Swizo_Db","root", "root");
			
			PreparedStatement ps = con.prepareStatement("delete from Users where Id = ?");
			
			ps.setInt(1, restroId);
			
			int affectedRows = ps.executeUpdate();
			if(affectedRows >= 1) {
				System.out.println("Restro deleted sucessfully");
				response.sendRedirect("ListAllRestro");
			}
			else {
				System.out.println("Failed to delete Restro");
				response.sendRedirect("ListAllRestro");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
