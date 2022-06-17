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
			System.out.println("----------------------------------------");
			capacitacionesList = db.readAll();
			System.out.println("----------------------------------------");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Capacitacion capacitacion : capacitacionesList) {
			System.out.println(capacitacion.toString());
		}
		
		index("/listarcapacitaciones");
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
