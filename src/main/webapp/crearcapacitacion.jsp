<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="clases.Form"%>

<main>
	<div class="container">
		<h2>Nueva capacitacion</h2>
		<form id="formulario" action="./crearcapacitacion" method="post" style="max-width: 600px" novalidate>
			<div class="mt-3">
				<label class="form-label">Rut empresa:</label> 
				<input type="text" class="form-control" name="rut_empresa" id="rut_empresa" value="<%=Form.getParameter("rut_empresa")%>" placeholder="9000000-k" required />
				<div class="invalid-feedback">
       				 Por favor ingrese un RUT válido.
     			</div>
			</div>
			<div class="mt-3">
				<select class="form-select" name="dia" data-selected="<%=Form.getParameter("dia")%>">
					<option value="0">Lunes</option>
					<option value="1">Martes</option>
					<option value="2">Miercoles</option>
					<option value="3">Jueves</option>
					<option value="4">Viernes</option>
				</select>
			</div>
			<div class="mt-3">
				<label class="form-label">Hora:</label> 
				<input type="text" class="form-control" name="hora" id="hora" value="<%=Form.getParameter("hora")%>" placeholder="23:59:59" required />
				<div class="invalid-feedback">
       				 Por favor ingrese hora válida.
     			</div>
			</div>
			<div class="mt-3">
				<label class="form-label">Lugar:</label> <input type="text" class="form-control" name="lugar" id="lugar" value="<%=Form.getParameter("lugar")%>" required />
				<div class="invalid-feedback">
       				 Por favor ingrese lugar válido.
     			</div>
			</div>
			<div class="mt-3">
				<label class="form-label">Duracion:</label> 
				<input type="text" class="form-control" name="duracion" id="duracion" value="<%=Form.getParameter("duracion")%>" placeholder="1:30 horas" required/>
				<div class="invalid-feedback">
       				 Por favor ingrese duración válida.
     			</div>
			</div>
			<div class="mt-3">
				<label class="form-label">Cantidad de asistentes:</label>
				<input type="text" class="form-control" name="cantidad_asistentes" id="cantidad_asistentes" value="<%=Form.getParameter("cantidad_asistentes")%>" required/>
				<div class="invalid-feedback">
       				 Por favor ingrese cantidad válida.
     			</div>
			</div>
			<!-- Submit button -->
			<div class="mt-3">
				<button type="submit" class="btn btn-primary">Enviar</button>
			</div>
		</form>
			</div>
			
			<script>
			
					document.querySelector('#rut_empresa').addEventListener('blur', validateRun);
					document.querySelector('#hora').addEventListener('blur', validateTime);
					document.querySelector('#lugar').addEventListener('blur', validatePlace);
					document.querySelector('#duracion').addEventListener('blur', validateTimeFrame);
					document.querySelector('#cantidad_asistentes').addEventListener('blur', validateAttendanceAmount);
					
					const reSpaces = /^\S*$/;
					
					function validatePlace(e) {
					  const place = document.querySelector('#lugar');
					  const re = /^[a-zA-Z ]{4,30}$/;
					  if (re.test(place.value)) {
					    place.classList.remove('is-invalid');
					    place.classList.add('is-valid');
					    return true;
					  } else {
					    place.classList.remove('is-valid');
					
					    place.classList.add('is-invalid');
					    return false;
					  }
					} 
					
					function validateTime(e) {
						  const time = document.querySelector('#hora');
						  const re = /([01]?[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])$/;
					
						  if (re.test(time.value)) {
						    time.classList.remove('is-invalid');
						    time.classList.add('is-valid');
					
						    return true;
						  } else {
						    time.classList.add('is-invalid');
						    time.classList.remove('is-valid');
					
						    return false;
						  }
						}
					 
						
					function validateRun(e) {
						  const rut = document.querySelector('#rut_empresa');
						  const re = /^[0-9]+-[0-9kK]{1}$/;
					
						  if (re.test(rut.value)) {
						    rut.classList.remove('is-invalid');
						    rut.classList.add('is-valid');
					
						    return true;
						  } else {
						    rut.classList.add('is-invalid');
						    rut.classList.remove('is-valid');
					
						    return false;
						  }
						}	
					
					function validateTimeFrame(e) {
						  const TimeFrame = document.querySelector('#duracion');
						  const re = /^([01]?[0-9]|2[0-3]):[0-5][0-9] horas$/;
					
						  if (re.test(TimeFrame.value)) {
							  TimeFrame.classList.remove('is-invalid');
							  TimeFrame.classList.add('is-valid');
					
						    return true;
						  } else {
							  TimeFrame.classList.add('is-invalid');
							  TimeFrame.classList.remove('is-valid');
					
						    return false;
						  }
						}
					
					function validateAttendanceAmount(e) {
						  const attendance = document.querySelector('#cantidad_asistentes');
						  const re = /^[1-9]{1}[0-9]{0,3}$/;
					
						  if (re.test(attendance.value)) {
							  attendance.classList.remove('is-invalid');
							  attendance.classList.add('is-valid');
					
						    return true;
						  } else {
							  attendance.classList.add('is-invalid');
							  attendance.classList.remove('is-valid');
					
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
					          !validateRun() ||
					          !validateTime() ||
					          !validatePlace() ||
					          !validateTimeFrame() ||
					          !validateAttendanceAmount()
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