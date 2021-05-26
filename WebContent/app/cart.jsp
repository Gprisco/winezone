<%@ page language="java"
	import="java.util.Collection, model.CartBean, model.WineBean, routes.Routes, java.net.URLEncoder, java.nio.charset.StandardCharsets"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
CartBean cart = (CartBean) request.getAttribute("cart");
cart = cart == null ? new CartBean() : cart;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<title>Carrello</title>
</head>
<body>
	<jsp:include page="./Components/NavBar.jsp" flush="true" />

	<div class="container mt-4">
		<h1 class="h1">Carrello</h1>

		<c:choose>
			<c:when test="${fn:length(wines) > 0}">
				<ul class="list-group mt-2 mb-4">

					<c:forEach var="wine" items="${ wines }">
						<li class="list-group-item">
							<div class="row">
								<div class="col">${wine.getPk().getWine()}<%=" - "%>
									${wine.getPk().getVintage()}
								</div>
								<div class="col-2">€ ${wine.getPrice() }</div>
								<div class="col-2">
									<c:set var="wineParam" value="${wine.getPk().getWine()}" />
									<c:set var="vintageParam"
										value="${String.valueOf(wine.getPk().getVintage())}" />

									<%
									String queryString = "?wine="
											+ URLEncoder.encode((String) pageContext.getAttribute("wineParam"), StandardCharsets.UTF_8) + "&vintage="
											+ URLEncoder.encode((String) pageContext.getAttribute("vintageParam"), StandardCharsets.UTF_8);
									String baseUrl = "/Winezone";
									String url = baseUrl + Routes.DETAILS + queryString;
									%>

									<a
										href="<%=response.encodeUrl(baseUrl + Routes.REMOVE_FROM_CART + queryString)%>"
										class="btn btn-sm btn-danger">Elimina</a>
								</div>
							</div>
						</li>
					</c:forEach>

					<li class="list-group-item mt-4">
						<div class="row">
							<div class="col">Totale</div>

							<div class="col-4 text-right">€ ${ cart.getTotalPrice() }</div>
						</div>
					</li>

				</ul>

				<a class="btn btn-lg btn-primary w-100 mt-4"
					href="<%=response.encodeUrl(Routes.BASE_URL + Routes.CHECKOUT)%>">Procedi
					alla cassa</a>

			</c:when>
			<c:otherwise>
				<p class="lead">
					Sembra che tu non abbia nulla nel carrello, vai al <a
						href="${Routes.BASE_URL }${Routes.CATALOGO}">Catalogo</a> per
					acquistare qualcosa!
				</p>
			</c:otherwise>
		</c:choose>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
</body>
</html>