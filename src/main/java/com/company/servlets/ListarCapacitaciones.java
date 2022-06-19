package com.company.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.mysql.MySQLCapacitacionDAO;
import entities.Capacitacion;


public class ListarCapacitaciones extends MainServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		
		MySQLCapacitacionDAO db = new MySQLCapacitacionDAO();
		ArrayList<Capacitacion> capacitacionesList = new ArrayList<Capacitacion>();
		try {
			capacitacionesList = db.readAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("capacitacionesList", capacitacionesList);
		index("listarcapacitaciones.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
