package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Contacto extends MainServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init(request, response);
		
		index("contacto.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init(request, response);

		String correo = form.getStringOrBlank("nombre");
		String clave = form.getStringOrBlank("correo");
		String mensaje = form.getStringOrBlank("mensaje");
		
		// TODO: validacion
		index("contacto.jsp");
	}
}
