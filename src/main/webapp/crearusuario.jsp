<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="clases.Form"%>

<main>
	<div class="container">
		<h2>Crear usuario</h2>
		<form class="needs-validation" id="formulario" action="./crearusuario" method="post" style="max-width: 1000px" novalidate>
			<div class="row">
				<div class="col">
					<div class="mt-3 position-relative">
					
						<label for="validationServer01" class="form-label">Correo:</label> 
						<input type="email" class="form-control" name="correo" id="correo" value="<%=Form.getParameter("correo")%>" required />
								<div class="invalid-feedback">
       								 Por favor ingrese un email correcto.
     							 </div>
     							 
					</div>
					<div class="mt-3">
						<label class="form-label">Contraseña:</label> 
						<input type="password" class="form-control" name="clave" id="clave" value="" required />
						<div class="invalid-feedback">Por favor asegurese que su contraseña contenga al menos 6 carácterres</div>
					</div>
					<div class="mt-3">
						<label class="form-label">Confirmar contraseña:</label> 
						<input type="password" class="form-control" name="clave2" id="clave2" value="" required/>
						<div class="invalid-feedback">La contraseña no concuerdan</div>
					</div>
					<div class="mt-3">
						<label class="form-label">Nombre de usuario:</label>
						 <input type="text" class="form-control" name="nombre_usuario" id="nombre_usuario" value="<%=Form.getParameter("nombre_usuario")%>" required/>
						 <div class="invalid-feedback">Ingrese nombre usuario válido</div>
					</div>
					
					<div class="mt-3">
						<label class="form-label">Fecha de nacimiento:</label> 
						<input type="text" class="form-control" name="fecha_nacimiento" id="fecha_nacimiento" value="<%=Form.getParameter("fecha_nacimiento")%>" placeholder="AAAA-MM-DD" required/>
						<div class="invalid-feedback">Ingrese fecha válido</div>
					</div>
					<div class="mt-3">
						<label class="form-label">R.U.N.:</label> 
						<input type="text" class="form-control" name="run" id="run" value="<%=Form.getParameter("run")%>" placeholder="9000000-k" required/>
						<div class="invalid-feedback">Ingrese R.U.N. válido</div>
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
		<label class="form-label needs-validation">Area:</label> 
		<input type="text" class="form-control" name="area" id="area" value="<%=Form.getParameter("area")%>" required/>
		<div class="invalid-feedback">Ingrese Area válida</div>
	</div>
	<div class="mt-3">
		<label class="form-label">Experiencia previa:</label> 
		<input type="text" class="form-control" name="experiencia_previa" id="experiencia_previa" value="<%=Form.getParameter("experiencia_previa")%>" placeholder="2 años" required/>
		<div class="invalid-feedback">Ingrese experiencia previa válido</div>
	</div>
</template>
<template id="cliente">
	<div class="mt-3">
		<label class="form-label">Nombres:</label> <input type="text" class="form-control" name="nombres" id="nombres" value="<%=Form.getParameter("nombres")%>"  required/>
	</div>
	<div class="mt-3">
		<label class="form-label">Apellidos:</label> <input type="text" class="form-control" name="apellidos" id="apellidos" value="<%=Form.getParameter("apellidos")%>"  required/>
	</div>
	<div class="mt-3">
		<label class="form-label">Telefono:</label> <input type="text" class="form-control" name="telefono" id="telefono" value="<%=Form.getParameter("telefono")%>"  required/>
	</div>
	<div class="mt-3">
		<label class="form-label">AFP:</label> <input type="text" class="form-control" name="afp" id="afp" value="<%=Form.getParameter("afp")%>"  required/>
	</div>
	<div class="mt-3">
		<label class="form-label">Sistema de salud:</label> <select class="form-select" name="sistema_salud" data-selected="<%=Form.getParameter("sistema_salud")%>">
			<option value="1">Fonasa</option>
			<option value="2">Isapre</option>
		</select>
	</div>
	<div class="mt-3">
		<label class="form-label">Direccion:</label> <input type="text" class="form-control" name="direccion" id="direccion" value="<%=Form.getParameter("direccion")%>"  required/>
	</div>
	<div class="mt-3">
		<label class="form-label">Comuna:</label> <input type="text" class="form-control" name="comuna" id="comuna" value="<%=Form.getParameter("comuna")%>"  required/>
	</div>
