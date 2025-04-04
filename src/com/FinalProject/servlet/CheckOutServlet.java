package com.FinalProject.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FinalProject.connection.DbCon;
import com.FinalProject.dao.OrderDao;
import com.FinalProject.model.*;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out= response.getWriter()){
			
           SimpleDateFormat formatter= new SimpleDateFormat("yyyy-mm-dd");
		   Date date=new Date();
		   
		   //Retrieve all cart products
		   ArrayList<Cart> cart_list= (ArrayList<Cart>)request.getSession().getAttribute("cart-list");
		   //user authentication
		   User auth=(User)request.getSession().getAttribute("auth");
		   //check auth and cart_list
		   if(cart_list != null && auth != null){
			   //prepare the order object
			   for(Cart c:cart_list){
				  Order order=new Order(); 
				  order.setId(c.getId());
				  order.setUid(auth.getId());
				  order.setQuantity(c.getQuantity());
				  order.setDate(formatter.format(date));
				  //Instantiate the dao class
				  OrderDao oDao=new OrderDao(DbCon.getConnection());
				  //calling the insert method
				  boolean result = oDao.insertOrder(order);
				  if(!result)break;
			   }
			   
			   cart_list.clear();
			   response.sendRedirect("orders.jsp");
			   
		   }else{
			   if(auth == null)response.sendRedirect("login.jsp");
		       response.sendRedirect("cart.jsp");
		   }
		   
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
