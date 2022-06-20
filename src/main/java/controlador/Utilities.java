package controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Utilities {

	public static String camelCaseToUnderscores(String camel) {
		String underscore;
		underscore = String.valueOf(Character.toLowerCase(camel.charAt(0)));
		for (int i = 1; i < camel.length(); i++) {
			underscore += Character.isLowerCase(camel.charAt(i)) ? String.valueOf(camel.charAt(i)) : "_" + String.valueOf(Character.toLowerCase(camel.charAt(i)));
		}
		return underscore;
	}

	public static Integer parseIntNotNull(String str) {
		Integer r = null;
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			r = 0;
		}
		r = r != null ? r : 0;
		return r;
	}

	public static String stringNotNull(String str) {
		String result = str != null ? str : "";
		return result;
	}
	
	public static boolean compareExpression(String exp, String str) {
		boolean result = false;
		Matcher matcher;
		matcher = Pattern.compile(exp).matcher(str);
		if (matcher.find()) {
			result = true;
		}
		return result;
	}
}
