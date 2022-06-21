package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.entities.Usuario;

public class MainServlet extends HttpServlet {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
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
		this.form = new Form(this.request);
		this.errors = new ArrayList<String>();

		verifyAuth();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");
		init(request, response);

		if (overcomeRestriction()) {
			callback.continueGet();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()");
		init(request, response);
		if (overcomeRestriction()) {
			callback.continuePost();
		}
	}

	protected void verifyAuth() {
		usuarioAut = new Authentication(request.getSession()).getUserAuth();
		if (usuarioAut != null) {
			this.request.setAttribute("nameUserAuth", usuarioAut.getNombreUsuario());
		}
	}

	protected boolean overcomeRestriction() {
		String currentNameClass = this.getClass().getSimpleName();
		usuarioAut = new Authentication(request.getSession()).getUserAuth();
		// Requerimiento ABPRO 2.4
		String[] authServletList = new String[]{"Contacto", "CrearCapacitacion", "ListarCapacitaciones"};
		if (usuarioAut == null) {
			for (String servlet : authServletList) {
				if (currentNameClass.equals(servlet)) {
					redirect(request.getContextPath() + "/login");
					return false;
				}
			}
		}
		return true;
	}

	protected void showView(String fileJsp) {
		this.request.setAttribute("fileJsp", fileJsp);
		this.request.setAttribute("errors", this.errors);

		try {
			this.response.setContentType("text/html;charset=UTF-8");
			// this.request.getRequestDispatcher("/layout.jsp").include(this.request, this.response);
			this.request.getRequestDispatcher("/layout.jsp").forward(this.request, this.response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// redirecciona de acuerdo a la URI recibida
	protected void redirect(String uri) {
		errors = new ArrayList<String>();
		try {
			System.out.println("Redirect from: " + request.getRequestURI() + " To: " + uri);
			response.sendRedirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
