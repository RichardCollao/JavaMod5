package com.company.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Usuario;
import model.dao.mysql.MySQLUsuarioDAO;


public class ListarCapacitaciones extends MainServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		
		
		MySQLUsuarioDAO db = new MySQLUsuarioDAO();
		List<Usuario> usurariosList = new ArrayList();
		try {
			usurariosList = db.readAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Usuario usuario : usurariosList) {
			System.out.println(usuario.toString());
		}
		
		
		index("/capacitaciones");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		index("/capacitaciones");
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
