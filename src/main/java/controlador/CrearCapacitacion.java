package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.mysql.MySQLCapacitacionDAO;
import modelo.entities.Capacitacion;

public class CrearCapacitacion extends MainServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init(request, response);
				
		index("crearcapacitacion.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init(request, response);;
			
		String[] diasArray = new String[]{"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
		String rut_empresa = form.getStringOrBlank("rut_empresa");
		Integer iDia = form.getIntegerOrZero("dia");
		String dia = diasArray[iDia + 1];
		String hora = form.getStringOrBlank("hora");
		String lugar = form.getStringOrBlank("lugar");
		String duracion = form.getStringOrBlank("duracion");
		Integer cantidad_asistentes = form.getIntegerOrZero("cantidad_asistentes");

		Capacitacion capacitacion = new Capacitacion();
		capacitacion.setRutEmpresa(rut_empresa);
		capacitacion.setDia(dia);
		capacitacion.setHora(hora);
		capacitacion.setLugar(lugar);
		capacitacion.setDuracion(duracion);
		capacitacion.setCantidadAsistentes(cantidad_asistentes);

		index("crearcapacitacion.jsp");
	}

	private void create(Capacitacion capacitacion) {
		MySQLCapacitacionDAO db = new MySQLCapacitacionDAO();

		try {
			db.create(capacitacion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
