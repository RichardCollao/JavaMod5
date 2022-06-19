<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
String usuarioAtenticado = (String) session.getAttribute("usuarioAtenticado");
%>
<div class="container-fluid menu">
	<div class="container">
		<div class="row">
			<div class="col col-lg-10">
				<nav class="navbar navbar-expand-sm">
					<!-- Links -->
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="./inicio">Inicio</a></li>
						<li class="nav-item"><a class="nav-link" href="./listarcapacitaciones">Capacitaciones</a></li>
						<li class="nav-item"><a class="nav-link" href="./listarusuarios">Usuarios</a></li>
						<li class="nav-item"><a class="nav-link" href="./contacto">Contacto</a></li>
						<li class="nav-item"><a class="nav-link" href="./login">Login</a></li>
					</ul>
				</nav>
			</div>
			<div class="col col-lg-2">
				<%
				if (usuarioAtenticado != null) {
				%>
				<nav class="float-end navbar navbar-expand-sm">
					<ul class="navbar-nav">
						<li class="nav-item"><%=usuarioAtenticado%>&nbsp;<a href="./login?login=out">(Salir)</a></li>
					</ul>
				</nav>
				<%
				}
				%>
			</div>
		</div>
	</div>
</div>