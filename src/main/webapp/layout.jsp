<%@ page import="java.util.ArrayList"%>
<% String fileJsp = (String) request.getAttribute("fileJsp");%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<html lang="en">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- Bootstrap CSS -->
<link href="libs/bootstrap-5.0.2-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="libs/bootstrap-5.0.2-dist/js/bootstrap.min.js"></script>
<link href="css/main.css"  rel="stylesheet" type="text/css" />
<!-- <script src="js/funciones.js"></script> -->
<title>..:: Modulo 5 Grupal  ::..</title>
</head>
<body>
	<%@ include file="./menu.jsp"%>
	<h2>Test</h2>
	<%@ include file="./messages.jsp"%>
<%-- 	<jsp:include page="<%= fileJsp %>" flush="true" /> --%>
	<%@ include file="./footer.jsp"%>
</body>
</html>