<%@ page language="java" import="routes.Routes"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="previousPage" value="${String.valueOf(previousPage)}" />
<c:set var="nextPage" value="${String.valueOf(nextPage)}" />

<c:set var="url" value="${param.url}" />

<%
String wineQueryQS = (String) request.getAttribute("wineQueryQS");

wineQueryQS = wineQueryQS == null ? "" : wineQueryQS;
%>

<div class="col">
	<c:if test="${currentPage > 0}">
		<a
			href="<%=response.encodeUrl(Routes.BASE_URL + (String) pageContext.getAttribute("url") + "?page="
		+ (String) pageContext.getAttribute("previousPage") + wineQueryQS)%>">Precedente</a>
	</c:if>
</div>

<div class="col">
	<p>
		Sei alla pagina <b>${ currentPage+1 }/${ totalPages }</b>
	</p>
</div>

<div class="col">
	<c:if test="${currentPage+1 < totalPages}">
		<a
			href="<%=response.encodeUrl(Routes.BASE_URL + (String) pageContext.getAttribute("url") + "?page="
		+ (String) pageContext.getAttribute("nextPage") + wineQueryQS)%>">Successiva</a>
	</c:if>
</div>
