package controlador;

import java.io.IOException;
import java.util.ArrayList;

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

	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.form = new Form(this.request);
		this.errors = new ArrayList<String>();
	}

	protected void index(String fileJsp) {
		verifyAuth();
		
		if(checkRestrictions()) {
			showView(fileJsp);
		}else {
			redirect(request.getContextPath() + "/login");
		}
	}

	protected void verifyAuth() {
		usuarioAut = new Authentication(request.getSession()).getUserAuth();
		if (usuarioAut != null) {
			this.request.setAttribute("nameUserAuth", usuarioAut.getNombreUsuario());
		}
	}

	protected boolean checkRestrictions() {
		boolean result = true;
		String currentNameClass = this.getClass().getSimpleName();
		usuarioAut = new Authentication(request.getSession()).getUserAuth();
		// Requerimiento ABPRO 2.4
		String[] authServletList = new String[]{"Contacto", "CrearCapacitacion", "ListarCapacitaciones"};
		if (usuarioAut == null) {
			for(String servlet: authServletList) {
				if (currentNameClass.equals(servlet)) {
					result = false;
					break;
				}
			}
		}
		return result; 
	}

	protected void showView(String fileJsp) {
		this.request.setAttribute("fileJsp", fileJsp);
		this.request.setAttribute("errors", this.errors);

		try {
			this.response.setContentType("text/html;charset=UTF-8");
//			this.request.getRequestDispatcher("/layout.jsp").include(this.request, this.response);
			this.request.getRequestDispatcher("/layout.jsp").forward(this.request, this.response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// redirecciona de acuerdo a la URI recibida
	protected void redirect(String uri) {
		errors = new ArrayList<String>();
		try {
			System.out.println("Redirect from: " + request.getRequestURI()+ " To: " + uri);
			response.sendRedirect(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
