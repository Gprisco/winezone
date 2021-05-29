<%@ page language="java" import="model.WineBean"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
if (request.getAttribute("wines") == null)
	response.sendRedirect("/Winezone/app");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">
<title>Winezone</title>
</head>
<body>
	<jsp:include page="./Components/NavBar.jsp" flush="true" />

	<div class="container my-4">
		<div class="row">
			<div class="col">
				<h1 class="h1">Benvenuto su Winezone</h1>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<h6 class="h6 text-muted">Vini bianchi, rossi e rosati a casa
					tua!</h6>
			</div>
		</div>

		<div class="row my-4">
			<div class="col-12">
				<h3 class="h3">Selezione del giorno</h3>
			</div>

			<c:forEach var="wine" items="${ wines }">
				<jsp:include page="Components/WineCard.jsp">
					<jsp:param name="winecolor"
						value="${wine.getWinefamily().getWinecolor()}" />
					<jsp:param name="wine" value="${wine.getPk().getWine()}" />
					<jsp:param name="winery" value="${wine.getWinery().getWinery()}" />
					<jsp:param name="vintage" value="${wine.getPk().getVintage()}" />
				</jsp:include>
			</c:forEach>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
</body>
</html>