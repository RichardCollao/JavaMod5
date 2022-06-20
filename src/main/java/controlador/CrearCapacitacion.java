package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.mysql.MySQLCapacitacionDAO;
import modelo.entities.Capacitacion;
import controlador.Form;

public class CrearCapacitacion extends MainServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		this.form = new Form(this.request);
		
		index("crearcapacitacion.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		this.form = new Form(this.request);
			
		String[] diasArray = new String[]{"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
		String rut_empresa = this.form.getStringOrBlank("rut_empresa");
		Integer iDia = this.form.getIntegerOrZero("dia");
		String dia = diasArray[iDia + 1];
		String hora = this.form.getStringOrBlank("hora");
		String lugar = this.form.getStringOrBlank("lugar");
		String duracion = this.form.getStringOrBlank("duracion");
		Integer cantidad_asistentes = this.form.getIntegerOrZero("cantidad_asistentes");

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
