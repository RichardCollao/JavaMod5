package clases;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class Form {
	private static ArrayList<String[]> parameters = new ArrayList<String[]>();
	private HttpServletRequest request;

	public Form(HttpServletRequest request) {
		this.request = request;
		Form.parameters = new ArrayList<String[]>();
	};

	public String getStringOrBlank(String name) {
		String result = Utilities.getStringOrBlank(this.request.getParameter(name));
		Form.parameters.add(new String[]{name, result});
		return result;
	}

	public Integer getIntegerOrZero(String name) {
		Integer result = Utilities.getIntegerOrZero(this.request.getParameter(name));
		Form.parameters.add(new String[]{name, Utilities.getStringOrBlank(this.request.getParameter(name))});
		return result;
	}

	// metodo utilizado para poblar formulario
	public static String getParameter(String name) {
		String result = "";
		try {
			for (String[] param : Form.parameters) {
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
