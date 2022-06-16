package model.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entities.Profesional;
import model.dao.interfaces.IProfesional;

public class MySQLProfesionalDAO extends Conexion implements IProfesional {
	public int last_inserted_id;
	
	@Override
	public void create(Profesional profesional) throws Exception {
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("START TRANSACTION;");
			sql.append("INSERT INTO usuario(correo, clave, nombre_usuario, fecha_nacimiento, run) VALUES (?,?,?,?,?);");
			sql.append("SET @last_id = LAST_INSERT_ID();");
			sql.append("INSERT INTO profesional(fk_id_usuario, titulo, fecha_ingreso) VALUES (@last_id,?,?);");
			sql.append("COMMIT;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setString(1, profesional.getCorreo());
			st.setString(2, profesional.getClave());
			st.setString(3, profesional.getNombreUsuario());
			st.setString(4, profesional.getFechaNacimiento());
			st.setString(5, profesional.getRun());
			st.setString(6, profesional.getTitulo());
			st.setString(7, profesional.getFechaIngreso());

			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				last_inserted_id = rs.getInt(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
	}
	
	@Override
	public void update(Profesional profesional) throws Exception {
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("START TRANSACTION;");
			sql.append("UPDATE usuario SET correo=?, clave=?, nombre_usuario=?, fecha_nacimiento=?, run=? WHERE id_usuario=?;");
			sql.append("UPDATE profesional SET area=?, experiencia_previa=? WHERE fk_id_usuario=?;");
			sql.append("COMMIT;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setString(1, profesional.getCorreo());
			st.setString(2, profesional.getClave());
			st.setString(4, profesional.getNombreUsuario());
			st.setString(4, profesional.getFechaNacimiento());
			st.setString(5, profesional.getRun());
			st.setInt(6, profesional.getIdUsuario());
			st.setString(7, profesional.getTitulo());
			st.setString(8, profesional.getFechaIngreso());
			st.setInt(9, profesional.getIdUsuario());
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
	}

	@Override
	public void delete(Profesional profesional) throws Exception {
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE usuario WHERE id_usuario=?;");// Restrict cascade delete this

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setInt(1, profesional.getIdUsuario());
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
	}

	@Override
	public Profesional readOne(int idProfesional) throws Exception {
		Profesional profesional = new Profesional();
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario, profesional ");
			sql.append("WHERE profesional.fk_id_usuario=usuario.id_usuario ");
			sql.append("AND profesional.fk_id_usuario=?;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setInt(1, idProfesional);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				profesional.setIdUsuario(rs.getInt("id_profesional"));
				profesional.setCorreo(rs.getString("correo"));
				profesional.setClave(rs.getString("clave"));
				profesional.setNombreUsuario(rs.getString("nombre"));
				profesional.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				profesional.setTitulo(rs.getString("titulo"));
				profesional.setFechaIngreso(rs.getString("fecha_ingreso"));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
		return profesional;
	}

	@Override
	public List<Profesional> readAll() throws Exception {
		List<Profesional> listProfesionals = new ArrayList<>();
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario, profesional ");
			sql.append("WHERE profesional.fk_id_usuario=usuario.id_usuario;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Profesional profesional = new Profesional();
				profesional.setIdUsuario(rs.getInt("id_profesional"));
				profesional.setCorreo(rs.getString("correo"));
				profesional.setClave(rs.getString("clave"));
				profesional.setNombreUsuario(rs.getString("nombre"));
				profesional.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				profesional.setTitulo(rs.getString("titulo"));
				profesional.setFechaIngreso(rs.getString("fecha_ingreso"));
				listProfesionals.add(profesional);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
		return listProfesionals;
	}
}
