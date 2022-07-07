package modelo.dao.implementacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import modelo.dao.interfaces.IProfesional;
import modelo.entities.Profesional;

public class MySQLProfesionalDAO implements IProfesional {
	Conexion conexion;

	public MySQLProfesionalDAO() throws SQLException {
		this.conexion = Conexion.getInstance();
	}

	@Override
	public void create(Profesional profesional) throws Exception {
		try {
			this.conexion.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("START TRANSACTION;");
			sql.append("INSERT INTO usuario(correo, clave, nombre_usuario, fecha_nacimiento, run, tipo) ");
			sql.append("VALUES (?,?,?,?,?,?);");
			sql.append("SET @last_id=LAST_INSERT_ID();");
			sql.append("INSERT INTO profesional(fk_id_usuario, titulo, fecha_ingreso) ");
			sql.append("VALUES (@last_id,?,?);");
			sql.append("COMMIT;");

			PreparedStatement st = this.conexion.connection.prepareStatement(sql.toString());
			st.setString(1, profesional.getCorreo());
			st.setString(2, profesional.getClave());
			st.setString(3, profesional.getNombreUsuario());
			st.setString(4, profesional.getFechaNacimiento());
			st.setString(5, profesional.getRun());
			st.setString(6, profesional.getTipo());
			st.setString(7, profesional.getTitulo());
			st.setString(8, profesional.getFechaIngreso());
			st.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			this.conexion.close();
		}
	}

	@Override
	public void update(Profesional profesional) throws Exception {
		try {
			this.conexion.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("START TRANSACTION;");
			sql.append("UPDATE usuario SET correo=?, clave=?, nombre_usuario=?, fecha_nacimiento=?, run=?, tipo=? ");
			sql.append("WHERE id_usuario=?;");
			sql.append("REPLACE INTO profesional (fk_id_usuario, titulo, fecha_ingreso) ");
			sql.append("VALUES (?,?,?);");
			sql.append("COMMIT;");

			PreparedStatement st = this.conexion.connection.prepareStatement(sql.toString());
			st.setString(1, profesional.getCorreo());
			st.setString(2, profesional.getClave());
			st.setString(3, profesional.getNombreUsuario());
			st.setString(4, profesional.getFechaNacimiento());
			st.setString(5, profesional.getRun());
			st.setString(6, profesional.getTipo());
			st.setInt(7, profesional.getIdUsuario());
			st.setInt(8, profesional.getIdUsuario());
			st.setString(9, profesional.getTitulo());
			st.setString(10, profesional.getFechaIngreso());
			st.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			this.conexion.close();
		}
	}

	@Override
	public void delete(Profesional profesional) throws Exception {
		try {
			this.conexion.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE usuario WHERE id_usuario=?;");// Restrict cascade delete this.conexion

			PreparedStatement st = this.conexion.connection.prepareStatement(sql.toString());
			st.setInt(1, profesional.getIdUsuario());
			st.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			this.conexion.close();
		}
	}

	@Override
	public Profesional readOne(int idProfesional) throws Exception {
		Profesional profesional = null;
		try {
			this.conexion.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario, profesional ");
			sql.append("WHERE profesional.fk_id_usuario=usuario.id_usuario ");
			sql.append("AND profesional.fk_id_usuario=?;");

			PreparedStatement st = this.conexion.connection.prepareStatement(sql.toString());
			st.setInt(1, idProfesional);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				profesional = new Profesional();
				profesional.setIdUsuario(rs.getInt("id_usuario"));
				profesional.setCorreo(rs.getString("correo"));
				profesional.setClave(rs.getString("clave"));
				profesional.setNombreUsuario(rs.getString("nombre_usuario"));
				profesional.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				profesional.setRun(rs.getString("run"));
				profesional.setTipo(rs.getString("tipo"));
				profesional.setTitulo(rs.getString("titulo"));
				profesional.setFechaIngreso(rs.getString("fecha_ingreso"));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.conexion.close();
		}
		return profesional;
	}

	@Override
	public ArrayList<Profesional> readAll() throws Exception {
		ArrayList<Profesional> listProfesionals = new ArrayList<>();
		try {
			this.conexion.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario, profesional ");
			sql.append("WHERE profesional.fk_id_usuario=usuario.id_usuario;");

			PreparedStatement st = this.conexion.connection.prepareStatement(sql.toString());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Profesional profesional = new Profesional();
				profesional.setIdUsuario(rs.getInt("id_usuario"));
				profesional.setCorreo(rs.getString("correo"));
				profesional.setClave(rs.getString("clave"));
				profesional.setNombreUsuario(rs.getString("nombre_usuario"));
				profesional.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				profesional.setRun(rs.getString("run"));
				profesional.setTipo(rs.getString("tipo"));
				profesional.setTitulo(rs.getString("titulo"));
				profesional.setFechaIngreso(rs.getString("fecha_ingreso"));
				listProfesionals.add(profesional);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.conexion.close();
		}
		return listProfesionals;
	}
}
