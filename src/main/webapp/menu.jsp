<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
String nameUserAuth = (String) request.getAttribute("nameUserAuth");
%>
<div class="container-fluid menu">
	<div class="container">
		<div class="row">
			<div class="col col-lg-10">
				<nav class="navbar navbar-expand-sm">
					<!-- Links -->
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link" href="./inicio">Inicio</a></li>
						<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false"> Capacitaciones </a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="./crearcapacitacion">Crear capacitacion</a></li>
								<li><a class="dropdown-item" href="./listarcapacitaciones">Listar capacitaciones</a></li>
							</ul>
						</li>
						<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false"> Usuarios </a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="./crearusuario">Crear usuario</a></li>
								<li><a class="dropdown-item" href="./listarusuarios">Listar usuarios</a></li>
							</ul>
						</li>
						<li class="nav-item"><a class="nav-link" href="./contacto">Contacto</a></li>
						<li class="nav-item"><a class="nav-link" href="./login">Login</a></li>
					</ul>
				</nav>
			</div>
			<div class="col col-lg-2">
				<%
				if (nameUserAuth != null) {
				%>
				<nav class="float-end navbar navbar-expand-sm">
					<ul class="navbar-nav">
						<li class="nav-item"><%=nameUserAuth%>&nbsp;<a href="./login?login=out">(Salir)</a></li>
					</ul>
				</nav>
				<%
				}
				%>
			</div>
		</div>
	</div>
</div>