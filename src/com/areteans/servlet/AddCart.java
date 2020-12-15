package com.areteans.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.areteans.pojo.Items;

@WebServlet("/AddCart")
public class AddCart extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		HttpSession session=request.getSession();
		PrintWriter out = response.getWriter();
		
		int restroId = (int) session.getAttribute("selectedRestroId");
		String sItemId = request.getParameter("itemId");
		int itemId = Integer.parseInt(sItemId);
		String name = request.getParameter("name");
		String sPrice=request.getParameter("price");
		float price = Float.parseFloat(sPrice);
		String sDiscount=request.getParameter("discount");
		float discount = Float.parseFloat(sDiscount);
		
		System.out.println("Restro selected = "+ restroId);
		System.out.println("AddCart?itemId="+itemId+"&name="+name+"&price="+price+"&discount="+discount);
		
		Items item = new Items(itemId, name, price, discount);
		
		ArrayList<Items> list = (ArrayList<Items>) session.getAttribute("list");
		
		  if(list==null) { 
			  list=new ArrayList<>(); 
			  session.setAttribute("list", list);
		  }
		  
		  list.add(item); 
		  response.sendRedirect("ListCart");
	}
}
