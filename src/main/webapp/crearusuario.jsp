<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="controlador.Form"%>







<main>
	<div class="container">
		<h2>Nuevo usuario</h2>
		<form id="formulario" action="./crearusuario" method="post" style="max-width: 600px">
			<div class="mt-3">
				<label class="form-label">Correo:</label> <input type="email" class="form-control" name="correo" value="<%=Form.getParameter("correo")%>" />
			</div>

			<div class="mt-3">
				<label class="form-label">Contraseña:</label> <input type="password" class="form-control" name="clave" value="" />
			</div>
			<div class="mt-3">
				<label class="form-label">Confirmar contraseña:</label> <input type="password" class="form-control" name="clave2" value="" />
			</div>
			<div class="mt-3">
				<label class="form-label">Nombre de usuario:</label> <input type="text" class="form-control" name="nombre_usuario" value="<%=Form.getParameter("nombre_usuario")%>" />
			</div>
			<div class="mt-3">
				<label class="form-label">Fecha de nacimiento:</label> <input type="text" class="form-control" name="fecha_nacimiento" value="<%=Form.getParameter("fecha_nacimiento")%>" placeholder="2020-12-31" />
			</div>
			<div class="mt-3">
				<label class="form-label">R.U.N.:</label> <input type="text" class="form-control" name="run" value="<%=Form.getParameter("run")%>" placeholder="9000000-k" />
			</div>
			<div class="mt-3">
				<select class="form-select" name="tipo" data-selected="<%=Form.getParameter("tipo")%>">
					<option value="0">Administrativo</option>
					<option value="1">Cliente</option>
					<option value="2">Profesional</option>
				</select>
			</div>
			<!-- Submit button -->
			<div class="mt-3">
				<button type="submit" class="btn btn-primary">Enviar</button>
			</div>

		</form>
	</div>
</main>