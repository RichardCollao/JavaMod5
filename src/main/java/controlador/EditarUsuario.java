package controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import clases.Utilities;
import modelo.dao.implementacion.MySQLAdministrativoDAO;
import modelo.dao.implementacion.MySQLClienteDAO;
import modelo.dao.implementacion.MySQLProfesionalDAO;
import modelo.dao.implementacion.MySQLUsuarioDAO;
import modelo.entities.Administrativo;
import modelo.entities.Cliente;
import modelo.entities.Profesional;
import modelo.entities.Usuario;

public class EditarUsuario extends MainServlet implements Callback {
	private String clave2;

	public EditarUsuario() {
		super.setCallback(this);
	}

	@Override
	public void continueGet() {
		
		Usuario usuario = this.cargarUsuario();
		if (usuario == null) {
			errors.add("No se encontro ningun usuario con este identificador.");
			redirect(request.getContextPath() + "/listarusuarios");
			return;
		}

		Usuario tipoUsuario = this.cargarTipoUsuario(usuario);
		
		List<String> list = new ArrayList<>(Arrays.asList(new String[]{"administrativo", "cliente", "profesional"}));
		Integer iTipo = list.indexOf(usuario.getTipo());
		form.addTuple("tipo", iTipo.toString());
		form.addTuple("correo", usuario.getCorreo());
		form.addTuple("clave", usuario.getClave());
		form.addTuple("nombre_usuario", usuario.getNombreUsuario());
		form.addTuple("fecha_nacimiento", usuario.getFechaNacimiento());
		form.addTuple("run", usuario.getRun());
		
		if (usuario.getTipo().equals("administrativo")) {
			Administrativo administrativo = (Administrativo) tipoUsuario;
			form.addTuple("area", administrativo.getArea());
			form.addTuple("experiencia_previa", administrativo.getExperienciaPrevia());
		}
		if (usuario.getTipo().equals("cliente")) {
			Cliente cliente = (Cliente) tipoUsuario;
			form.addTuple("nombres", cliente.getNombres());
			form.addTuple("apellidos", cliente.getApellidos());
			form.addTuple("telefono", cliente.getTelefono());
			form.addTuple("afp", cliente.getAfp());
			form.addTuple("sistema_salud", Integer.toString(cliente.getSistemaSalud()));
			form.addTuple("direccion", cliente.getDireccion());
			form.addTuple("comuna", cliente.getComuna());
		}
		if (usuario.getTipo().equals("profesional")) {
			Profesional profesional = (Profesional) tipoUsuario;
			form.addTuple("titulo", profesional.getTitulo());
			form.addTuple("fecha_ingreso", profesional.getFechaIngreso());
		}

		request.setAttribute("id", this.request.getParameter("id"));
		showView("editarusuario.jsp");
	}

	@Override
	public void continuePost() {
		Usuario usuarioDB = this.cargarUsuario();
		if (usuarioDB == null) {
			errors.add("No se encontro ningun usuario con este identificador.");
			redirect(request.getContextPath() + "/listarusuarios");
			return;
		}
		
		// Carga los datos desde el formulario
		Usuario usuario = leerFormularioUsuario();
		this.errors = validarFormulario(usuario);
		
		Administrativo administrativo = null;
		if (usuario.getTipo().equals("administrativo")) {
			administrativo = leerFormularioAdministrativo(usuario);
			administrativo.setIdUsuario(usuarioDB.getIdUsuario());
			// this.errors.addAll(validarFormularioAdministrativo(administrativo));
		}
		Cliente cliente = null;
		if (usuario.getTipo().equals("cliente")) {
			cliente = leerFormularioCliente(usuario);
			cliente.setIdUsuario(usuarioDB.getIdUsuario());
			// this.errors.addAll(validarFormularioCliente(cliente));
		}
		Profesional profesional = null;
		if (usuario.getTipo().equals("profesional")) {
			profesional = leerFormularioProfesional(usuario);
			profesional.setIdUsuario(usuarioDB.getIdUsuario());
			// this.errors.addAll(validarFormularioProfesional(profesional));
		}

		if (!this.errors.isEmpty()) {
			request.setAttribute("id", this.request.getParameter("id"));
			showView("editarusuario.jsp");
			return;
		}

		if (update(usuario, administrativo, cliente, profesional)) {
			redirect(request.getContextPath() + "/listarusuarios");
		} else {
			request.setAttribute("id", this.request.getParameter("id"));
			this.errors.add("Error en la base de datos");
			showView("editarusuario.jsp");
		}
	}

