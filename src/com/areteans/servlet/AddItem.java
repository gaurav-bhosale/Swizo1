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

@WebServlet("/AddItem")
public class AddItem extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        String name = request.getParameter("Name");
        String catId = request.getParameter("CategoryId");
        int categoryId = Integer.parseInt(catId);
        String price1 = request.getParameter("Price");
        float price = Float.parseFloat(price1);
        String disc = request.getParameter("Discount");
        float discount = Float.parseFloat(disc);
        int restroId = (int) session.getAttribute("userId");
        
        try {
 		   System.out.println("Dish = " + name + "CategoryId = " + categoryId+ "RestroId = " + restroId+ "Price = " + price+ "Discount = " + discount);
            // loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
            
            //creating connection with the database 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Swizo_Db","root", "root");

            PreparedStatement ps = con.prepareStatement("insert into Items (Name, CategoryId, RestroId, Price, Discount) values (?,?,?,?,?)");

            ps.setString(1, name);
            ps.setInt(2, categoryId);
            ps.setInt(3, restroId);
            ps.setFloat(4, price);
            ps.setFloat(5, discount);
            
            int rowAffected = ps.executeUpdate();
            
            if(rowAffected > 0) {
                System.out.println("Item Added sucessfully");
                response.sendRedirect("restroHome.html");
            }
            else {
            	System.out.println("Failed to Add Item");
                response.sendRedirect("addItem.html");
            }
        }
        catch(Exception se) {
            se.printStackTrace();
        }
	}
}
