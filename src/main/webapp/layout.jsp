<%@ page import="java.util.ArrayList"%>
<%
String fileJsp = (String) request.getAttribute("fileJsp");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="en">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- Bootstrap CSS -->
<link type="text/css" href="libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="libs/bootstrap/js/bootstrap.min.js"></script>

<link href="css/main.css" rel="stylesheet" />
<!-- <script src="js/funciones.js"></script> -->
<title>..:: Modulo 5 Grupal ::..</title>
</head>
<body>
	<%@ include file="./menu.jsp"%>
	<%@ include file="./messages.jsp"%>
	<div>
		<jsp:include page="<%=fileJsp%>" flush="true" />
	</div>
	<%@ include file="./footer.jsp"%>
</body>
</html>