package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	protected static Conexion instance = null;
	public Connection connection;

	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // org.postgresql.com.Driver
	private final String DB_URL = "jdbc:mysql://localhost:3306/javam5"; // jdbc:postgresql://localhost:5432/ejemplo
	private final String USER = "root";
	private final String PASS = "";

	private Conexion() {
	};

	public static Conexion getInstance(){
		if (Conexion.instance == null) {
			Conexion.instance = new Conexion();
		}
		return Conexion.instance;
	}

	public void connect() throws Exception {
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			System.out.println("Error en la conexion_ " + e);
			throw e;
		}
	}

	public void close() throws Exception {
		if (connection != null) {
			if (!connection.isClosed()) {
				connection.close();
			}
		}
	}
}
