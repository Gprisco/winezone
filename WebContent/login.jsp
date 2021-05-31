<%@ page language="java" import="routes.Routes, java.util.Date"
	contentType="text/html;"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/style.css" rel="stylesheet">
<link href="signin.css" rel="stylesheet">
<title>Sign In</title>
</head>
<body>
	<div class="container-fluid">
		<form id="signin-form" class="form-signin needs-validation"
			action="Login" method="post" novalidate>
			<h1 class="h3 mb-3 fw-normal text-center">Login</h1>

			<div class="form-floating">
				<input name="username" type="text"
					class="form-control <%=request.getParameter("notValid") != null ? "is-invalid" : ""%>"
					id="floatingInput" placeholder="username" autofocus> <label
					for="floatingInput">Username</label>
			</div>
			<div class="form-floating">
				<input name="password" type="password"
					class="form-control <%=request.getParameter("notValid") != null ? "is-invalid" : ""%>"
					id="floatingPassword" placeholder="Password"> <label
					for="floatingPassword">Password</label>
			</div>

			<%
			if (request.getParameter("notValid") != null) {
			%>
			<div class="alert alert-danger">Username o password errati</div>
			<%
			}
			%>

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
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
	<script src="./js/checkInputField.js"></script>
	<script>
		$("#signin-form").on("submit", (e) => {
			const usernameInputField = $("#floatingInput");
			const passwordInputField = $("#floatingPassword");
	
			let error = false;
			const username = usernameInputField.val();
			const password = passwordInputField.val();
	
			// Set error to true if err, else leave its value as it is
			checkInputField(username, usernameInputField, [true], (err) => error = err ? err : error);
			checkInputField(password, passwordInputField, [true], (err) => error = err ? err : error);
	
			if (error) e.preventDefault();
		});
	</script>
</body>
</html>