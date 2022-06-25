package controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import clases.Utilities;
import modelo.dao.implementacion.MySQLUsuarioDAO;
import modelo.entities.Usuario;

public class CrearUsuario extends MainServlet implements Callback {

	private String clave2;
	public CrearUsuario() {
		super.setCallback(this);;
	}

	@Override
	public void continueGet() {
		showView("crearusuario.jsp");
	}

	@Override
	public void continuePost() {
		Usuario usuario = loadForm();

		this.errors = validateForm(usuario);
		System.out.println("usuario: " + usuario);
		System.out.println("this.errors: " + this.errors);
		if (this.errors.isEmpty()) {
			if (create(usuario)) {
				redirect(request.getContextPath() + "/listarusuarios");
			} else {
				this.errors.add("Error en la base de datos");
				showView("crearusuario.jsp");
			}
		} else {
			showView("crearusuario.jsp");
		}
	}

	private Usuario loadForm() {
		String[] tipoArray = new String[]{"Administrativo", "Cliente", "Profesional"};
		Integer iTipo = form.getIntegerOrZero("tipo");
		String correo = form.getStringOrBlank("correo");
		String clave = form.getStringOrBlank("clave");
		clave2 = form.getStringOrBlank("clave2");
		String nombreUsuario = form.getStringOrBlank("nombre_usuario");
		String fechaNacimiento = form.getStringOrBlank("fecha_nacimiento");
		String run = form.getStringOrBlank("run");
		String tipo = tipoArray[iTipo];

		Usuario usuario = new Usuario();
		usuario.setCorreo(correo);
		usuario.setClave(clave);
		usuario.setNombreUsuario(nombreUsuario);
		usuario.setFechaNacimiento(fechaNacimiento);
		usuario.setRun(run);
		usuario.setTipo(tipo);
		return usuario;
	}

	private boolean create(Usuario usuario) {
		try {
			MySQLUsuarioDAO db = new MySQLUsuarioDAO();
			db.create(usuario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private ArrayList<String> validateForm(Usuario usuario) {
		errors = new ArrayList<String>();
		// validar correo
		if (usuario.getCorreo().length() == 0) {
			errors.add("El campo 'correo' esta vacio.");
		} else {
			if (!Utilities.compareExpression("^[a-zA-Z0-9_\\Q.\\E]+@[a-zA-Z0-9_\\Q.\\E]+$", usuario.getCorreo())) {
				errors.add("El valor del campo 'correo' no es valido.");
			}
		}
		// validar contraseña
		if (usuario.getClave().length() < 6) {
			errors.add("El campo 'Contraseña' debe contener minimo 6 caracteres.");
		} else if (!usuario.getClave().equals(clave2)) {
			errors.add("Las contraseñas no son iguales.");
		}
		// validar nombre de usuario
		if (usuario.getNombreUsuario().length() == 0) {
			errors.add("El campo 'nombre usuario' esta vacio.");
		} else {
			if (!Utilities.compareExpression("^[a-zA-Z0-9 ]{3,50}$", usuario.getNombreUsuario())) {
				errors.add("El valor del campo 'nombre usuario' no es valido. (solo se permiten caracteres alfanumericos)");
			}
		}
		// validar fecha de nacimiento
		if (!Utilities.compareExpression("^[1-2]{1}[0-9]{3}\\-(0[1-9]{1}|1[0-2]{1})\\-([0-2]{1}[1-9]{1}|3[0-1]{1})$", usuario.getFechaNacimiento())) {
			errors.add("El valor del campo 'fecha de nacimiento' no es valido.");
		}
		// validar run
		if (!Utilities.compareExpression("^[1-9]{1}[0-9]+\\-[1-9kK]{1}$", usuario.getRun())) {
			errors.add("El valor del campo 'run' no es valido.");
		}
		// validar tipo
		List<String> list = new ArrayList<>(Arrays.asList(new String[]{"Cliente", "Administrativo", "Profesional"}));
		if (!list.contains(usuario.getTipo())) {
			errors.add("El valor del campo 'tipo' no es valido.");
		}
		return errors;
	}

}
