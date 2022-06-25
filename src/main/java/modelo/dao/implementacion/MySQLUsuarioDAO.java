package modelo.dao.implementacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import conexion.Conexion;
import modelo.dao.interfaces.IUsuario;
import modelo.entities.Usuario;

public class MySQLUsuarioDAO implements IUsuario {

	Conexion conexion;
	
	public MySQLUsuarioDAO() {
		this.conexion = Conexion.getInstance();
	}
	
	@Override
	public void create(Usuario usuario) throws Exception {
		try {
			
			this.conexion.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO usuario(correo, clave, nombre_usuario, fecha_nacimiento, run, tipo) ");
			sql.append("VALUES (?,?,?,?,?,?);");

			PreparedStatement st = this.conexion.connection.prepareStatement(sql.toString());
			st.setString(1, usuario.getCorreo());
			st.setString(2, usuario.getClave());
			st.setString(3, usuario.getNombreUsuario());
			st.setString(4, usuario.getFechaNacimiento());
			st.setString(5, usuario.getRun());
			st.setString(6, usuario.getTipo());
			st.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			this.conexion.close();
		}
	}

	@Override
	public void update(Usuario usuario) throws Exception {
		try {
			this.conexion.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE usuario SET correo=?, clave=?, nombre_usuario=?, fecha_nacimiento=?, run=?, tipo=? ");
			sql.append("WHERE id_usuario=?;");

			PreparedStatement st = this.conexion.connection.prepareStatement(sql.toString());
			st.setString(1, usuario.getNombreUsuario());
			st.setString(2, usuario.getCorreo());
			st.setString(3, usuario.getClave());
			st.setString(4, usuario.getFechaNacimiento());
			st.setString(5, usuario.getRun());
			st.setString(6, usuario.getTipo());
			st.setInt(7, usuario.getIdUsuario());
			st.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			this.conexion.close();
		}
	}

	@Override
	public void delete(Usuario usuario) throws Exception {
		try {
			this.conexion.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE usuario WHERE id_usuario=?;");

			PreparedStatement st = this.conexion.connection.prepareStatement(sql.toString());
			st.setInt(1, usuario.getIdUsuario());
			st.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			this.conexion.close();
		}
	}

	@Override
	public Usuario readOne(int idUsuario) throws Exception {
		Usuario usuario = null;
		try {
			this.conexion.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario WHERE id_usuario=?;");// Tambien borrara la table con relacion de llave foranea

			PreparedStatement st = this.conexion.connection.prepareStatement(sql.toString());
			st.setInt(1, idUsuario);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("id_usuario"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setClave(rs.getString("clave"));
				usuario.setNombreUsuario(rs.getString("nombre_usuario"));
				usuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				usuario.setRun(rs.getString("run"));
				usuario.setTipo(rs.getString("tipo"));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.conexion.close();
		}
		return usuario;
	}

	@Override
	public ArrayList<Usuario> readAll() throws Exception {
		ArrayList<Usuario> listUsuarios = new ArrayList<Usuario>();
		try {
			this.conexion.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario;");

			PreparedStatement st = this.conexion.connection.prepareStatement(sql.toString());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("id_usuario"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setClave(rs.getString("clave"));
				usuario.setNombreUsuario(rs.getString("nombre_usuario"));
				usuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				usuario.setRun(rs.getString("run"));
				usuario.setTipo(rs.getString("tipo"));
				listUsuarios.add(usuario);
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.conexion.close();
		}
		return listUsuarios;
	}

	public Usuario verifyCredentials(String correo, String clave) throws Exception {
		Usuario usuario = null;
		try {
			this.conexion.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario WHERE correo=? AND clave=?;");

			PreparedStatement st = this.conexion.connection.prepareStatement(sql.toString());
			st.setString(1, correo);
			st.setString(2, clave);

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("id_usuario"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setClave(rs.getString("clave"));
				usuario.setNombreUsuario(rs.getString("nombre_usuario"));
				usuario.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				usuario.setRun(rs.getString("run"));
				usuario.setTipo(rs.getString("tipo"));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.conexion.close();
		}
		return usuario;
	}

}
