package com.company.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Usuario;

public class MainServlet extends HttpServlet {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Usuario usuario = null;
	
	protected void processRequest(String view) {
//		if(verifyAuth(){
//			usuario
//		}
//		verifyAuth(checkPermissions);
		showView(view);
    }

	protected void verifyAuth() {
		HttpSession session = request.getSession();
		// TODO: preguntar si existe una sesion activa
		// if ((boolean) session.getAttribute("authenticated")) {
		//	showView("/login");
		// }
	}

	protected void checkPermissions() {
		// TODO: verificar si el usuario autenticado tiene permiso para acceder a la pagina actual
		// Si tiene permisos se mantiene en la pagina de lo contrario se redirecciona a inicio o login. 

	}

	protected void showView(String view) {
		try {
			request.getRequestDispatcher(view + ".jsp").include(request, response);
			// request.getRequestDispatcher("/" + view + ".jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
