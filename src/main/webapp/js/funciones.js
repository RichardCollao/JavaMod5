// Contacto
function validarFormulario(event) {
  // Cancela el evento si este es cancelable, sin detener el resto del funcionamiento del evento, es decir, puede ser llamado de nuevo.
  event.preventDefault();

  var contact_name = document.getElementById('contact_name');
  if (contact_name.value.length < 3) {
    alert('El campo "Nombre" debe contener al menos 3 letras.');
    contact_name.focus();
    return;
  }
  var contact_email = document.getElementById('contact_email');
  if (contact_email.value.length < 6) {
    alert('El campo "Correo" debe contener al menos 6 letras.');
    contact_email.focus();
    return;
  }
  var contact_comment = document.getElementById('contact_comment');
  if (contact_comment.value.length < 6) {
    alert('El campo "Mensaje" debe contener al menos 6 letras.');
    contact_comment.focus();
    return;
  }

  // this.submit();
  alert('Su mensaje ha sido enviado con exito.');
  this.reset();
}

// Estadissticas
function populateTable() {
  // var table = document.querySelector("#datos");
  var tbody = document.querySelector('#datos tbody');
  var str = '';

  for (var i = 0; i < 100; i++) {
    str += '<tr>';
    str += '<td>' + faker.name.firstName() + '</td>';
    str += '<td>' + faker.name.firstName() + '</td>';
    str += '<td>' + faker.internet.email() + '</td>';
    str += '<td>' + faker.address.city() + '</td>';
    str += '<td>' + faker.phone.phoneNumber() + '</td>';
    str += '</tr>';
  }
  tbody.innerHTML = tbody.innerHTML + str;
}