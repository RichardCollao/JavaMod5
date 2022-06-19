package com.company.servlets;

import javax.servlet.http.HttpSession;

import entities.Usuario;
import model.dao.mysql.MySQLUsuarioDAO;

public class Authentication {
	private HttpSession session;
	public Authentication(HttpSession session) {
		this.session = session;
	}

	public Usuario getUserAuth() {
		boolean authenticated = false;
		Usuario usuario = null;

		if ((Boolean) session.getAttribute("authenticated") != null) {
			authenticated = true;
		}

		Integer idUsuario = (Integer) session.getAttribute("id_usuario");
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
