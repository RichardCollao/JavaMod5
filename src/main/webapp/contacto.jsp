<main>
	<div class="container">
		<div style="margin:15px auto;width:600px;padding:30px">
			<h2 style="text-align: center;">Contacto</h2>

			<form id="formulario" action="./contacto" method="post">
				<div class="mt-3">
					<label class="form-label">Nombre:</label> <input type="text" class="form-control" placeholder="" name="nombre" />
				</div>
				<div class="mt-3">
					<label class="form-label">Correo:</label> <input type="email" class="form-control"  placeholder="" name="correo" />
				</div>
				<div class="mt-3">
					<label for="contact_comment">Mensaje:</label>
					<textarea class="mt-2 form-control" rows="5" name="mensaje"></textarea>
				</div>
				<div class="mt-3">
					<button type="reset" class="btn btn-secondary">Borrar</button>
					<button type="submit" class="btn btn-primary">Enviar</button>
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