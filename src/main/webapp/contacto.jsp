<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="clases.Form"%>
<main>
	<div class="container">
		<div style="margin:15px auto;width:600px;padding:30px">
			<h2 style="text-align: center;">Contacto</h2>

			<form action="./contacto?action=send" method="post">
				<div class="mt-3">
					<label class="form-label">Nombre:</label> 
					<input type="text" class="form-control" name="nombre" id="nombre"  value="<%=Form.getParameter("nombre")%>"/>
				</div>
				<div class="mt-3">
					<label class="form-label">Correo:</label> 
					<input type="email" class="form-control" name="correo" id="correo"  value="<%=Form.getParameter("correo")%>"/>
				</div>
				<div class="mt-3">
					<label for="contact_comment">Mensaje:</label>
					<textarea class="mt-2 form-control" rows="5" name="mensaje" id="mensaje"><%=Form.getParameter("mensaje")%></textarea>
				</div>
				<div class="mt-3">
					<button type="reset" class="btn btn-secondary">Borrar</button>
					<button type="submit" class="btn btn-primary">Enviar</button>
				</div>
			</form>
		</div>
	</div>


	<script>
		
			document.querySelector('#nombre').addEventListener('blur', validateName);
			
			document.querySelector('#correo').addEventListener('blur', validateEmail);
			
			document.querySelector('#mensaje').addEventListener('blur', validateMessage);
		
			const reSpaces = /^\S*$/;
		
			function validateName(e) {
			  const username = document.querySelector('#nombre');
			  const re = /^[a-zA-Z ,.-]+$/;
			  if (re.test(username.value)) {
			    username.classList.remove('is-invalid');
			    username.classList.add('is-valid');
			    return true;
			  } else {
			    username.classList.remove('is-valid');
		
			    username.classList.add('is-invalid');
			    return false;
			  }
			}
		
			function validateEmail(e) {
			  const email = document.querySelector('#correo');
			  const re = /^([a-zA-Z0-9_\-?\.?]){3,}@([a-zA-Z]){3,}\.([a-zA-Z]){2,5}$/;
		
			  if (reSpaces.test(email.value) && re.test(email.value)) {
			    email.classList.remove('is-invalid');
			    email.classList.add('is-valid');
		
			    return true;
			  } else {
			    email.classList.add('is-invalid');
			    email.classList.remove('is-valid');
		
			    return false;
			  }
			}		  

			function validateMessage(e) {
				  const mssg = document.querySelector('#mensaje');
				  const re = /[a-zA-Z0-9 ]$/;
		
				  if (re.test(mssg.value)) {
					  mssg.classList.remove('is-invalid');
					  mssg.classList.add('is-valid');
		
				    return true;
				  } else {
					  mssg.classList.add('is-invalid');
					  mssg.classList.remove('is-valid');
		
				    return false;
				  }
				}
		
			(function () {
			  const forms = document.querySelectorAll('.needs-validation');
		
			  for (let form of forms) {
			    form.addEventListener(
			      'submit',
			      function (event) {
			        if (
			          !form.checkValidity() ||
			          !validateEmail() ||
			          !validateName() ||
			          !validateMessage()
			        ) {
			          event.preventDefault();
			          event.stopPropagation();
			        } else {
			          form.classList.add('was-validated');
			        }
			      },
			      false
			    );
			  }
			})();
	
	</script>

</main>