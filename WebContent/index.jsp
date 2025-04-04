<%@ page import="java.util.*"%>
<%@ page import="com.FinalProject.dao.ProductDao"%>
<%@ page import="com.FinalProject.connection.DbCon"%>
<%@ page import="com.FinalProject.model.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	User auth = (User) request.getSession().getAttribute("auth");
	if (auth != null) {
		request.setAttribute("auth", auth);
	}
Connection connection = DbCon.getConnection();	
ProductDao pd = new ProductDao(connection);
List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if(cart_list != null){
	request.setAttribute("cart_list",cart_list);
}

	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome to E-Mall!!</title>
<%@include file="includes/head.jsp"%>
</head>
<body style="background-color:powderblue">

	<%@include file="includes/navbar.jsp"%>

	<div class="container" style="background-color:#a0ced9">
		<div class="card-headermy-3"><marquee behaviour="scroll" direction="left"><b><h3>All Products</h3></b></marquee></div>
		<div class="row">
			<%
		//if the product is not empty
		if(!products.isEmpty()){
			//using for loop to create more products
			for(Product p:products){%>
			<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img class="card-img-top" src="product-image/<%= p.getImage()%>"alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%= p.getName()%></h5>
						<h6 class="price">Price: $<%= p.getPrice()%></h6>
						<h6 class="category">Category: <%= p.getCategory()%></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a href="add-to-cart?id=<%= p.getId()%>" class="btn btn-dark">Add to Cart</a>
							 <a href="order-now?quantity=1&id=<%= p.getId()%>" class="btn btn-primary">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%} 
		} %>

		</div>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>