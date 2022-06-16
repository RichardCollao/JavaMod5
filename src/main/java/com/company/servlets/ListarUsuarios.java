package com.company.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Usuario;
import model.dao.mysql.MySQLUsuarioDAO;

public class ListarUsuarios extends MainServlet{

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		
		MySQLUsuarioDAO db = new MySQLUsuarioDAO();
		List<Usuario> usurariosList =  db.readAll();

		for (Usuario usuario : usurariosList) {
			System.out.println(usuario.toString());
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		processRequest("/usuarios");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		processRequest("/usuarios");
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
