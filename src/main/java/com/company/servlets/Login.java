package com.company.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.MainServlet;

public class Login extends MainServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	this.request = request;
	this.response = response;
	// processRequest();
	System.out.println("GET");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	this.request = request;
	this.response = response;

	String usuario = (String) request.getAttribute("usuario");
	String clave = (String) request.getAttribute("clave");
	HttpSession session = request.getSession();

	if (usuario.equals("admin") && clave.equals("1234")) {
	    session.setAttribute("authenticated", true);
	}

	System.out.println(request.toString());
	System.out.println("POST");
	System.out.println("Usuario: " + usuario);
	System.out.println("Clave: " + clave);

	// processRequest();
    }

    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>

}
