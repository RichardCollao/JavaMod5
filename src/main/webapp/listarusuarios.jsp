<%@ page import="java.util.ArrayList"%>
<%@ page import="entities.Usuario"%>

<%
ArrayList<Usuario> usuariosList = (ArrayList<Usuario>) request.getAttribute("usuariosList");
%>

<main>
	<div class="container mt-3">
		<h2>Lista de usuarios</h2>
		<hr />
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Nombre de usuario</th>
					<th>RUN</th>
					<th>Correo</th>
					<th>Fecha de nacimiento</th>
					<th>Tipo</th>
					<th>opciones</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Usuario usuario : usuariosList) {
				%>
				<tr>
					<td><%=usuario.getNombreUsuario()%></td>
					<td><%=usuario.getRun()%></td>
					<td><%=usuario.getCorreo()%></td>
					<td><%=usuario.getFechaNacimiento()%></td>
					<td><%=usuario.getType()%></td>
					<td><span class="float-end">[Borrar]</span>&nbsp;<span>[Mostrar]</span></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</main>