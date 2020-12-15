package com.areteans.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.areteans.pojo.Items;

@WebServlet("/ListCart")
public class ListCart extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println("Time : " + now);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Swizo_Db","root", "root");

            PreparedStatement ps = con.prepareStatement("insert into Orders values(?,?,?)");
            
			out.println("<html>");
			out.println("<body>");
			
			ArrayList<Items> list = (ArrayList<Items>) session.getAttribute("list");
			int restroId = (int) session.getAttribute("selectedRestroId");
			float cartTotal = 0;
			float discount = 0;
			 
			 if(list==null) {
				 out.println("Your cart is empty");
			 }
			 else{
				 out.println("Bill for your Ordes");
				 out.println("<table border='1'>");
				 out.println("<tr>");
				 //out.println("<th>Restro Id</th>");
				 out.println("<th>Item Name</th>");
				 out.println("<th>Price</th>");
				 out.println("<th>Discount</th>");
				 out.println("</tr>");
				 
				 for(Items items:list) { 
					 
					 out.println("<tr>");
					 //out.println("<td>"+restroId+"</td>");
					 out.println("<td>"+items.getName()+"</td>");
					 out.println("<td>"+items.getPrice()+"</td>");
					 out.println("<td>"+(items.getPrice()*items.getDiscount()/100)+"</td>");
					 out.println("</tr>");
					 
					 if(items.getDiscount() > 0) {
						 cartTotal += (items.getPrice() - (items.getPrice()*items.getDiscount()/100));
						 discount += (items.getPrice()*items.getDiscount()/100);
					 }
					 else {
						 cartTotal+=items.getPrice();
					 }
				 }
			 } 
			 out.println("</table>");
			 out.println("<h3><br/>Discount Availed:<b>"+discount+"</b></h3>");
			 out.println("<h3><br/>Total:<b>"+cartTotal+"</b></h3>");
			 out.println("</br>");
			 out.println("</br>");
			 out.println("</br>");
			 out.println("<a href=ListMenu?id="+ restroId +">Continue Shopping</a><br/>");
			 out.println("<a href='ClearCart'>Clear Cart</a>");
			 out.println("<a href='PlaceOrder'>Place Order</a>");
			 out.println("</body>");
			 out.println("</html>");
			 
			 System.out.println("Total : " + cartTotal);
			 session.setAttribute("total", cartTotal);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
