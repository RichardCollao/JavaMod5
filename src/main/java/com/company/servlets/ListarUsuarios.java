package com.company.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Usuario;
import model.dao.mysql.MySQLUsuarioDAO;

public class ListarUsuarios extends MainServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		
		MySQLUsuarioDAO db = new MySQLUsuarioDAO();
		ArrayList<Usuario> usuariosList = new ArrayList();
		try {
			usuariosList = db.readAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("it's showtime");

		for (Usuario usuario : usuariosList) {
			System.out.println(usuario.toString());
		}

		request.setAttribute("usuariosList", usuariosList);
		//index("/listarusuarios");
		request.getRequestDispatcher("listarusuarios.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		index("/listarusuarios");
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
