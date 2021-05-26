<%@ page language="java"
	import="model.WineBean, java.util.*, routes.Routes"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
if (request.getAttribute("wines") == null)
	response.sendRedirect(request.getContextPath() + Routes.APP_MAIN);
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
<title>Catalogo</title>
</head>
<body>
	<jsp:include page="./Components/NavBar.jsp" flush="true" />

	<div class="m-4">
		<h1 class="h1">Catalogo</h1>
		<h6 class="h6 text-muted">Sfoglia il nostro catalogo di migliaia
			di vini</h6>
	</div>

	<c:set var="previousPage" value="${String.valueOf(previousPage)}" />
	<c:set var="nextPage" value="${String.valueOf(nextPage)}" />

	<div class="container text-center w-50">
		<div class="row justify-content-between">
			<div class="col-12 mb-4">
				<p class="lead text-center">
					Ti stiamo mostrando <b>${ wines.size() }</b> vini dei <b>${ winesCount }</b>
					totali
				</p>
			</div>

			<div class="col">
				<a
					href="<%=response
		.encodeUrl("/Winezone" + Routes.CATALOGO + "?page=" + (String) pageContext.getAttribute("previousPage"))%>">Precedente</a>
			</div>

			<div class="col">
				<p>
					Sei alla pagina <b>${ currentPage+1 }/${ totalPages }</b>
				</p>
			</div>

			<div class="col">
				<a
					href="<%=response.encodeUrl("/Winezone" + Routes.CATALOGO + "?page=" + (String) pageContext.getAttribute("nextPage"))%>">Successiva</a>
			</div>
		</div>
	</div>

	<div
		class="row m-4 justify-content-center align-center text-center mx-auto">
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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
</body>
</html>