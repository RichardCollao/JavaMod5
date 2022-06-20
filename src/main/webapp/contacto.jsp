<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="controlador.Form"%>
<main>
	<div class="container">
		<div style="margin:15px auto;width:600px;padding:30px">
			<h2 style="text-align: center;">Contacto</h2>

			<form action="./contacto" method="post">
				<div class="mt-3">
					<label class="form-label">Nombre:</label> <input type="text" class="form-control" name="nombre"  value="<%=Form.getParameter("nombre")%>"/>
				</div>
				<div class="mt-3">
					<label class="form-label">Correo:</label> <input type="email" class="form-control" name="correo"  value="<%=Form.getParameter("correo")%>"/>
				</div>
				<div class="mt-3">
					<label for="contact_comment">Mensaje:</label>
					<textarea class="mt-2 form-control" rows="5" name="mensaje"><%=Form.getParameter("mensaje")%></textarea>
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
// 	document.addEventListener("DOMContentLoaded", function() {
// 		document.getElementById("formulario").addEventListener("submit",
// 				validarFormulario);
// 	});
</script>