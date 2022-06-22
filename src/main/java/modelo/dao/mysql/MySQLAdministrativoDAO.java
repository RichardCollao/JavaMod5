package modelo.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.dao.interfaces.IAdministrativo;
import modelo.entities.Administrativo;

public class MySQLAdministrativoDAO extends Conexion implements IAdministrativo {
	public int last_inserted_id;

	@Override
	public void create(Administrativo administrativo) throws Exception {
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("START TRANSACTION;");
			sql.append("INSERT INTO usuario(correo, clave, nombre_usuario, fecha_nacimiento, run, tipo) ");
			sql.append("VALUES (?,?,?,?,?,?);");
			sql.append("SET @last_id = LAST_INSERT_ID();");
			sql.append("INSERT INTO administrativo(fk_id_usuario, area, experiencia_previa) VALUES (@last_id,?,?);");
			sql.append("COMMIT;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setString(1, administrativo.getCorreo());
			st.setString(2, administrativo.getClave());
			st.setString(3, administrativo.getNombreUsuario());
			st.setString(4, administrativo.getFechaNacimiento());
			st.setString(5, administrativo.getRun());
			st.setString(6, administrativo.getTipo());
			st.setString(7, administrativo.getArea());
			st.setString(8, administrativo.getExperienciaPrevia());
			st.execute();

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
	public void update(Administrativo administrativo) throws Exception {
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("START TRANSACTION;");
			sql.append("UPDATE usuario SET correo=?, clave=?, nombre_usuario=?, fecha_nacimiento=?, run=?, tipo=? ");
			sql.append("WHERE id_usuario=?;");
			sql.append("UPDATE administrativo SET area=?, experiencia_previa=? WHERE fk_id_usuario=?;");
			sql.append("COMMIT;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setString(1, administrativo.getCorreo());
			st.setString(2, administrativo.getClave());
			st.setString(3, administrativo.getNombreUsuario());
			st.setString(4, administrativo.getFechaNacimiento());
			st.setString(5, administrativo.getRun());
			st.setString(6, administrativo.getTipo());
			st.setInt(7, administrativo.getIdUsuario());
			st.setString(8, administrativo.getArea());
			st.setString(9, administrativo.getExperienciaPrevia());
			st.setInt(10, administrativo.getIdUsuario());
			st.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
	}

	@Override
	public void delete(Administrativo administrativo) throws Exception {
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE usuario WHERE id_usuario=?;");// Restrict cascade delete this

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setInt(1, administrativo.getIdUsuario());
			st.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
	}

	@Override
	public Administrativo readOne(int idAdministrativo) throws Exception {
		Administrativo administrativo = null;
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario, administrativo ");
			sql.append("WHERE administrativo.fk_id_usuario=usuario.id_usuario ");
			sql.append("AND administrativo.fk_id_usuario=?;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setInt(1, idAdministrativo);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				administrativo = new Administrativo();
				administrativo.setIdUsuario(rs.getInt("id_usuario"));
				administrativo.setCorreo(rs.getString("correo"));
				administrativo.setClave(rs.getString("clave"));
				administrativo.setNombreUsuario(rs.getString("nombre_usuario"));
				administrativo.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				administrativo.setRun(rs.getString("run"));
				administrativo.setTipo(rs.getString("tipo"));
				administrativo.setArea(rs.getString("area"));
				administrativo.setExperienciaPrevia(rs.getString("experiencia_previa"));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
		return administrativo;
	}

	@Override
	public ArrayList<Administrativo> readAll() throws Exception {
		ArrayList<Administrativo> listAdministrativos = new ArrayList<>();
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario, administrativo ");
			sql.append("WHERE administrativo.fk_id_usuario=usuario.id_usuario;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Administrativo administrativo = new Administrativo();
				administrativo.setIdUsuario(rs.getInt("id_usuario"));
				administrativo.setCorreo(rs.getString("correo"));
				administrativo.setClave(rs.getString("clave"));
				administrativo.setNombreUsuario(rs.getString("nombre_usuario"));
				administrativo.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				administrativo.setRun(rs.getString("run"));
				administrativo.setTipo(rs.getString("tipo"));
				administrativo.setArea(rs.getString("area"));
				administrativo.setExperienciaPrevia(rs.getString("experiencia_previa"));
				listAdministrativos.add(administrativo);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
		return listAdministrativos;
	}
}
