package model.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entities.Usuario;
import model.dao.interfaces.IUsuario;

public class MySQLUsuarioDAO extends Conexion implements IUsuario {

	@Override
	public void create(Usuario usuario) throws Exception {
		try {
			this.connect();
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO usuario(nombre, fecha_nacimiento, run) VALUES (?,?,?);");
			
			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setString(1, usuario.getNombreUsuario());
			st.setString(2, usuario.getFechaNacimiento());
			st.setString(3, usuario.getRun());
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
	}

	@Override
	public void update(Usuario usuario) throws Exception {
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE usuario SET nombre=?, fecha_nacimiento=?, run=? WHERE id_usuario=?;");
			
			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setString(1, usuario.getNombreUsuario());
			st.setString(2, usuario.getFechaNacimiento());
			st.setString(3, usuario.getRun());
			st.setInt(4, usuario.getIdUsuario());
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
	}

	@Override
	public void delete(Usuario usuario) throws Exception {
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE usuario WHERE id_usuario=?;");
			
			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setInt(1, usuario.getIdUsuario());
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
	}

	@Override
	public Usuario readOne(int idUsuario) throws Exception {
		Usuario usuario = new Usuario();
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario WHERE id_usuario=?;");
			
			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setInt(1, idUsuario);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				usuario.setIdUsuario(rs.getInt("id_usuario"));
				usuario.setNombreUsuario(rs.getString("nombre"));
				usuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				usuario.setRun(rs.getString("run"));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
		return usuario;
	}

	@Override
	public List<Usuario> readAll() throws Exception {
		List<Usuario> listUsuarios = new ArrayList<>();
		try {

			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario;");
			
			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("id_usuario"));
				usuario.setNombreUsuario(rs.getString("nombre_usuario"));
				usuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				usuario.setRun(rs.getString("run"));
				listUsuarios.add(usuario);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
		return listUsuarios;
	}
	
	public Usuario verifyCredentials(String correo, String clave) throws Exception {
		Usuario usuario = null; //new Usuario();
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario WHERE correo=? AND clave=?;");
			
			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setString(1, correo);
			st.setString(2, clave);
			
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				usuario.setIdUsuario(rs.getInt("id_usuario"));
				usuario.setNombreUsuario(rs.getString("correo"));
				usuario.setNombreUsuario(rs.getString("clave"));
				usuario.setNombreUsuario(rs.getString("nombre_usuario"));
				usuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				usuario.setRun(rs.getString("run"));
				usuario.setType(rs.getString("type"));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
		return usuario;
	}
	
}
