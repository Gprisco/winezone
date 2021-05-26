
<%@ page language="java"
	import="routes.Routes, model.CartBean, model.UserRoles"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
boolean isLoggedIn = session.getAttribute(UserRoles.REGISTERED) != null;
%>

<%
String pages[][] = { { "Home", request.getContextPath() + Routes.APP_MAIN },
		{ "Catalogo", request.getContextPath() + Routes.CATALOGO } };
request.setAttribute("pages", pages);
%>

<%
CartBean cart = (CartBean) request.getSession().getAttribute("cart");

cart = cart == null ? new CartBean() : cart;
%>

<link href="/Winezone/css/NavBar.css" rel="stylesheet" />

<script src="https://kit.fontawesome.com/a87d1c7ff8.js"
	crossorigin="anonymous"></script>

<nav
	class="navbar navbar-collapse navbar-expand-lg navbar-dark bg-dark w-auto">
	<div class="container-fluid">
		<a class="navbar-brand" href="/Winezone">Winezone</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0 w-100 navbar-ul">
				<c:forEach var="page" items="${pages}">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="${page[1]}">${page[0]}</a></li>
				</c:forEach>

				<li class="nav-item d-flex flex-row"><a class="nav-item p-2"
					href="<%="/Winezone" + Routes.CART%>"><i
						class="fas fa-shopping-cart"><span class="p-1"><%=cart.getCount() > 0 ? cart.getCount() : ""%></span></i></a>

					<%
					if (isLoggedIn) {
					%> <a class="nav-item my-auto" href="<%=Routes.BASE_URL + Routes.LOGOUT%>">Logout</a>
					<%
					} else {
					%> <a class="nav-item my-auto" href="<%=Routes.BASE_URL + Routes.LOGIN%>">Login</a>
					<%
					}
					%></li>
			</ul>
		</div>
	</div>
</nav>
