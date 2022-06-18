package com.company.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Usuario;
import model.dao.mysql.MySQLUsuarioDAO;

public class Login extends MainServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;

		this.request.setAttribute("errors", new ArrayList<String>());
		
		index("login");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;

		// Obtiene las credenciales de acceso desde el formulario login
		String correo = (String) request.getAttribute("correo");
		String clave = (String) request.getAttribute("clave");

		MySQLUsuarioDAO db = new MySQLUsuarioDAO();
		Usuario usuario = null;
		// Verifica si existe un usuario en la base de datos que coincida con la tupla de credenciales
		try {
			usuario = db.verifyCredentials(correo, clave);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Si se cargo un usuario se guardan las referencias del usuario en la sesion y se redirecciona a la pagina de inicio
		if (usuario != null) {
			HttpSession session = request.getSession();
			session.setAttribute("authenticated", true);
			session.setAttribute("id_usuario", usuario.getIdUsuario());
			this.request.setAttribute("type", usuario.getType());
			
			this.request.setAttribute("errors", new ArrayList<String>());
			index("inicio");
		} else {
			this.request.setAttribute("errors", "El nombre de usuario o contraseña son incorrectos");
			index("login");
			
		}


	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
