package controlador;

import javax.servlet.http.HttpSession;

import modelo.dao.mysql.MySQLUsuarioDAO;
import modelo.entities.Usuario;

public class Authentication {
	private HttpSession session;
	public Authentication(HttpSession session) {
		this.session = session;
	}

	public Usuario getUserAuth() {
		Boolean authenticated;
		Usuario usuario = null;
		Integer idUsuario = (Integer) session.getAttribute("idUsuario");
		// Ya que la sesion se destruye retornara null
		authenticated = (Boolean) session.getAttribute("authenticated") != null ? true : false;
		if (authenticated == true && idUsuario != null && idUsuario > 0) {
			usuario = loadUser(idUsuario);
		}
		return usuario;
	}

	public Usuario loadUser(int idUsuario) {
		MySQLUsuarioDAO db = new MySQLUsuarioDAO();
		Usuario usuario = null;

		try {
			usuario = db.readOne(idUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

}
