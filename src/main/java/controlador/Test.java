package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.mysql.MySQLCapacitacionDAO;
import modelo.entities.Capacitacion;

public class Test extends MainServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;

		ArrayList<String> errors = new ArrayList<String>();
		errors.add("Error 1");
		errors.add("Error 2");
		errors.add("Error 3");


		request.setAttribute("fileJsp", "login.jsp");
		request.setAttribute("errors", errors);
		index("/layout");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		index("/layout");
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
