package modelo.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.dao.interfaces.ICapacitacion;
import modelo.entities.Capacitacion;

public class MySQLCapacitacionDAO extends Conexion implements ICapacitacion {

	@Override
	public void create(Capacitacion capacitacion) throws Exception {
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO capacitacion(rut_empresa, dia, hora, lugar, duracion, cantidad_asistentes) ");
			sql.append("VALUES (?,?,?,?,?,?);");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setString(1, capacitacion.getRutEmpresa());
			st.setString(2, capacitacion.getDia());
			st.setString(3, capacitacion.getHora());
			st.setString(4, capacitacion.getLugar());
			st.setString(5, capacitacion.getDuracion());
			st.setInt(6, capacitacion.getCantidadAsistentes());
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
	}

	@Override
	public void update(Capacitacion capacitacion) throws Exception {
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE capacitacion ");
			sql.append("SET rut_empresa=?, dia=?, hora=?, lugar=?, duracion=?, cantidad_asistentes=? ");
			sql.append("WHERE id_capacitacion=?;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setString(1, capacitacion.getRutEmpresa());
			st.setString(2, capacitacion.getDia());
			st.setString(3, capacitacion.getHora());
			st.setString(4, capacitacion.getLugar());
			st.setString(5, capacitacion.getDuracion());
			st.setInt(6, capacitacion.getCantidadAsistentes());
			st.setInt(7, capacitacion.getIdCapacitacion());
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
	}

	@Override
	public void delete(Capacitacion capacitacion) throws Exception {
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE capacitacion WHERE id_capacitacion=?;");
			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setInt(1, capacitacion.getIdCapacitacion());
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
	}

	@Override
	public Capacitacion readOne(int idCapacitacion) throws Exception {
		Capacitacion capacitacion = null;
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM capacitacion ");
			sql.append("WHERE id_capacitacion=?;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setInt(1, idCapacitacion);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				capacitacion = new Capacitacion();
				capacitacion.setIdCapacitacion(rs.getInt("id_capacitacion"));
				capacitacion.setRutEmpresa(rs.getString("rut_empresa"));
				capacitacion.setDia(rs.getString("dia"));
				capacitacion.setHora(rs.getString("hora"));
				capacitacion.setLugar(rs.getString("lugar"));
				capacitacion.setDuracion(rs.getString("duracion"));
				capacitacion.setCantidadAsistentes(rs.getInt("cantidad_asistente"));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
		return capacitacion;
	}

	@Override
	public ArrayList<Capacitacion> readAll() throws Exception {
		ArrayList<Capacitacion> listCapacitaciones = new ArrayList<Capacitacion>();
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM capacitacion;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Capacitacion capacitacion = new Capacitacion();
				capacitacion.setRutEmpresa(rs.getString("rut_empresa"));
				capacitacion.setDia(rs.getString("dia"));
				capacitacion.setHora(rs.getString("hora"));
				capacitacion.setLugar(rs.getString("lugar"));
				capacitacion.setDuracion(rs.getString("duracion"));
				capacitacion.setCantidadAsistentes(rs.getInt("cantidad_asistentes"));
				listCapacitaciones.add(capacitacion);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
		return listCapacitaciones;
	}
}
