<%@ page language="java" import="routes.Routes"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../../css/bootstrap.min.css" rel="stylesheet">
<link href="../../css/style.css" rel="stylesheet">
<title>Ordini effettuati</title>
</head>
<body>
	<jsp:include page="../Components/NavBar.jsp" flush="true" />

	<div class="container mt-4">
		<div class="row">
			<div class="col">
				<h1 class="h1">Ordini effettuati</h1>
			</div>
		</div>
	</div>

	<div class="container text-center w-50">
		<div class="row justify-content-between">
			<jsp:include page="../Components/Pagination.jsp" flush="true">
				<jsp:param name="url" value="<%=Routes.SHIPPINGS%>" />
			</jsp:include>
		</div>
	</div>

	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">User ID</th>
					<th scope="col">Address</th>
					<th scope="col">Date</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="shipping" items="${shippings}">
					<tr>
						<th scope="row">${shipping.getId()}</th>
						<td>${shipping.getIdUser() }</td>
						<td>${shipping.getAddress() }</td>
						<td>${shipping.getCreatedAt() }</td>

						<c:set var="shippingId" value="${shipping.getId()}" />

						<td><a
							href="<%=response.encodeUrl(
		Routes.BASE_URL + Routes.SHIPPING_DETAILS + "?id=" + String.valueOf(pageContext.getAttribute("shippingId")))%>"
							class="btn btn-sm btn-secondary">Dettagli</a></td>
						<td><a
							href="<%=response.encodeUrl(
		Routes.BASE_URL + Routes.DELETE_SHIPPING + "?id=" + String.valueOf(pageContext.getAttribute("shippingId")))%>"
							class="btn btn-sm btn-danger">Elimina</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
</body>
</html>