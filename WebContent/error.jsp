<%@ page language="java" import="routes.Routes"
	contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"
	isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">
<title>Error Page</title>
</head>
<body>
	<%
	if (response.getStatus() == 500) {
	%>
	<font color="red">Error: <%=exception.getMessage()%></font>
	<br>

	<%
	} else {
	%>
	Error code:
	<%=response.getStatus()%><br> Torna alla
	<a href="<%=Routes.BASE_URL + Routes.APP_MAIN%>">Home Page</a> e
	riprova
	<%
	}
	%>
</body>
</html>