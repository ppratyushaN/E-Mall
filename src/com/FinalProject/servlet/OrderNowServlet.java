package com.FinalProject.servlet;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FinalProject.connection.DbCon;
import com.FinalProject.dao.OrderDao;
import com.FinalProject.model.Cart;
import com.FinalProject.model.Order;
import com.FinalProject.model.User;

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out= response.getWriter()){
			
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-mm-dd");
			
			Date date=new Date();
			
			User auth=(User)request.getSession().getAttribute("auth");
			if(auth != null){
				
				String productId= request.getParameter("id");
				int productQuantity= Integer.parseInt(request.getParameter("quantity"));
				if(productQuantity<=0){
					productQuantity=1;
				}
				
				Order orderModel= new Order();
				orderModel.setId(Integer.parseInt(productId));
				orderModel.setUid(auth.getId());
				orderModel.setQuantity(productQuantity);
				orderModel.setDate(formatter.format(date));
				
				OrderDao orderDao= new OrderDao(DbCon.getConnection());
				boolean result = orderDao.insertOrder(orderModel);
				
				if(result){
					ArrayList<Cart> cart_list= (ArrayList<Cart>)request.getSession().getAttribute("cart-list");
					if(cart_list!=null){
						for(Cart c:cart_list){
							if(c.getId()==Integer.parseInt(productId)){
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
					
					response.sendRedirect("orders.jsp");
				}else{
					out.println("order failed");
				}
				
			}else{
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
