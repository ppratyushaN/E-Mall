<%@ page import="java.util.*" %>
<%@ page import="com.FinalProject.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%
	User auth = (User)request.getSession().getAttribute("auth");
	if(auth!=null){
		response.sendRedirect("index.jsp");
	}
	
	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
	if(cart_list != null){
		request.setAttribute("cart_list",cart_list);
	}
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>E-Mall login</title>
<%@include file="includes/head.jsp"%>
</head>
<body style= "background-color:#ADDFFF;">
<%@include file="includes/navbar.jsp"%>
	<div class="container" style= "background-color:#a0ced9;">
		<div class="card w-50 mx-auto my-5"></div>
		<div class="card-header text-center"><h3>User Login</h3></div>
		<form action="user-login" method="post">

			<div class="form-group">
				<label><h5>Email Address</h5></label> <input type="email"
					class="form-control" name="login-email"
					placeholder="Enter Your Email" required>
			</div>
			<div class="form-group">
				<label><h5>Password</h5></label> <input type="password" class="form-control"
					name="login-password" placeholder="*******" required>
			</div>

			<div class="text-center">
				<button type="submit" class="btn btn-primary">Login</button>
			</div>


		</form>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>