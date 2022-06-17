package com.company.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.mysql.MySQLCapacitacionDAO;


public class ListarCapacitaciones extends MainServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		
		
		MySQLCapacitacionDAO db = new MySQLCapacitacionDAO();
		ArrayList<entities.Capacitacion> capacitacionesList = new ArrayList<entities.Capacitacion>();
		try {
			capacitacionesList = db.readAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (entities.Capacitacion capacitacion : capacitacionesList) {
			System.out.println(capacitacion.toString());
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
