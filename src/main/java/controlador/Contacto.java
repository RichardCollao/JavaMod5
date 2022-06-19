package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Contacto extends MainServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;

		index("contacto.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;

		boolean validated = true;
		if (!validated) {
			this.request.setAttribute("errors", "El nombre de usuario o contraseña son incorrectos");
		}
		index("contacto.jsp");
	}
}
