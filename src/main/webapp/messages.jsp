<%@ page import="java.util.ArrayList"%>
<%
ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");
if (!errors.isEmpty()) {
%>
<section>
	<div class="container" style="margin-top: 30px;">
		<div class="alert alert-light alert-dismissible fade show" role="alert" style="max-width: 800px; margin: 15px auto; border: 1px solid silver;">
			<h6>Se han encontrado los siguientes errores:</h6>
			<hr />
			<ul>
				<%
				for (String error : errors) {
				%>
				<li><%=error%></li>
				<%
				}
				%>
			</ul>
			<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
	</div>
</section>
<%}%>