<%@ page language="java" import="routes.Routes"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
</head>
<body>
	<jsp:include page="../Components/NavBar.jsp" flush="true" />

	<div class="m-4 container">
		<div class="row">
			<div class="col">
				<h1 class="h1">Cassa</h1>
				<h6 class="h6 text-center text-muted">
					Stai per pagare <b>€ ${totalPrice}</b> a Winezone
				</h6>
			</div>
		</div>
	</div>

	<div class="container">
		<form action="<%=Routes.BASE_URL + Routes.PAYMENT%>" method="post">
			<div class="row mb-3">
				<div class="col">
					<label for="address" class="form-label">Indirizzo</label> <input
						name="address" type="text" class="form-control" id="address"
						placeholder="Viale delle Noci n.44" required />
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-8">
					<label for="cardNumber" class="form-label">Carta di credito</label>
					<input name="cardNumber" type="text" class="form-control"
						id="cardNumber" placeholder="4242-4242-4242-4242" required />
				</div>

				<div class="col">
					<label for="cvv" class="form-label">CVV</label> <input name="cvv"
						type="text" class="form-control" id="cvv" placeholder="333"
						required />
				</div>
			</div>

			<div class="row mt-4">
				<div class="col">
					<input type="submit" class="btn btn-lg btn-primary w-100 mx-auto"
						value="Paga" />
				</div>
			</div>
		</form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>

</body>
</html>