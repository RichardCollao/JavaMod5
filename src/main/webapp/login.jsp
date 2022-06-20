<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<main>
	<div class="container">
		<div style="margin: 15px auto; width: 500px; padding: 30px">
			<h2 style="text-align: center;">Login</h2>
			<form id="formulario" action="./login" method="post">
				<!-- Email input -->
				<div class="mt-3">
					<label class="form-label">Correo:</label> <input type="email" class="form-control" placeholder="" name="correo" value="<%=request.getParameter("correo")%>" />
				</div>

				<!-- Password input -->
				<div class="mt-3">
					<label class="form-label">Contraseña:</label> <input type="password" class="form-control" placeholder="" name="clave" value="" />
				</div>
				<!-- Checkbox -->
				<div class="mt-3">
					<div class="form-check">
						<label class="form-check-label" for="form1Example3"> Remember me </label><input class="form-check-input" type="checkbox" value="" id="form1Example3" checked />
					</div>
				</div>
				<!-- Submit button -->
				<div class="mt-3">
					<button type="submit" class="btn btn-primary">Enviar</button>
				</div>
				<div class="mt-3">
					Don't have an account? <a href="#!">Register here</a> &nbsp;&nbsp;&nbsp;<a href="#!">Forgot password?</a>
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