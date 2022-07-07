package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Form;
import modelo.entities.Usuario;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected ArrayList<String> errors = new ArrayList<String>();
	protected Usuario usuarioAut = null;
	protected Form form;
	protected Callback callback;

	protected void setCallback(Callback callback) {
		this.callback = callback;
	} 

	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = this.request.getSession();
		this.form = new Form(this.request);
		this.errors = new ArrayList<String>();

		toast();
		// Si la sesion se encuentra activa, carga el usuario desde la DB.
		verifyAuth();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init(request, response);
		if (overcomeRestriction()) {
			callback.continueGet();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init(request, response);
		if (overcomeRestriction()) {
			callback.continuePost();
		}
	}

	protected void verifyAuth() {
		usuarioAut = new Authentication(this.session).getUserAuth();
		if (usuarioAut != null) {
			request.setAttribute("nameUserAuth", usuarioAut.getNombreUsuario());
		}

	}

	protected void toast() {
		request.setAttribute("toast", null);
		String toast = (String) session.getAttribute("toast");
		if (toast != null) {
			session.setAttribute("toast", null);
			request.setAttribute("toast", toast);
		}
	}

	protected boolean overcomeRestriction() {
		String currentNameClass = this.getClass().getSimpleName();
		List<String> authServletList = Arrays.asList(
				"Contacto",
				"CrearCapacitacion",
				"ListarCapacitaciones",
				"CrearUsuario",
				"EditarUsuario",
				"ListarUsuarios");
		if (usuarioAut == null) {
			if (authServletList.contains(currentNameClass)) {
				session.setAttribute("toast", "Acceso permitido solo a usuarios autenticados.");
				redirect(request.getContextPath() + "/login");
				return false;
			}
		}
		return true;
	}

	protected void showView(String fileJsp) {
		request.setAttribute("fileJsp", fileJsp);
		request.setAttribute("errors", this.errors);

		try {
			response.setContentType("text/html;charset=UTF-8");
			// this.request.getRequestDispatcher("/layout.jsp").include(this.request, this.response);
			request.getRequestDispatcher("/layout.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// redirecciona de acuerdo a la URI recibida
	protected void redirect(String uri) {
		try {
			// System.out.println("Redirect from: " + request.getRequestURI() + " To: " + uri);
			response.sendRedirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
