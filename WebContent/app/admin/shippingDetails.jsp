<%@ page language="java"
	import="routes.Routes, java.net.URLEncoder, java.nio.charset.StandardCharsets"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<title>Dettagli Ordine</title>
</head>
<body>
	<jsp:include page="../Components/NavBar.jsp" flush="true" />

	<div class="container mt-4">
		<div class="row">
			<div class="col">
				<h1 class="h1">Ordine #${shipping.getId()}</h1>
			</div>
		</div>

		<div class="row mt-4">
			<div class="col">
				<p class="lead">
					<b>Utente</b> #${shipping.getIdUser()}
				</p>
			</div>
		</div>

		<div class="row">
			<div class="col">
				<p class="lead">
					<b>Indirizzo:</b> ${shipping.getAddress()}
				</p>
			</div>
		</div>

		<hr />

		<div class="row mt-2">
			<p class="lead">
				<b>Vini:</b>
			</p>
		</div>

		<div class="row">
			<ul class="list-group">
				<c:forEach var="wine" items="${shipping.getWines()}">
					<c:set var="wineParam" value="${wine.getWine()}" />
					<c:set var="vintageParam"
						value="${String.valueOf(wine.getVintage())}" />

					<%
					String queryString = "?wine="
							+ URLEncoder.encode((String) pageContext.getAttribute("wineParam"), StandardCharsets.UTF_8) + "&vintage="
							+ URLEncoder.encode((String) pageContext.getAttribute("vintageParam"), StandardCharsets.UTF_8);
					String url = Routes.BASE_URL + Routes.DETAILS + queryString;
					%>

					<li class="list-group-item lead"><a
						href="<%=response.encodeUrl(url)%>" class="lead">${wine.getWine()}${" - "}${wine.getVintage()}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
</body>
</html>