</template>
<template id="profesional">
	<div class="mt-3">
		<label class="form-label">Titulo:</label> <input type="text" class="form-control" name="titulo" id="titulo" value="<%=Form.getParameter("titulo")%>"  required/>
	</div>
	<div class="mt-3">
		<label class="form-label">Fecha ingreso:</label> <input type="text" class="form-control" name="fecha_ingreso" id="fecha_ingreso" value="<%=Form.getParameter("fecha_ingreso")%>"  required/>
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
			validaAdministrativo();
			break;
		case "1":
			setDynamicContent("cliente");
			validaCliente();
			break;
		case "2":
			setDynamicContent("profesional");
			validaProfesional();
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
	
	function validaAdministrativo(){
		
		document.querySelector('#area').addEventListener('blur', validateArea);
		
		document.querySelector('#experiencia_previa').addEventListener('blur', validateExperience);
		
		document.querySelector('#correo').addEventListener('blur', validateEmail);

		document.querySelector('#clave').addEventListener('blur', validatePassword);

		document.querySelector('#clave2').addEventListener('blur', validatePassword2);

		document.querySelector('#nombre_usuario').addEventListener('blur', validateUsername);

		document.querySelector('#fecha_nacimiento').addEventListener('blur', validateDate);

		document.querySelector('#run').addEventListener('blur', validateRun);

		const reSpaces = /^\S*$/;

		function validateUsername(e) {
		  const username = document.querySelector('#nombre_usuario');
		  const re = /^[a-zA-Z0-9\_\-]{2,16}$/;
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

		function validatePassword() {
		  const password = document.querySelector('#clave');
		  const re = /^.{6,12}$/; 
			
				  if (re.test(password.value) && reSpaces.test(password.value)) {
				    password.classList.remove('is-invalid');
				    password.classList.add('is-valid');
				
				    return true;
				  } else {
				    password.classList.add('is-invalid');
				    password.classList.remove('is-valid');
				
				    return false;
				  }
		  }
		  
		function validatePassword2() {
			  const password = document.querySelector('#clave');
			  const password2 = document.querySelector('#clave2');

			
			 	 if(password.value !== password2.value){
					    password2.classList.add('is-invalid');
					    password2.classList.remove('is-valid');
					
					    return false;
				  } else {
					    password2.classList.remove('is-invalid');
					    password2.classList.add('is-valid');
					
					    return true;
				  }
				
		}
		  
		  
		function validateDate(e) {
			  const date = document.querySelector('#fecha_nacimiento');
			  const re = /^(19|20)\d\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$/;

			  if (re.test(date.value)) {
			    date.classList.remove('is-invalid');
			    date.classList.add('is-valid');

			    return true;
			  } else {
			    date.classList.add('is-invalid');
			    date.classList.remove('is-valid');

			    return false;
			  }
			}
			
		function validateRun(e) {
			  const run = document.querySelector('#run');
			  const re = /^[0-9]+-[0-9kK]{1}$/;

			  if (re.test(run.value)) {
			    run.classList.remove('is-invalid');
			    run.classList.add('is-valid');

			    return true;
			  } else {
			    run.classList.add('is-invalid');
			    run.classList.remove('is-valid');

			    return false;
			  }
			}
		
		function validateArea(e) {
			  const labor_field = document.querySelector('#area');
			  const re = /[a-zA-Z ]{2,99}/;

			  if (re.test(labor_field.value)) {
				  labor_field.classList.remove('is-invalid');
				  labor_field.classList.add('is-valid');

			    return true;
			  } else {
				  labor_field.classList.add('is-invalid');
				  labor_field.classList.remove('is-valid');

			    return false;
			  }
			}
		
		function validateExperience(e) {
			  const experience_time = document.querySelector('#experiencia_previa');
			  const re = /[0-9]{1,2} años?$/;

			  if (re.test(experience_time.value)) {
				  experience_time.classList.remove('is-invalid');
				  experience_time.classList.add('is-valid');

			    return true;
			  } else {
				  experience_time.classList.add('is-invalid');
				  experience_time.classList.remove('is-valid');

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
		          !validateUsername() ||
		          !validatePassword() ||
		          !validatePassword2() ||
		          !validateDate() ||
		          !validateRun() ||
		          !validateArea() ||
		          !validateExperience() 
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
	}
	
	function validaCliente(){
		
		document.querySelector('#nombres').addEventListener('blur', validateName);
		
		document.querySelector('#apellidos').addEventListener('blur', validateLastname);
		
		document.querySelector('#telefono').addEventListener('blur', validatePhone);
		
		document.querySelector('#afp').addEventListener('blur', validateAfp);
		
		document.querySelector('#direccion').addEventListener('blur', validateAdress);
		
		document.querySelector('#comuna').addEventListener('blur', validateCity);
		
		document.querySelector('#correo').addEventListener('blur', validateEmail);

		document.querySelector('#clave').addEventListener('blur', validatePassword);

		document.querySelector('#clave2').addEventListener('blur', validatePassword2);

		document.querySelector('#nombre_usuario').addEventListener('blur', validateUsername);

		document.querySelector('#fecha_nacimiento').addEventListener('blur', validateDate);

		document.querySelector('#run').addEventListener('blur', validateRun);

		const reSpaces = /^\S*$/;

		function validateUsername(e) {
		  const username = document.querySelector('#nombre_usuario');
		  const re = /^[a-zA-Z0-9\_\-]{2,16}$/;
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

		function validatePassword() {
		  const password = document.querySelector('#clave');
		  const re = /^.{6,12}$/; 
			
				  if (re.test(password.value) && reSpaces.test(password.value)) {
				    password.classList.remove('is-invalid');
				    password.classList.add('is-valid');
				
				    return true;
				  } else {
				    password.classList.add('is-invalid');
				    password.classList.remove('is-valid');
				
				    return false;
				  }
		  }
		  
		function validatePassword2() {
			  const password = document.querySelector('#clave');
			  const password2 = document.querySelector('#clave2');

			
			 	 if(password.value !== password2.value){
					    password2.classList.add('is-invalid');
					    password2.classList.remove('is-valid');
					
					    return false;
				  } else {
					    password2.classList.remove('is-invalid');
					    password2.classList.add('is-valid');
					
					    return true;
				  }
				
		}
		  
		  
		function validateDate(e) {
			  const date = document.querySelector('#fecha_nacimiento');
			  const re = /^(19|20)\d\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$/;

			  if (re.test(date.value)) {
			    date.classList.remove('is-invalid');
			    date.classList.add('is-valid');

			    return true;
			  } else {
			    date.classList.add('is-invalid');
			    date.classList.remove('is-valid');

			    return false;
			  }
			}
			
		function validateRun(e) {
			  const run = document.querySelector('#run');
			  const re = /^[0-9]+-[0-9kK]{1}$/;

			  if (re.test(run.value)) {
			    run.classList.remove('is-invalid');
			    run.classList.add('is-valid');

			    return true;
			  } else {
			    run.classList.add('is-invalid');
			    run.classList.remove('is-valid');

			    return false;
			  }
			}
		
		function validateName(e) {
			  const name = document.querySelector('#nombres');
			  const re = /[a-zA-Z ]{3,60}/;

			  if (re.test(name.value)) {
				  name.classList.remove('is-invalid');
				  name.classList.add('is-valid');

			    return true;
			  } else {
				  name.classList.add('is-invalid');
				  name.classList.remove('is-valid');

			    return false;
			  }
			}
		
		function validateLastname(e) {
			  const lastname = document.querySelector('#apellidos');
			  const re = /[a-zA-Z ]{3,60}/;

			  if (re.test(lastname.value)) {
				  lastname.classList.remove('is-invalid');
				  lastname.classList.add('is-valid');

			    return true;
			  } else {
				  lastname.classList.add('is-invalid');
				  lastname.classList.remove('is-valid');

			    return false;
			  }
			}
		
		
		function validatePhone(e) {
			  const phone = document.querySelector('#telefono');
			  const re = /[0-9]{8,15}/;

			  if (re.test(phone.value)) {
				  phone.classList.remove('is-invalid');
				  phone.classList.add('is-valid');

			    return true;
			  } else {
				  phone.classList.add('is-invalid');
				  phone.classList.remove('is-valid');

			    return false;
			  }
			}
		
		function validateAfp(e) {
			  const afp = document.querySelector('#afp');
			  const re = /[a-zA-Z]{3,30}/;

			  if (re.test(afp.value)) {
				  afp.classList.remove('is-invalid');
				  afp.classList.add('is-valid');

			    return true;
			  } else {
				  afp.classList.add('is-invalid');
				  afp.classList.remove('is-valid');

			    return false;
			  }
			}
		
		function validateAdress(e) {
			  const adress = document.querySelector('#direccion');
			  const re = /^[#.0-9a-zA-Z\s,-]+$/;

			  if (re.test(adress.value)) {
				  adress.classList.remove('is-invalid');
				  adress.classList.add('is-valid');

			    return true;
			  } else {
				  adress.classList.add('is-invalid');
				  adress.classList.remove('is-valid');

			    return false;
			  }
			}
		
		function validateCity(e) {
			  const city = document.querySelector('#comuna');
			  const re = /[[a-zA-Z ]{4,30}$/;

			  if (re.test(city.value)) {
				  city.classList.remove('is-invalid');
				  city.classList.add('is-valid');

			    return true;
			  } else {
				  city.classList.add('is-invalid');
				  city.classList.remove('is-valid');

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
		          !validateUsername() ||
		          !validatePassword() ||
		          !validatePassword2() ||
		          !validateDate() ||
		          !validateRun() ||
		          !validateArea() ||
		          !validateName()||
		          !validateLastname()||
		          !validatePhone()||
		          !validateAfp()||
		          !validateAdress()||
		          !validateCity()      
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
	}
	
function validaProfesional(){
		
		document.querySelector('#titulo').addEventListener('blur', validateDegree);
		
		document.querySelector('#fecha_ingreso').addEventListener('blur', validateDateIn);
		
		document.querySelector('#correo').addEventListener('blur', validateEmail);

		document.querySelector('#clave').addEventListener('blur', validatePassword);

		document.querySelector('#clave2').addEventListener('blur', validatePassword2);

		document.querySelector('#nombre_usuario').addEventListener('blur', validateUsername);

		document.querySelector('#fecha_nacimiento').addEventListener('blur', validateDate);

		document.querySelector('#run').addEventListener('blur', validateRun);

		const reSpaces = /^\S*$/;

		function validateUsername(e) {
		  const username = document.querySelector('#nombre_usuario');
		  const re = /^[a-zA-Z0-9\_\-]{2,16}$/;
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

		function validatePassword() {
		  const password = document.querySelector('#clave');
		  const re = /^.{6,12}$/; 
			
				  if (re.test(password.value) && reSpaces.test(password.value)) {
				    password.classList.remove('is-invalid');
				    password.classList.add('is-valid');
				
				    return true;
				  } else {
				    password.classList.add('is-invalid');
				    password.classList.remove('is-valid');
				
				    return false;
				  }
		  }
		  
		function validatePassword2() {
			  const password = document.querySelector('#clave');
			  const password2 = document.querySelector('#clave2');

			
			 	 if(password.value !== password2.value){
					    password2.classList.add('is-invalid');
					    password2.classList.remove('is-valid');
					
					    return false;
				  } else {
					    password2.classList.remove('is-invalid');
					    password2.classList.add('is-valid');
					
					    return true;
				  }
				
		}
		  
		  
		function validateDate(e) {
			  const date = document.querySelector('#fecha_nacimiento');
			  const re = /^(19|20)\d\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$/;

			  if (re.test(date.value)) {
			    date.classList.remove('is-invalid');
			    date.classList.add('is-valid');

			    return true;
			  } else {
			    date.classList.add('is-invalid');
			    date.classList.remove('is-valid');

			    return false;
			  }
			}
			
		function validateRun(e) {
			  const run = document.querySelector('#run');
			  const re = /^[0-9]+-[0-9kK]{1}$/;

			  if (re.test(run.value)) {
			    run.classList.remove('is-invalid');
			    run.classList.add('is-valid');

			    return true;
			  } else {
			    run.classList.add('is-invalid');
			    run.classList.remove('is-valid');

			    return false;
			  }
			}
		
		function validateDegree(e) {
			  const degree = document.querySelector('#titulo');
			  const re = /[a-zA-Z ]{5,40}/;

			  if (re.test(degree.value)) {
				  degree.classList.remove('is-invalid');
				  degree.classList.add('is-valid');

			    return true;
			  } else {
				  degree.classList.add('is-invalid');
				  degree.classList.remove('is-valid');

			    return false;
			  }
			}
		
		function validateDateIn(e) {
			  const date_in = document.querySelector('#fecha_ingreso');
			  const re = /^(19|20)\d\d[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$/;

			  if (re.test(date_in.value)) {
				  date_in.classList.remove('is-invalid');
				  date_in.classList.add('is-valid');

			    return true;
			  } else {
				  date_in.classList.add('is-invalid');
				  date_in.classList.remove('is-valid');

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
		          !validateUsername() ||
		          !validatePassword() ||
		          !validatePassword2() ||
		          !validateDate() ||
		          !validateRun() ||
		          !validateDegree() ||
		          !validateDateIn() 
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
	}
		
</script>
