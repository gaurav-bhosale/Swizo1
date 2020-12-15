package com.areteans.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.*;

@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet{

	Connection con;
	PreparedStatement pStmt;
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("In Auth Init");
		super.init(config);
       try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Swizo_Db","root", "root");
        }
       catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			pStmt = con.prepareStatement("select * from Users where UserName = ? and Password = ?");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			pStmt.setString(1, username.trim());
			pStmt.setString(2, password.trim());

			 try(ResultSet rs=pStmt.executeQuery()) {		
			 
				 PrintWriter out=response.getWriter();
				 
				 if(rs.next()){ 
					 
					 HttpSession session = request.getSession();
					 session.setAttribute("userId", rs.getInt(1));
					 session.setAttribute("userName", rs.getString(2));
					 session.setAttribute("role", rs.getString(5));
					 
					 if(rs.getString(5).equals("Admin")) {
						 System.out.println("user is admin");
						 response.sendRedirect("adminHome.html");
					 }
					 
					 if(rs.getString(5).equals("Restro")) {
						 System.out.println("user is retro");
						 response.sendRedirect("restroHome.html");
					 }
					 
					 if(rs.getString(5).equals("Customer")) {
						 System.out.println("user is cutromer");
						 response.sendRedirect("CustomerHome");
					 }
				 }						 
				 else{
					 response.sendRedirect("login.html");
				 }
			 }
			 catch(SQLException e){
				 e.printStackTrace();
			 }
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		try {
			
			if(pStmt!=null)
				pStmt.close();
			if(con!=null)
	    		con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
