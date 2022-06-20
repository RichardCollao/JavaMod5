package controlador;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class Form {
	private ArrayList<String[]> parameters = new ArrayList<String[]>();
	private HttpServletRequest request;

	public Form(HttpServletRequest request) {
		this.request = request;
		this.parameters = new ArrayList<String[]>();
	};

	public String getStringOrBlank(String name) {
		String result = Utilities.stringNotNull(this.request.getParameter(name));
		this.parameters.add(new String[]{name, result});
		return result;
	}

	public Integer getIntegerOrZero(String name) {
		Integer result = Utilities.parseIntNotNull(this.request.getParameter(name));
		this.parameters.add(new String[]{name, result.toString()});
		return result;
	}

	// metodo utilizado para poblar formulario
	public String getParameter(String name) {
		String result = "";
		try {
			for (String[] param : this.parameters) {
				if (param[0].equals(name)) {
					result = param[1];
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
