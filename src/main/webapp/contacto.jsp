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
		<div class="container">
			<h2>Contacto</h2>
			<p class="mt-3">Escribenos, te responderemos en menos de 24 hrs.</p>
			<form id="formulario" action="/action_page.php"
				style="max-width: 600px">
				<div class="mt-3">
					<label for="contact_name" class="form-label">Nombre:</label> <input
						type="text" class="form-control" id="contact_name" placeholder=""
						name="contact_name" />
				</div>
				<div class="mt-3">
					<label for="contact_email" class="form-label">Correo:</label> <input
						type="email" class="form-control" id="contact_email"
						placeholder="" name="contact_email" />
				</div>
				<div class="mt-3">
					<label for="contact_comment">Mensaje:</label>
					<textarea class="mt-2 form-control" rows="5" id="contact_comment"
						name="contact_comment"></textarea>
				</div>
				<div class="mt-3">
					<button type="reset" class="btn btn-secondary">Borrar</button>
					<button type="submit" class="btn btn-primary">Enviar</button>
				</div>
			</form>
		</div>
	</main>

	<%@ include file="./footer.jsp"%>
	<script>
		document.addEventListener("DOMContentLoaded", function() {
			document.getElementById("formulario").addEventListener("submit",
					validarFormulario);
		});
	</script>
</body>

</html>