package com.areteans.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/ModifyItem")
public class ModifyItem extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("In doGet of Modify Item");
		HttpSession session = request.getSession();
		
		String itemsId = request.getParameter("id");
		int itemId = Integer.parseInt(itemsId);
		int restroId = (int) session.getAttribute("userId");
		System.out.println(itemsId + " " + restroId);
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Swizo_Db","root", "root");
			
			PreparedStatement ps = con.prepareStatement("select * from Items where Id = ? and RestroId = ?");
			
			ps.setInt(1, itemId);
			ps.setInt(2, restroId);
			
			System.out.println();
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
			System.out.println(itemId + " " + rs.getString(2) + " " + rs.getInt(3) + " " +restroId + " " + rs.getFloat(5) + " " + rs.getFloat(6));

			session.setAttribute("modidyId", itemId);			
			
			out.println("<html>" + 
					"<head>" + 
					"<meta charset='ISO-8859-1'>" + 
					"<title>Modify Item</title>" + 
					"</head>" + 
					"	<body>" + 
					"		<form action='SaveModifiedItem' method='POST'>" + 
					"			Dish Name:<input type='text' name='Name' value='"+ rs.getString(2) + "'/></br>" + 
					"			Select Category:<select name='CategoryId' id='CategoryId'>" + 
					"							  <option value='1'>Veg</option>" + 
					"							  <option value='2'>Non-Veg</option>" + 
					"							</select><br>" + 
					"			Price:<input type='text' name='Price' value='"+ rs.getFloat(5)+"'/></br>" + 
					"			Discount (%):<input type='text' name='Discount' value='"+ rs.getFloat(6) +"'/></br>" + 
					"			<input type='submit' value='Save'/><br>" + 
					"			<a href='ListAllItem'>Cancel</a>\r\n" + 
					"		</form>\r\n" + 
					"	</body>\r\n" + 
					"</html>\r\n" + 
					"<!-- ");
			}
			else {
				System.out.println("No Item Found");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
