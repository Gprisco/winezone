<%@ page language="java" import="routes.Routes"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<title>Checkout</title>
</head>
<body>
	<jsp:include page="../Components/NavBar.jsp" flush="true" />

	<div class="my-4 container">
		<div class="row">
			<div class="col mx-auto">
				<h1 class="h1 text-center mx-auto">Cassa</h1>
				<h6 class="h6 text-center text-muted">
					Stai per pagare <b>€ ${totalPrice}</b> a Winezone
				</h6>
			</div>
		</div>
	</div>

	<div class="container">
		<form id="checkout-form"
			action="<%=Routes.BASE_URL + Routes.PAYMENT%>" method="post">
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
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
	<script src="../js/checkInputField.js"></script>
	<script>
		function validateCardNumber(cb) {
			const cardNumberInputField = $("#cardNumber");
			const cardNumber = cardNumberInputField.val();
			checkInputField(cardNumber, cardNumberInputField, [cardNumber.trim().length == 16], cb);
		}
		
		function validateAddress(cb) {
			const addressInputField = $("#address");
			const address = addressInputField.val();
			checkInputField(address, addressInputField, [address.trim().length > 3], (err) => error = err ? err : error);
		}
		
		function validateCvv(cb) {
			const cvvInputField = $("#cvv")
			const cvv = cvvInputField.val();
			checkInputField(cvv, cvvInputField, [cvv.trim().length === 3], (err) => error = err ? err : error);
		}
	
		function validateCheckoutForm() {
			let error = false;
			
			// Set error to true if err, else leave its value as it is
			const handleError = (err) => error = err ? err : error;
	
			validateCardNumber(handleError);
			validateAddress(handleError);
			validateCvv(handleError);
			
			return { error };
		}
	
		$("#checkout-form").on("submit", (e) => {
			if(validateCheckoutForm().error) e.preventDefault(); 
		});
		
		$("#cardNumber").on("input", validateCardNumber);
		$("#address").on("input", validateAddress);
		$("#cvv").on("input", validateCvv);
	</script>
</body>
</html>