<%@ page language="java"
	import="routes.Routes, java.net.URLEncoder, java.nio.charset.StandardCharsets"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="wineParam" value="${param.wine}" />
<c:set var="vintageParam" value="${String.valueOf(param.vintage)}" />

<%
String queryString = "?wine="
		+ URLEncoder.encode((String) pageContext.getAttribute("wineParam"), StandardCharsets.UTF_8) + "&vintage="
		+ URLEncoder.encode((String) pageContext.getAttribute("vintageParam"), StandardCharsets.UTF_8);
String baseUrl = "/Winezone";
String url = baseUrl + Routes.DETAILS + queryString;
%>

<div class="col-sm-12 col-md-6 col-lg-4 p-2 mx-auto">
	<div class="card mx-auto" style="width: 18rem;">
		<img src="/Winezone/public/images/wine.small.jpg" class="card-img-top"
			alt="Wine image" />
		<div class="card-body">
			<i class="card-subtitle text-muted">${ param.winecolor }</i>
			<h5 class="card-title">${ param.wine }${" "}${ String.valueOf(param.vintage) }</h5>
			<h6 class="card-subtitle mb-2 text-muted">${ param.winery }</h6>
			<a href="<%=url%>" class="card-link">Dettagli</a> <a
				href="<%=response.encodeUrl(baseUrl + Routes.ADD_TO_CART + queryString)%>"
				class="card-link">Acquista</a>
		</div>
	</div>
</div>
