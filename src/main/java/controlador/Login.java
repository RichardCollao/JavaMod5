package controlador;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import clases.Utilities;
import modelo.dao.mysql.MySQLUsuarioDAO;
import modelo.entities.Usuario;

public class Login extends MainServlet implements Callback {

	public Login() {
		super.setCallback(this);
	}
	
	@Override
	public void continueGet() {
		if (request.getParameter("login") != null && request.getParameter("login").equals("out")) {
			logOut();
		} else {
			showView("login.jsp");
		}
	}
	
	@Override
	public void continuePost() {
		String correo = form.getStringOrBlank("correo");
		String clave = form.getStringOrBlank("clave");

		this.errors = validateForm(correo, clave);
		if (this.errors.isEmpty()) {
			Usuario usuario = verifyCredentials(correo, clave);
			if (usuario != null) {
				saveAuthInSession(usuario);
				session.setAttribute("toast", "Bienvenido: " + usuario.getNombreUsuario());
				redirect(request.getContextPath() + "/inicio");
			} else {
				this.errors.add("El nombre de usuario o contraseña son incorrectos");
			}
		}
		showView("login.jsp");
	}

	private Usuario verifyCredentials(String correo, String clave) {
		MySQLUsuarioDAO db = new MySQLUsuarioDAO();
		Usuario usuario = null;

		try {
			usuario = db.verifyCredentials(correo, clave);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	private void saveAuthInSession(Usuario usuario) {
		session.setAttribute("authenticated", true);
		session.setAttribute("idUsuario", usuario.getIdUsuario());
	}

	private void logOut() {
		HttpSession session = request.getSession();
		session.invalidate();
		redirect(request.getContextPath() + "/login");
	}

	private ArrayList<String> validateForm(String correo, String clave) {
		errors = new ArrayList<String>();
		// validar correo
		if (correo.length() == 0) {
			errors.add("El campo 'correo' esta vacio.");
		} else {
			if (!Utilities.compareExpression("^[a-zA-Z0-9_\\Q.\\E]+@[a-zA-Z0-9_\\Q.\\E]+$", correo)) {
				errors.add("El valor del campo 'correo' no es valido.");
			}
		}
		// validar contraseña
		if (clave.length() < 6) {
			errors.add("El campo 'Contraseña' debe contener minimo 6 caracteres.");
		}

		return errors;
	}
}
