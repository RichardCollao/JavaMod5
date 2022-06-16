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

	<main>
		<div class="container">
			<h2>Autenticacion</h2>
			<%
			String usuario = (String) request.getAttribute("usuario");
			String clave = (String) request.getAttribute("clave");
			if (usuario.equals("admin")) {
			%>
			<p>
				El usuario:
				<%=usuario%>
				corresponde
			</p>
			<% } else { %>
			<p>
				El usuario:
				<%=usuario%>
				no corresponde
			</p>
			<% } if (clave.equals("1234")) { %>
			<p>
				La clave:
				<%=clave%>
				corresponde
			</p>
			<% } else { %>
			<p>
				La clave:<%=clave%>
				no corresponde
			</p>
			<% } %>
		</div>
	</main>

	<%@ include file="./footer.jsp"%></body>

</html>