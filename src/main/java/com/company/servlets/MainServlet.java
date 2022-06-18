package com.company.servlets;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Usuario;

public class MainServlet extends HttpServlet {
	public HttpServletRequest request;
	public HttpServletResponse response;

	public MainServlet() {

	}

	protected void index(String fileJsp) {
		this.request.setAttribute("fileJsp", fileJsp);
		if (this.request.getAttribute("errors") == null) {
			this.request.setAttribute("errors", new ArrayList<String>());
		}
		showView(fileJsp);
	}

	protected void verifyAuth() {
		// HttpSession session = request.getSession();
		// TODO: preguntar si existe una sesion activa
		// if ((boolean) session.getAttribute("authenticated")) {
		// showView("/login");
		// }
	}

	protected void checkPermissions() {
		// TODO: verificar si el usuario autenticado tiene permiso para acceder a la pagina actual
		// Si tiene permisos se mantiene en la pagina de lo contrario se redirecciona a inicio o login.

	}

	protected void showView(String fileJsp) {

		try {
			this.response.setContentType("text/html;charset=UTF-8");
			this.request.getRequestDispatcher("/layout.jsp").include(this.request, this.response);
			// this.request.getRequestDispatcher("/layout.jsp").forward(this.request, this.response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
