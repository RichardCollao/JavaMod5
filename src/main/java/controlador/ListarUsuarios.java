package controlador;

import java.util.ArrayList;

import modelo.dao.implementacion.MySQLUsuarioDAO;
import modelo.entities.Usuario;

public class ListarUsuarios extends MainServlet implements Callback {

	public ListarUsuarios() {
		super.setCallback(this);
	}
	
	@Override
	public void continueGet() {
		MySQLUsuarioDAO db = new MySQLUsuarioDAO();
		ArrayList<Usuario> usuariosList = new ArrayList<Usuario>();
		try {
			usuariosList = db.readAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("usuariosList", usuariosList);
		showView("listarusuarios.jsp");
	}

	@Override
	public void continuePost() {
		// TODO Auto-generated method stub
		
	}
}
