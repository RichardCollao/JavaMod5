<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body"></div>
		</div>
	</div>
</div>




<script type="text/javascript">
	window.onload = function() {

		var modal = new bootstrap.Modal(
				document.getElementById('exampleModal'), {
					keyboard : false
				})

		// 		var myModalEl = document.getElementById('exampleModal')
		// 		var modal = bootstrap.Modal.getInstance(myModalEl);

		console.log("------");
		console.log(modal);
		modal.show();
	};
</script>