package com.company.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.mysql.MySQLUsuarioDAO;
import entities.Usuario;

public class ListarUsuarios extends MainServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;

		MySQLUsuarioDAO db = new MySQLUsuarioDAO();
		ArrayList<Usuario> usuariosList = new ArrayList<Usuario>();
		try {
			usuariosList = db.readAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("usuariosList", usuariosList);
		index("listarusuarios.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
