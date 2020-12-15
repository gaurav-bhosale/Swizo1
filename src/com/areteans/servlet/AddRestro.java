package com.areteans.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddRestro")
public class AddRestro extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //HttpSession session = request.getSession();
        
        String name = request.getParameter("Name");
        String username = request.getParameter("UserName");
        String password = request.getParameter("Password");
        String role = "Restro";
        int status =1;
        
        try {
 		   System.out.println("Restro = " + name + "UserName = " + username + "Password = " + password + "Role = " + role + "Status = " + status);

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Swizo_Db","root", "root");

            PreparedStatement ps = con.prepareStatement("insert into Users (Name, UserName, Password, Role, Status) values (?,?,?,?,?)");

            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.setInt(5, status);
            
            int rowAffected = ps.executeUpdate();
            
            if(rowAffected > 0) {
                System.out.println("Restro Added sucessfully");
                response.sendRedirect("ListAllRestro");
            }
            else {
            	System.out.println("Failed to Add Restro");
                response.sendRedirect("ListAllRestro");
            }
        }
        catch(Exception se) {
            se.printStackTrace();
        }
	}
}
