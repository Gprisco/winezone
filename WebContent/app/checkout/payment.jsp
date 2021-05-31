<%@ page language="java" import="routes.Routes"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../../css/bootstrap.min.css" rel="stylesheet">
<link href="../../css/style.css" rel="stylesheet">
<title>Pagamento</title>
</head>
<body>
	<jsp:include page="../Components/NavBar.jsp" flush="true" />

	<div class="container h-100 mt-4">
		<div class="row h-100 mt-4">
			<div class="col my-auto">
				<h4 class="h4 text-center">Grazie per averci scelto!</h4>
				<h6 class="h6 text-center text-muted">Il tuo ordine arriverà
					tra 3-5 giorni lavorativi</h6>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>

</body>
</html>