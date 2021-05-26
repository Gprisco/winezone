<%@ page language="java" import="routes.Routes, java.util.Date"
	contentType="text/html;"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<link href="signin.css" rel="stylesheet">
<title>Sign In</title>
</head>
<body>
	<div class="container-fluid">
		<form class="form-signin" action="Login" method="post">
			<h1 class="h3 mb-3 fw-normal text-center">Login</h1>

			<div class="form-floating">
				<input name="username" type="text" class="form-control"
					id="floatingInput" placeholder="username"> <label
					for="floatingInput">Username</label>
			</div>
			<div class="form-floating">
				<input name="password" type="password" class="form-control"
					id="floatingPassword" placeholder="Password"> <label
					for="floatingPassword">Password</label>
			</div>

			<button class="w-100 btn btn-lg btn-primary" type="submit">Entra</button>
			<p class="mt-5 mb-3 text-muted text-center">
				&copy; Winezone 2020 -
				<%=(new Date()).getYear() + 1900%></p>
		</form>

		<div class="row">
			<p class="text-center">
				Non hai un account? <a href="<%=Routes.BASE_URL + Routes.REGISTER%>">Registrati</a>
			</p>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
</body>
</html>