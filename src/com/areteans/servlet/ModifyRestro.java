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

@WebServlet("/ModifyRestro")
public class ModifyRestro extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("In doGet of Modify Restro");
		HttpSession session = request.getSession();
		
		String sRestroId = request.getParameter("id");
		int restroId = Integer.parseInt(sRestroId);
		//int restroId = (int) session.getAttribute("userId");
		System.out.println("restro id = " + restroId);
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Swizo_Db","root", "root");
			
			PreparedStatement ps = con.prepareStatement("select * from Users where Id = ?");
			
			ps.setInt(1, restroId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getInt(6));

			session.setAttribute("modidyId", rs.getInt(1));			
			
			out.println("<html>" + 
					"<head>" + 
					"<meta charset='ISO-8859-1'>" + 
					"<title>Modify Item</title>" + 
					"</head>" + 
					"	<body>" + 
					"		<form action='SaveModifiedRestro' method='POST'>" + 
					"			Restro Name:<input type='text' name='Name' value='"+ rs.getString(2) + "'/></br>" +  
					"			<input type='submit' value='Save'/><br>" + 
					"			<a href='ListAllRestro'>Cancel</a>" + 
					"		</form>" + 
					"	</body>" + 
					"</html>");
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
