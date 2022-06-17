<%@ page import="java.util.ArrayList"%>
<%
ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");
if (!errors.isEmpty()) {
%>
<section>
	<div class="container">
		<div class="alert alert-light" role="alert" style="max-width: 800px; margin: 15px auto; border: 1px solid silver;">
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
		</div>
	</div>
</section>
<%}%>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">...</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>




<script type="text/javascript">
	window.onload = function() {
		var myModal = document.getElementById('exampleModal')

		myModal.show();
	};
</script>