	private boolean update(Usuario usuario, Administrativo administrativo, Cliente cliente, Profesional profesional) {
		try {
			if (usuario.getTipo().equals("administrativo")) {
				MySQLAdministrativoDAO db = new MySQLAdministrativoDAO();
				db.update(administrativo);
				return true;
			}
			if (usuario.getTipo().equals("cliente")) {
				MySQLClienteDAO db = new MySQLClienteDAO();
				db.update(cliente);
				return true;
			}
			if (usuario.getTipo().equals("profesional")) {
				MySQLProfesionalDAO db = new MySQLProfesionalDAO();
				db.update(profesional);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private Usuario cargarUsuario() {
		Usuario usuario = null;
		Integer id = Integer.parseInt(this.request.getParameter("id"));
		try {
			MySQLUsuarioDAO db = new MySQLUsuarioDAO();
			usuario = db.readOne(id);
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Usuario cargarTipoUsuario(Usuario usuario) {
		Usuario tipoUsuario = null; 
		try {
			if (usuario.getTipo().equals("administrativo")) {
				MySQLAdministrativoDAO db = new MySQLAdministrativoDAO();
				tipoUsuario = db.readOne(usuario.getIdUsuario());
			}
			if (usuario.getTipo().equals("cliente")) {
				MySQLClienteDAO db = new MySQLClienteDAO();
				tipoUsuario = db.readOne(usuario.getIdUsuario());
			}
			if (usuario.getTipo().equals("profesional")) {
				MySQLProfesionalDAO db = new MySQLProfesionalDAO();
				tipoUsuario = db.readOne(usuario.getIdUsuario());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoUsuario;
	}
	
	private Usuario leerFormularioUsuario() {
		String[] tipoArray = new String[]{"administrativo", "cliente", "profesional"};
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

	private Administrativo leerFormularioAdministrativo(Usuario usuario) {
		String area = form.getStringOrBlank("area");
		String experienciaPrevia = form.getStringOrBlank("experiencia_previa");
		Administrativo administrativo = new Administrativo();
		administrativo.setArea(area);
		administrativo.setExperienciaPrevia(experienciaPrevia);
		administrativo.setCorreo(usuario.getCorreo());
		administrativo.setClave(usuario.getClave());
		administrativo.setNombreUsuario(usuario.getNombreUsuario());
		administrativo.setFechaNacimiento(usuario.getFechaNacimiento());
		administrativo.setRun(usuario.getRun());
		administrativo.setTipo(usuario.getTipo());
		return administrativo;
	}

	private Cliente leerFormularioCliente(Usuario usuario) {
		String nombres = form.getStringOrBlank("nombres");
		String apellidos = form.getStringOrBlank("apellidos");
		String telefono = form.getStringOrBlank("telefono");
		String afp = form.getStringOrBlank("afp");
		Integer sistemaSalud = form.getIntegerOrZero("sistema_salud");
		String direccion = form.getStringOrBlank("direccion");
		String comuna = form.getStringOrBlank("comuna");
		System.out.println("SISTEMA SALUD: " + sistemaSalud);
		Cliente cliente = new Cliente();
		cliente.setNombres(nombres);
		cliente.setApellidos(apellidos);
		cliente.setTelefono(telefono);
		cliente.setAfp(afp);
		cliente.setSistemaSalud(sistemaSalud);
		cliente.setDireccion(direccion);
		cliente.setComuna(comuna);
		cliente.setCorreo(usuario.getCorreo());
		cliente.setClave(usuario.getClave());
		cliente.setNombreUsuario(usuario.getNombreUsuario());
		cliente.setFechaNacimiento(usuario.getFechaNacimiento());
		cliente.setRun(usuario.getRun());
		cliente.setTipo(usuario.getTipo());
		return cliente;
	}

	private Profesional leerFormularioProfesional(Usuario usuario) {
		String titulo = form.getStringOrBlank("titulo");
		String fecha_ingreso = form.getStringOrBlank("fecha_ingreso");
		Profesional profesional = new Profesional();
		profesional.setTitulo(titulo);
		profesional.setFechaIngreso(fecha_ingreso);
		profesional.setCorreo(usuario.getCorreo());
		profesional.setClave(usuario.getClave());
		profesional.setNombreUsuario(usuario.getNombreUsuario());
		profesional.setFechaNacimiento(usuario.getFechaNacimiento());
		profesional.setRun(usuario.getRun());
		profesional.setTipo(usuario.getTipo());
		return profesional;
	}


	
	
	private ArrayList<String> validarFormulario(Usuario usuario) {
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
			if (!Utilities.compareExpression("^[a-zA-Z0-9 ]{2,50}$", usuario.getNombreUsuario())) {
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
		List<String> list = new ArrayList<>(Arrays.asList(new String[]{"cliente", "administrativo", "profesional"}));
		if (!list.contains(usuario.getTipo())) {
			errors.add("El valor del campo 'tipo' no es valido.");
		}
		return errors;
	}

	private ArrayList<String> validarFormularioAdministrativo(Administrativo administrativo) {
		errors = new ArrayList<String>();
		// TODO: escribir validaciones
		return errors;
	}

	private ArrayList<String> validarFormularioCliente(Cliente cliente) {
		errors = new ArrayList<String>();
		// TODO: escribir validaciones
		return errors;
	}

	private ArrayList<String> validarFormularioProfesional(Profesional profesional) {
		errors = new ArrayList<String>();
		// TODO: escribir validaciones
		return errors;
	}
}
