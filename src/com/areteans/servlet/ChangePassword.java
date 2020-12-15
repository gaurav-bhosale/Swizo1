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

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Swizo_Db","root", "root");
			PreparedStatement ps = con.prepareStatement("select * from Users where Id = ?");
			PreparedStatement ps1 = con.prepareStatement("update Users set Password = ? where Id = ?");
			
			int userId = (int) session.getAttribute("userId");
			String pass1 = request.getParameter("curPass");
			String pass2 = request.getParameter("newPass");
			String pass3 = request.getParameter("reNewPass");
			
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				if(rs.getString(4).equals(pass1)) {
					if(pass2.equals(pass3)) {
						ps1.setString(1, pass2);
						ps1.setInt(2, userId);
						
						int affectedRows = ps1.executeUpdate();
						if(affectedRows >= 1) {
							System.out.println("Password Changed Sucessfully...");

							if(rs.getString(5).equals("Admin")) {
								 response.sendRedirect("adminHome.html");
							 }
							 
							 if(rs.getString(5).equals("Restro")) {
								 response.sendRedirect("restroHome.html");
							 }
							 
							 if(rs.getString(5).equals("Customer")) {
								 response.sendRedirect("CustomerHome");
							 }
						}
					}
					else {
						System.out.println("new password and re-type password not matched...");
						response.sendRedirect("changePassword.html");
					}
				}
				else {
					System.out.println("Current Password Not Matched...");
					if(rs.getString(5).equals("Admin")) {
						 response.sendRedirect("adminHome.html");
					 }
					 
					 if(rs.getString(5).equals("Restro")) {
						 response.sendRedirect("restroHome.html");
					 }
					 
					 if(rs.getString(5).equals("Customer")) {
						 response.sendRedirect("CustomerHome");
					 }
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
