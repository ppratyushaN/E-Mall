
<nav class="navbar navbar-expand-lg navbar-light bg-light">

	<div class="container">
		<a class="navbar-brand" href="index.jsp"><h1>E-Mall</h1></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp"><h6>Home</h6> </a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp"><h6>Cart</h6><span class="badge badge-danger px-1">${cart_list.size()}</span></a></li>
				<%
				if(auth != null){%>
					<li class="nav-item"><a class="nav-link" href="orders.jsp"><h6>Orders</h6></a></li>
					<li class="nav-item"><a class="nav-link" href="log-out"><h6>Logout</h6></a></li>
				<%}else{%>
					<li class="nav-item"><a class="nav-link" href="login.jsp"><h6>Login</h6></a></li>	
				<%}
				%>
				
			</ul>
		</div>
	</div>
</nav>