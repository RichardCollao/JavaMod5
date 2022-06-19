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
	protected ArrayList<String> errors = new ArrayList<String>();

	Usuario usuarioAut = null;

	public MainServlet() {

	}

	protected void index(String fileJsp) {
		verifyAuth();

		this.request.setAttribute("fileJsp", fileJsp);

		showView(fileJsp);
	}

	protected void verifyAuth() {
		usuarioAut = new Authentication(request.getSession()).getUserAuth();
		if(usuarioAut != null) {
			this.request.setAttribute("usuarioAtenticado", usuarioAut.getNombreUsuario());
		}
	}

	protected void checkPermissions() {
		// TODO: verificar si el usuario autenticado tiene permiso para acceder a la pagina actual
		// Si tiene permisos se mantiene en la pagina de lo contrario se redirecciona a inicio o login.

	}

	protected void showView(String fileJsp) {
		this.request.setAttribute("errors", this.errors);
		try {
			this.response.setContentType("text/html;charset=UTF-8");
			this.request.getRequestDispatcher("/layout.jsp").include(this.request, this.response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
