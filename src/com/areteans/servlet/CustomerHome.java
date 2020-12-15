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

/**
 * Servlet implementation class Restaurant
 */
@WebServlet("/CustomerHome")
public class CustomerHome extends HttpServlet {

	Connection con;
	PreparedStatement pStmt;
	
	private static final long serialVersionUID = 1L;
	@Override
	public void init(ServletConfig config) throws ServletException {	
		super.init(config);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Swizo_Db","root", "root");
			pStmt = con.prepareStatement("select * from Users where Role = 'Restro'");
        } 
        catch(ClassNotFoundException  | SQLException e){
			e.printStackTrace();
        }
    }
    public CustomerHome() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
	   
		try(ResultSet rs =pStmt.executeQuery();){ 
			out.println("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>Home</title>\r\n" + 
					"<style>\r\n" + 
					"	img {\r\n" + 
					"  display: block;\r\n" + 
					"  margin-left: auto;\r\n" + 
					"  margin-right: auto;\r\n" + 
					"  width: 50%;\r\n" + 
					"}\r\n" + 
					"	body {font-family: Arial, Helvetica, sans-serif;}\r\n" + 
					"\r\n" + 
					".navbar {\r\n" + 
					"  overflow: hidden;\r\n" + 
					"  background-color: rgb(31, 29, 29);\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".navbar a {\r\n" + 
					"  float: left;\r\n" + 
					"  font-size: 16px;\r\n" + 
					"  color: white;\r\n" + 
					"  text-align: center;\r\n" + 
					"  padding: 14px 16px;\r\n" + 
					"  text-decoration: none;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".dropdown {\r\n" + 
					"  float: left;\r\n" + 
					"  overflow: hidden;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".dropdown .dropbtn {\r\n" + 
					"  font-size: 16px;  \r\n" + 
					"  border: none;\r\n" + 
					"  outline: none;\r\n" + 
					"  color: white;\r\n" + 
					"  padding: 14px 16px;\r\n" + 
					"  background-color: inherit;\r\n" + 
					"  font-family: inherit;\r\n" + 
					"  margin: 0;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".navbar a:hover, .dropdown:hover .dropbtn {\r\n" + 
					"  background-color: #797474;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".dropdown-content {\r\n" + 
					"  display: none;\r\n" + 
					"  position: absolute;\r\n" + 
					"  background-color: #f9f9f9;\r\n" + 
					"  min-width: 160px;\r\n" + 
					"  box-shadow: 0px 8px 16px 0px rgba(95, 94, 94, 0.2);\r\n" + 
					"  z-index: 1;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".dropdown-content a {\r\n" + 
					"  float: none;\r\n" + 
					"  color: rgb(99, 97, 97);\r\n" + 
					"  padding: 12px 16px;\r\n" + 
					"  text-decoration: none;\r\n" + 
					"  display: block;\r\n" + 
					"  text-align: left;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".dropdown-content a:hover {\r\n" + 
					"  background-color: #ddd;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".dropdown:hover .dropdown-content {\r\n" + 
					"  display: block;\r\n" + 
					"}\r\n" + 
					"	@media screen and (max-width: 650px) {\r\n" + 
					"	  .column {\r\n" + 
					"		width: 100%;\r\n" + 
					"		display: block;\r\n" + 
					"	  }\r\n" + 
					"	}\r\n" + 
					"	.fixed-footer{\r\n" + 
					"		width: 100%;\r\n" + 
					"        position: fixed;        \r\n" + 
					"        background: rgb(161, 160, 160);\r\n" + 
					"        padding: 10px 0;\r\n" + 
					"        color: #fff;\r\n" + 
					"    text-align: center;\r\n" + 
					"        bottom: 0;\r\n" + 
					"    } \r\n" + 
					"	\r\n" + 
					"		</style>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<div class=\"navbar\">\r\n" + 
					"	<ul>\r\n" + 
					"		<li style=\"float: right;\"><a href=\"Logout\">Signout</a></li>\r\n" + 
					"		<li style=\"float: right;\"><a href=\"changePassword.html\">Change Password</a></li>\r\n" + 
					"	</ul>\r\n" + 
					"	  </div>\r\n" + 
					"\r\n" + 
					"	  \r\n" + 
					"	<h2 style=\"text-align: center;\">Welcome</h2>\r\n" + 
					"	<h2 style=\"text-align: center;\">Choose Restro</h2>\r\n" + 
					"	<div class=\"img\">\r\n");
			
					while(rs.next()){
						
						out.println("<h4>" + 
	                         "<div class=\"row\">" +
	                         "<div class=\"column\" style=\"background-color:#ffff;\">"+
	                         "<h3><a style='text-align: center;' href='ListMenu?id="+rs.getInt(1)+"'>"+rs.getString(2)+"</a></h3>"+
	                         "</div></div> </h4>");
					}
					
					out.println("	</div>\r\n" + 
					"</div>    \r\n" + 
					"\r\n" + 
					"<div class=\"fixed-footer\">\r\n" + 
					"	<div class=\"container\">Copyright &copy; 2020 SWIZO</div>\r\n" + 
					"</div>\r\n" + 
					"\r\n" + 
					"</body>\r\n" + 
					"</html>");

		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
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
