<%@ page import="java.util.ArrayList"%>
<%@ page import="entities.Capacitacion"%>

<%
ArrayList<Capacitacion> capacitacionesList = new ArrayList<Capacitacion>();
capacitacionesList = (ArrayList<Capacitacion>) request.getAttribute("capacitacionesList");
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

	<section class="vh-100" style="background-color: #eee;">
		<div class="container mt-3">
			<h2>Lista de capacitacions</h2>
			<hr />
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>RUT empresa</th>
						<th>Dia</th>
						<th>Hora</th>
						<th>Lugar</th>
						<th>Duracion</th>
						<th>Cantidad de asistentes</th>
						<th>opciones</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Capacitacion capacitacion : capacitacionesList) {
					%>
					<tr>
						<td><%= capacitacion.getRutEmpresa()%></td>
						<td><%= capacitacion.getDia()%></td>
						<td><%= capacitacion.getHora()%></td>
						<td><%= capacitacion.getLugar()%></td>
						<td><%= capacitacion.getDuracion()%></td>
						<td><%= capacitacion.getCantidadAsistentes()%></td>
						<td>[Borrar]&nbsp;[Mostrar]</td>
						<%
						}
						%>
					</tr>
				</tbody>
			</table>
		</div>
	</section>
	<%@ include file="./footer.jsp"%>
</body>
</html>
