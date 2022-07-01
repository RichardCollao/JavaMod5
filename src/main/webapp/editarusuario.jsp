<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="clases.Form"%>

<main>
	<div class="container">
		<h2>Editar usuario</h2>
		<form id="formulario" action="./editarusuario" method="post" style="max-width: 1000px">
			<div class="row">
				<div class="col">
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

				</div>
				<div class="col">
					<div class="mt-3">
						<label class="form-label">Tipo de usuario:</label> <select id="tipo_usuario" class="form-select" name="tipo" data-selected="<%=Form.getParameter("tipo")%>" onchange="changeType(this)">
							<option value="0">Administrativo</option>
							<option value="1">Cliente</option>
							<option value="2">Profesional</option>
						</select>
						<div id="dynamic_content"></div>
					</div>
				</div>
			</div>
			<!-- Submit button -->
			<div class="mt-5" style="text-align: center;">
				<button type="submit" class="btn btn-primary" style="width: 40%">Enviar</button>
			</div>
		</form>
	</div>
</main>

<template id="administrativo">
	<div class="mt-3">
		<label class="form-label">Area:</label> <input type="text" class="form-control" name="area" value="<%=Form.getParameter("area")%>" />
	</div>
	<div class="mt-3">
		<label class="form-label">Experiencia previa:</label> <input type="text" class="form-control" name="experiencia_previa" value="<%=Form.getParameter("experiencia_previa")%>" />
	</div>
</template>
<template id="cliente">
	<div class="mt-3">
		<label class="form-label">Nombres:</label> <input type="text" class="form-control" name="nombres" value="<%=Form.getParameter("nombres")%>" />
	</div>
	<div class="mt-3">
		<label class="form-label">Apellidos:</label> <input type="text" class="form-control" name="apellidos" value="<%=Form.getParameter("apellidos")%>" />
	</div>
	<div class="mt-3">
		<label class="form-label">Telefono:</label> <input type="text" class="form-control" name="telefono" value="<%=Form.getParameter("telefono")%>" />
	</div>
	<div class="mt-3">
		<label class="form-label">AFP:</label> <input type="text" class="form-control" name="afp" value="<%=Form.getParameter("afp")%>" />
	</div>
	<div class="mt-3">
		<label class="form-label">Sistema de salud:</label> <select class="form-select" name="sistema_salud" data-selected="<%=Form.getParameter("sistema_salud")%>">
			<option value="1">Fonasa</option>
			<option value="2">Isapre</option>
		</select>
	</div>
	<div class="mt-3">
		<label class="form-label">Direccion:</label> <input type="text" class="form-control" name="direccion" value="<%=Form.getParameter("direccion")%>" />
	</div>
	<div class="mt-3">
		<label class="form-label">Comuna:</label> <input type="text" class="form-control" name="comuna" value="<%=Form.getParameter("comuna")%>" />
	</div>
</template>
<template id="profesional">
	<div class="mt-3">
		<label class="form-label">Titulo:</label> <input type="text" class="form-control" name="titulo" value="<%=Form.getParameter("titulo")%>" />
	</div>
	<div class="mt-3">
		<label class="form-label">Fecha ingreso:</label> <input type="text" class="form-control" name="fecha_ingreso" value="<%=Form.getParameter("fecha_ingreso")%>" />
	</div>
</template>

<script>
	window.addEventListener("load", function(event) {
		el = document.querySelector('#tipo_usuario');
		el.value = '<%=Form.getParameter("tipo")%>';
		changeType(el)
	}, false);

	function changeType(v) {
		switch (v.value) {
		case "0":
			setDynamicContent("administrativo");
			break;
		case "1":
			setDynamicContent("cliente");
			break;
		case "2":
			setDynamicContent("profesional");
			break;
		}
	}

	function setDynamicContent(id_template) {
		var dynamic_content = document.querySelector('#dynamic_content');
		var template = document.getElementById(id_template);
		dynamic_content.innerHTML = '';
		var nodeClone = document.importNode(template.content, true);
		dynamic_content.appendChild(nodeClone);
	}
</script>



