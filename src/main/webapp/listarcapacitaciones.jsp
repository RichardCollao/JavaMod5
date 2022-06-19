<%@ page import="java.util.ArrayList"%>
<%@ page import="modelo.entities.Capacitacion"%>

<%
ArrayList<Capacitacion> capacitacionesList = (ArrayList<Capacitacion>) request.getAttribute("capacitacionesList");
%>

<main>
	<div class="container mt-3">
		<h2>Lista de capacitaciones</h2>
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
					<td><%=capacitacion.getRutEmpresa()%></td>
					<td><%=capacitacion.getDia()%></td>
					<td><%=capacitacion.getHora()%></td>
					<td><%=capacitacion.getLugar()%></td>
					<td><%=capacitacion.getDuracion()%></td>
					<td><%=capacitacion.getCantidadAsistentes()%></td>
					<td><span class="float-end">[Borrar]</span>&nbsp;<span>[Mostrar]</span></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</main>

