<%@ page import="java.util.ArrayList"%>
<%@ page import="entities.Usuario"%>

<%
ArrayList<Usuario> usuariosList = (ArrayList<Usuario>) request.getAttribute("usuariosList");
%>

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

	<section>
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
						<td>[Borrar]&nbsp;[Mostrar]</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</section>
	<%@ include file="./footer.jsp"%>
</body>
</html>
