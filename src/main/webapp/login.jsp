
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script src="js/funciones.js"></script>
<title>..:: GrupalM5AE1.0 ::..</title>
</head>

<body>

	<%@ include file="./menu.jsp"%>


	<main>

		<div class="d-flex justify-content-center">
			<form action="./login" method="post">
				<div class="mb-3">
					<label for="exampleInputEmail1" class="form-label">Email address</label> <input name="usuario" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
					<div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
				</div>
				<div class="mb-3">
					<label for="exampleInputPassword1" class="form-label">Password</label> <input name="clave" type="password" class="form-control" id="exampleInputPassword1">
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
		</div>

	</main>

	<%@ include file="./footer.jsp"%>

</body>

</html>




