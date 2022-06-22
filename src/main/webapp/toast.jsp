<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
String toast = (String) request.getAttribute("toast");
%>
<%if (toast != null) {%>
<div class="toast-container position-absolute p-3 top-0 end-0" id="toastPlacement" style="margin-top: 50px;">
	<div class="toast align-items-center" role="alert" aria-live="assertive" aria-atomic="true">
		<div class="d-flex">
			<div class="toast-body"><%=toast%></div>
			<button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
		</div>
	</div>
</div>
<%}%>