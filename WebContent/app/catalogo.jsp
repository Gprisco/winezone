<%@ page language="java"
	import="model.WineBean, java.util.*, routes.Routes"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
if (request.getAttribute("wines") == null)
	response.sendRedirect(request.getContextPath() + Routes.APP_MAIN);

String query = request.getParameter("q");
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
		<h6 class="h6 text-muted">Sfoglia il nostro catalogo di centinaia
			di vini</h6>
	</div>

	<div class="container text-center w-50">
		<div class="row justify-content-between">
			<div class="col-12 mb-4">
				<form action="<%=Routes.BASE_URL + Routes.CATALOGO%>" method="get">
					<input name="q" type="text" class="form-control" id="wineQuery"
						placeholder="Vino | Azienda | Colore..."
						value="<%=query != null ? query : ""%>">
				</form>
			</div>

			<c:if test="<%=query != null && query.trim().length() > 0%>">
				<div class="col-12 mb-4">
					<p class="lead text-center">
						Ti stiamo mostrando <b>${ wines.size() }</b> vini su <b>${winesCount}</b>
					</p>
				</div>
			</c:if>

			<jsp:include page="./Components/Pagination.jsp" flush="true">
				<jsp:param name="url" value="<%=Routes.CATALOGO%>" />
			</jsp:include>
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