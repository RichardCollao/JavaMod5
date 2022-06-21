package controlador;

public class Contacto extends MainServlet implements Callback {

	public Contacto() {
		super.setCallback(this);
	}
	
	@Override
	public void continueGet() {
		showView("contacto.jsp");
	}
	
	@Override
	public void continuePost() {
		String correo = form.getStringOrBlank("nombre");
		String clave = form.getStringOrBlank("correo");
		String mensaje = form.getStringOrBlank("mensaje");

		// TODO: validacion
		showView("contacto.jsp");
	}
}
