/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

public class Inicio extends MainServlet implements Callback {

	public Inicio() {
		super.setCallback(this);
	}
	
	@Override
	public void continueGet() {
		showView("inicio.jsp");
	}

	@Override
	public void continuePost() {
		// TODO Auto-generated method stub

	}
}
