<%@ page language="java"
	import="routes.Routes, model.WineBean, model.WineWinegrapeBean, java.net.URLEncoder, java.nio.charset.StandardCharsets"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String winegrapes = "";

if (request.getAttribute("wine") == null)
	response.sendRedirect(Routes.CATALOGO);
else {
	WineBean wine = (WineBean) request.getAttribute("wine");

	for (WineWinegrapeBean wg : wine.getWinegrapes()) {
		System.out.println(wg.getWinegrape().getWinegrape());
		winegrapes += wg.getWinegrape().getWinegrape() + " (" + String.valueOf(wg.getPercentage()) + "%) ";
	}
}
%>

<c:set var="wineParam" value="${wine.getPk().getWine()}" />
<c:set var="vintageParam"
	value="${String.valueOf(wine.getPk().getVintage())}" />

<%
String queryString = "?wine="
		+ URLEncoder.encode((String) pageContext.getAttribute("wineParam"), StandardCharsets.UTF_8) + "&vintage="
		+ URLEncoder.encode((String) pageContext.getAttribute("vintageParam"), StandardCharsets.UTF_8);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<title>${ wine.getPk().getWine() }${" "}${ wine.getPk().getVintage() }</title>
</head>
<body>
	<jsp:include page="Components/NavBar.jsp" flush="true" />

	<div class="container my-4">
		<div class="row">
			<div class="col-md-12 col-lg-4">
				<img class="img-fluid" src="/Winezone/public/images/wine.big.jpg"
					alt="Wine image" />
			</div>

			<div class="col-md-12 col-lg-8 px-4 my-auto">
				<h3 class="h3 my-4">${ wine.getPk().getWine() }${" "}${ wine.getPk().getVintage() }</h3>

				<jsp:include page="Components/TableRow.jsp" flush="true">
					<jsp:param name="key" value="Azienda" />
					<jsp:param name="value" value="${ wine.getWinery().getWinery() }" />
				</jsp:include>

				<jsp:include page="Components/TableRow.jsp" flush="true">
					<jsp:param name="key" value="Famiglia" />
					<jsp:param name="value"
						value="${ wine.getWinefamily().getWinefamily() }" />
				</jsp:include>

				<jsp:include page="Components/TableRow.jsp" flush="true">
					<jsp:param name="key" value="Colore" />
					<jsp:param name="value"
						value="${ wine.getWinefamily().getWinecolor() }" />
				</jsp:include>

				<jsp:include page="Components/TableRow.jsp" flush="true">
					<jsp:param name="key" value="Tipologia" />
					<jsp:param name="value"
						value="${ wine.getWinefamily().getWinetype() }" />
				</jsp:include>

				<jsp:include page="Components/TableRow.jsp" flush="true">
					<jsp:param name="key" value="Uvaggio" />
					<jsp:param name="value" value="<%=winegrapes%>" />
				</jsp:include>

				<hr />
				<jsp:include page="Components/TableRow.jsp" flush="true">
					<jsp:param name="key" value="Prezzo" />
					<jsp:param name="value" value="€ ${wine.getPrice()}" />
				</jsp:include>

				<div class="row mt-4">
					<div class="col-12">
						<a
							href="<%=response.encodeUrl(Routes.BASE_URL + Routes.ADD_TO_CART + queryString)%>"
							class="btn btn-primary w-100">Aggiungi al carrello</a>
					</div>

				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
</body>
</html>