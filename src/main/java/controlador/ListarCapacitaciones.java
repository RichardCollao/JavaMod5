package controlador;

import java.util.ArrayList;

import modelo.dao.implementacion.MySQLCapacitacionDAO;
import modelo.entities.Capacitacion;

public class ListarCapacitaciones extends MainServlet implements Callback {

	public ListarCapacitaciones() {
		super.setCallback(this);
	}
	
	@Override
	public void continueGet() {
		MySQLCapacitacionDAO db = new MySQLCapacitacionDAO();
		ArrayList<Capacitacion> capacitacionesList = new ArrayList<Capacitacion>();
		try {
			capacitacionesList = db.readAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("capacitacionesList", capacitacionesList);
		showView("listarcapacitaciones.jsp");
	}

	@Override
	public void continuePost() {
		// TODO Auto-generated method stub
		
	}

}
