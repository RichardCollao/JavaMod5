<main>
	<div class="container">
		<h2>Contacto</h2>
		<p class="mt-3">Escribenos, te responderemos en menos de 24 hrs.</p>
		<form id="formulario" action="/action_page.php" style="max-width: 600px">
			<div class="mt-3">
				<label for="contact_name" class="form-label">Nombre:</label> <input type="text" class="form-control" id="contact_name" placeholder="" name="contact_name" />
			</div>
			<div class="mt-3">
				<label for="contact_email" class="form-label">Correo:</label> <input type="email" class="form-control" id="contact_email" placeholder="" name="contact_email" />
			</div>
			<div class="mt-3">
				<label for="contact_comment">Mensaje:</label>
				<textarea class="mt-2 form-control" rows="5" id="contact_comment" name="contact_comment"></textarea>
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
