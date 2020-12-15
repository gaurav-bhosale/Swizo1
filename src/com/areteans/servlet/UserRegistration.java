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

import com.mysql.jdbc.*;

@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    public UserRegistration() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		System.out.println("in dopost od regi");
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String name = request.getParameter("Name");
        String userName = request.getParameter("UserName");
       String pass1 = request.getParameter("Password1");
       String pass2 = request.getParameter("Password2");
        
       if(pass1.equals(pass2)) {
    	   try {
    		   System.out.println("Pass1 == pass2");
    		   
               // loading drivers for mysql
               Class.forName("com.mysql.jdbc.Driver");
               
               //creating connection with the database 
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Swizo_Db","root", "root");

               PreparedStatement ps = con.prepareStatement("insert into Users (Name , UserName , Password , Role , Status) values(?,?,?,?,?)");

               ps.setString(1, name);
               ps.setString(2, userName);
               ps.setString(3, pass1);
               ps.setString(4, "Customer");
               ps.setInt(5 , 1);
               
               int rowAffected = ps.executeUpdate();
               
               if(rowAffected > 0) {
                   System.out.println("You are sucessfully registered");
                   response.sendRedirect("login.html");
               }
               else {
            	   System.out.println("pass1 != pass2");
            	   
        			out.println("<!DOCTYPE html>" + 
        					"<html>" + 
        					"<head>" + 
        					"<meta charset='ISO-8859-1'>" + 
        					"<title>Registration</title>" + 
        					"</head>" + 
        					"	<body>" + 
        					"		<form action='UserRegistration' method='POST'>" + 
        					"			Enter Name:<input type='text' name='Name'/></br>" + 
        					"			Enter UserName:<input type='text' name='UserName'/></br>" + 
        					"			Create Password:<input type='password' name='Password1'/></br>" + 
        					"			Retype Password:<input type=\"password\" name='Password2'/>" + 
        					"			<h4 style='color: red;'>Failed to Register, Try Again</h4>" + 
        					"			<input type='submit' value='Register'/></br>" +  
        					"			<a href='login.html'>Click here to login</a>" + 
        					"		</form>" + 
        					"	</body>" + 
        					"</html>");
               }
           }
           catch(Exception se) {
               se.printStackTrace();
           }
       }
       else {
    	   System.out.println("pass1 != pass2");
    	   
			out.println("<!DOCTYPE html>" + 
					"<html>" + 
					"<head>" + 
					"<meta charset='ISO-8859-1'>" + 
					"<title>Registration</title>" + 
					"</head>" + 
					"	<body>" + 
					"		<form action='UserRegistration' method='POST'>" + 
					"			Enter Name:<input type='text' name='Name'/></br>" + 
					"			Enter UserName:<input type='text' name='UserName'/></br>" + 
					"			Create Password:<input type='password' name='Password1'/></br>" + 
					"			Retype Password:<input type=\"password\" name='Password2'/>" + 
					"			<h4 style='color: red;'>Password Not matched</h4>" + 
					"			<input type='submit' value='Register'/></br>" +  
					"			<a href='login.html'>Click here to login</a>" + 
					"		</form>" + 
					"	</body>" + 
					"</html>");
       }
	
	}

}
