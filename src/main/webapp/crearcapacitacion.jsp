<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="controlador.Form"%>
<%
Form form = (Form) session.getAttribute("form");
%>
<main>
	<div class="container">
		<h2>Capacitacion</h2>
		<form id="formulario" action="./crearcapacitacion" method="post" style="max-width: 600px">
			<div class="mt-3">
				<label class="form-label">Rut empresa:</label> <input type="text" class="form-control" name="rut_empresa" value="<%=form.getParameter("rut_empresa")%>"/>
			</div>
			<div class="mt-3">
				<select class="form-select" name="dia" value="1">
					<option value="1">Lunes</option>
					<option value="2">Martes</option>
					<option value="3">Miercoles</option>
					<option value="3">Jueves</option>
					<option value="3">Viernes</option>
				</select>
			</div>
			<div class="mt-3">
				<label class="form-label">Hora:</label> <input type="text" class="form-control" name="hora" value="<%=form.getParameter("hora")%>"/>
			</div>
			<div class="mt-3">
				<label class="form-label">Lugar:</label> <input type="text" class="form-control" name="lugar" value="<%=form.getParameter("lugar")%>"/>
			</div>
			<div class="mt-3">
				<label class="form-label">Duracion:</label> <input type="text" class="form-control" name="duracion" value="<%=form.getParameter("duracion")%>"/>
			</div>
			<div class="mt-3">
				<label class="form-label">Cantidad de asistentes:</label> <input type="text" class="form-control" name="cantidad_asistentes" value="<%=form.getParameter("cantidad_asistentes")%>" />
			</div>
			<!-- Submit button -->
			<div class="mt-3">
				<button type="submit" class="btn btn-primary">Enviar</button>
			</div>
		</form>
	</div>
</main>