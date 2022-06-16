package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import clases.Usuario;

public class MainServlet extends HttpServlet {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    protected void processRequest() {

    }

    protected void verifyAuth(String strController) {
	HttpSession session = request.getSession();
	if ((boolean) session.getAttribute("authenticated")) {
	    showView("/login");
	}
    }

    protected void checkPermissions(Usuario usuario) {
	
    }
    
    protected void showView(String view) {
	try {
	    request.getRequestDispatcher(view + ".jsp").include(request, response);
	    // request.getRequestDispatcher("/" + view + ".jsp").forward(request, response);
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
