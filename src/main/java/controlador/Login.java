package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.mysql.MySQLUsuarioDAO;
import modelo.entities.Usuario;

public class Login extends MainServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		this.errors = new ArrayList<String>();
		String login = request.getParameter("login");

		if (login != null && login.equals("out")) {
			logOut();
		} else {
			index("login.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;

		String correo = (String) request.getParameter("correo");
		String clave = (String) request.getParameter("clave");

		this.errors = validateForm(correo, clave);
		if (this.errors.isEmpty()) {
			Usuario usuario = verifyCredentials(correo, clave);
			if (usuario != null) {
				saveAuthInSession(usuario);
				response.sendRedirect(request.getContextPath() + "/inicio");
			} else {
				this.errors.add("El nombre de usuario o contraseña son incorrectos");
			}
		}
		index("login.jsp");
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
		HttpSession session = request.getSession();
		session.setAttribute("authenticated", true);
		session.setAttribute("usuarioAtenticado", usuario.getNombreUsuario());
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
		if (correo == null || correo.length() == 0) {
			errors.add("El campo 'correo' esta vacio.");
		} else {
			Matcher matcher;
			matcher = Pattern.compile("^[a-zA-Z0-9_\\Q.\\E]+@[a-zA-Z0-9_\\Q.\\E]+$").matcher(correo);
			if (!matcher.find()) {
				errors.add("El valor del campo 'correo' no es valido.");
			}
		}
		// validar contraseña
		if (clave == null || clave.length() < 6) {
			errors.add("El campo 'Contraseña' debe contener minimo 6 caracteres.");
		}

		return errors;
	}
}
