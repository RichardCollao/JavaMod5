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
		String nombre = form.getStringOrBlank("nombre");
		String correo = form.getStringOrBlank("correo");
		String mensaje = form.getStringOrBlank("mensaje");

		System.out.println("nombre: " +  nombre);
		System.out.println("correo: " + correo);
		System.out.println("mensaje: " + mensaje);
		// redirect(request.getContextPath() + "/contacto?action=send");
		
		
		// TODO: validacion
		showView("contacto.jsp");
	}
}
