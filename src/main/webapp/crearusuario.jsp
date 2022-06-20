<main>
	<div class="container">
		<div style="margin: 15px auto; width: 500px; padding: 30px">
			<h2 style="text-align: center;">Crear usuario</h2>
			<form class="mx-1 mx-md-4">
				<div class="d-flex flex-row align-items-center mb-4">
					<i class="fas fa-user fa-lg me-3 fa-fw"></i>
					<div class="form-outline flex-fill mb-0">
						<input type="text" id="form3Example1c" class="form-control" /> <label class="form-label" for="form3Example1c">Nombre</label>
					</div>
				</div>

				<div class="d-flex flex-row align-items-center mb-4">
					<i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
					<div class="form-outline flex-fill mb-0">
						<input type="email" id="form3Example3c" class="form-control" /> <label class="form-label" for="form3Example3c">Correo</label>
					</div>
				</div>

				<div class="d-flex flex-row align-items-center mb-4">
					<i class="fas fa-lock fa-lg me-3 fa-fw"></i>
					<div class="form-outline flex-fill mb-0">
						<input type="password" id="form3Example4c" class="form-control" /> <label class="form-label" for="form3Example4c">Clave</label>
					</div>
				</div>

				<div class="d-flex flex-row align-items-center mb-4">
					<i class="fas fa-key fa-lg me-3 fa-fw"></i>
					<div class="form-outline flex-fill mb-0">
						<input type="password" id="form3Example4cd" class="form-control" /> <label class="form-label" for="form3Example4cd">Repite tu clave</label>
					</div>
				</div>

				<div class="form-check d-flex justify-content-center mb-5">
					<input class="form-check-input me-2" type="checkbox" value="" id="form2Example3c" /> <label class="form-check-label" for="form2Example3"> Estoy de acuerdo con los <a href="#!">Terminos
							de servicio</a>
					</label>
				</div>

				<div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
					<button type="button" class="btn btn-primary btn-lg">Registrate</button>
				</div>

			</form>
		</div>
	</div>
</main>
<script>
	document.addEventListener("DOMContentLoaded", function() {
		document.getElementById("formulario").addEventListener("submit",
				validarFormulario);
	});
</script>