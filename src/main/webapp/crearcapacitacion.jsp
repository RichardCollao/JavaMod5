<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="controlador.Form"%>

<main>
	<div class="container">
		<h2>Capacitacion</h2>
		<form id="formulario" action="./crearcapacitacion" method="post" style="max-width: 600px">
			<div class="mt-3">
				<label class="form-label">Rut empresa:</label> <input type="text" class="form-control" name="rut_empresa" value="<%=Form.getParameter("rut_empresa")%>" />
			</div>
			<div class="mt-3">
				<select class="form-select" name="dia" data-selected="<%=Form.getParameter("dia")%>">
					<option value="0">Lunes</option>
					<option value="1">Martes</option>
					<option value="2">Miercoles</option>
					<option value="3">Jueves</option>
					<option value="4">Viernes</option>
				</select>
			</div>
			<div class="mt-3">
				<label class="form-label">Hora:</label> <input type="text" class="form-control" name="hora" value="<%=Form.getParameter("hora")%>" />
			</div>
			<div class="mt-3">
				<label class="form-label">Lugar:</label> <input type="text" class="form-control" name="lugar" value="<%=Form.getParameter("lugar")%>" />
			</div>
			<div class="mt-3">
				<label class="form-label">Duracion:</label> <input type="text" class="form-control" name="duracion" value="<%=Form.getParameter("duracion")%>" />
			</div>
			<div class="mt-3">
				<label class="form-label">Cantidad de asistentes:</label> <input type="text" class="form-control" name="cantidad_asistentes" value="<%=Form.getParameter("cantidad_asistentes")%>" />
			</div>
			<!-- Submit button -->
			<div class="mt-3">
				<button type="submit" class="btn btn-primary">Enviar</button>
			</div>
		</form>
	</div>
</main>

<script type="text/javascript">
	window.onload = function() {
		// mantiene la seleccion del elemento <select>
		element = document.querySelector("select");
		if (element.getAttribute('data-selected') != "") {
			element.value = element.getAttribute('data-selected');
		}

	};
</script>