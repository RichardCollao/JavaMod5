package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Usuario;

public class MainServlet extends HttpServlet {
	public HttpServletRequest request;
	public HttpServletResponse response;
	protected ArrayList<String> errors = new ArrayList<String>();
	Usuario usuarioAut = null;

	public MainServlet() {

	}

	protected void index(String fileJsp) {
		// System.out.println("_____________" + this.getClass().getSimpleName());
		verifyAuth();
		checkPermissions();
		showView(fileJsp);
	}

	protected void verifyAuth() {
		usuarioAut = new Authentication(request.getSession()).getUserAuth();
		if (usuarioAut != null) {
			this.request.setAttribute("usuarioAtenticado", usuarioAut.getNombreUsuario());
		}

	}

	protected void checkPermissions() {
		String currentNameClass = this.getClass().getSimpleName();
		usuarioAut = new Authentication(request.getSession()).getUserAuth();
		// Requerimiento ABPRO 2.4
		if (usuarioAut == null) {
			if (currentNameClass.equals("Contacto") || currentNameClass.equals("Capacitaciones") || currentNameClass.equals("ListarCapacitaciones")) {
				redirect(request.getContextPath() + "/login");
			}
		}
	}

	protected void showView(String fileJsp) {
		this.request.setAttribute("fileJsp", fileJsp);
		this.request.setAttribute("errors", this.errors);
		this.response.setContentType("text/html;charset=UTF-8");
		try {
			this.request.getRequestDispatcher("/layout.jsp").include(this.request, this.response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// redirecciona de acuerdo a la URI recibida
	protected void redirect(String uri) {
		try {
			System.out.println("Redireccion: " + uri);
			response.sendRedirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
