package controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import modelo.dao.mysql.MySQLCapacitacionDAO;
import modelo.entities.Capacitacion;

public class CrearCapacitacion extends MainServlet implements Callback {

	public CrearCapacitacion() {
		super.setCallback(this);;
	}
	
	@Override
	public void continueGet() {
		showView("crearcapacitacion.jsp");
	}
	
	@Override
	public void continuePost() {
		Capacitacion capacitacion = loadForm();

		this.errors = validateForm(capacitacion);

		if (this.errors.isEmpty()) {
			if (create(capacitacion)) {
				redirect(request.getContextPath() + "/listarcapacitaciones");
			} else {
				this.errors.add("Error en la base de datos");
				showView("crearcapacitacion.jsp");
			}
		} else {
			showView("crearcapacitacion.jsp");
		}
	}

	private Capacitacion loadForm() {
		String[] diasArray = new String[]{"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
		String rut_empresa = form.getStringOrBlank("rut_empresa");
		Integer iDia = form.getIntegerOrZero("dia");
		String dia = diasArray[iDia];
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
		return capacitacion;
	}

	private boolean create(Capacitacion capacitacion) {
		try {
			MySQLCapacitacionDAO db = new MySQLCapacitacionDAO();
			db.create(capacitacion);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private ArrayList<String> validateForm(Capacitacion capacitacion) {
		errors = new ArrayList<String>();
		// validar rut
		if (!Utilities.compareExpression("^[1-9]{1}[0-9]+-[1-9kK]{1}$", capacitacion.getRutEmpresa())) {
			errors.add("El valor del campo 'rut empresa' no es valido.");
		}
		// validar dia
		System.out.println("__________________" + capacitacion.getDia());
		List<String> list = new ArrayList<>(Arrays.asList(new String[]{"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"}));
		if (!list.contains(capacitacion.getDia())) {
			errors.add("El campo 'dia' no es valido.");
		}
		// validar hora
		if (!Utilities.compareExpression("^([0-1]{1}[0-9]{1}|2[0-3]):[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}$", capacitacion.getHora())) {
			errors.add("El valor del campo 'hora' no es valido.");
		}
		// validar lugar
		if (!Utilities.compareExpression("[a-zA-Z0-9 ]{0,50}$", capacitacion.getLugar())) {
			errors.add("El valor del campo 'lugar' no es valido. (solo se permiten caracteres alfanumericos)");
		}
		// validar duracion
		if (!Utilities.compareExpression("[a-zA-Z0-9 ]{0,70}$", capacitacion.getDuracion())) {
			errors.add("El valor del campo 'lugar' no es valido. (solo se permiten caracteres alfanumericos)");
		}
		// validar cantidad asistentes
		if (capacitacion.getCantidadAsistentes() < 0 || capacitacion.getCantidadAsistentes() > 1000) {
			errors.add("El valor del campo 'cantidad de asistentes' no es valido. (valor aceptado entre 0-1000)");
		}
		return errors;
	}

}
