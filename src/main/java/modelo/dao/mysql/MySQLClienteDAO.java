package modelo.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.dao.interfaces.ICliente;
import modelo.entities.Cliente;

public class MySQLClienteDAO extends Conexion implements ICliente {
	public int last_inserted_id;

	@Override
	public void create(Cliente cliente) throws Exception {
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("START TRANSACTION;");
			sql.append("INSERT INTO usuario(correo, clave, nombre_usuario, fecha_nacimiento, run, tipo) ");
			sql.append("VALUES (?,?,?,?,?,?);");
			sql.append("SET @last_id = LAST_INSERT_ID();");
			sql.append("INSERT INTO cliente(fk_id_usuario, nombres, apellidos, telefono, afp, sistema_salud, direccion, comuna) ");
			sql.append("VALUES (@last_id,?,?,?,?,?,?,?);");
			sql.append("COMMIT;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setString(1, cliente.getCorreo());
			st.setString(2, cliente.getClave());
			st.setString(3, cliente.getNombreUsuario());
			st.setString(4, cliente.getFechaNacimiento());
			st.setString(5, cliente.getRun());
			st.setString(6, cliente.getTipo());
			st.setString(7, cliente.getNombres());
			st.setString(8, cliente.getApellidos());
			st.setString(9, cliente.getTelefono());
			st.setString(10, cliente.getAfp());
			st.setInt(11, cliente.getSistemaSalud());
			st.setString(12, cliente.getDireccion());
			st.setString(13, cliente.getComuna());
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
	public void update(Cliente cliente) throws Exception {
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("START TRANSACTION;");
			sql.append("UPDATE usuario SET correo=?, clave=?, nombre_usuario=?, fecha_nacimiento=?, run=?, tipo=? ");
			sql.append("WHERE id_usuario=?;");
			sql.append("UPDATE cliente SET nombres=?, apellidos=?, telefono=?, afp=?, sistema_salud=?, direccion=?, comuna=? ");
			sql.append("WHERE fk_id_usuario=?;");
			sql.append("COMMIT;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setString(1, cliente.getCorreo());
			st.setString(2, cliente.getClave());
			st.setString(4, cliente.getNombreUsuario());
			st.setString(4, cliente.getFechaNacimiento());
			st.setString(5, cliente.getRun());
			st.setString(6, cliente.getTipo());
			st.setInt(7, cliente.getIdUsuario());
			st.setString(8, cliente.getNombres());
			st.setString(9, cliente.getApellidos());
			st.setString(10, cliente.getTelefono());
			st.setString(11, cliente.getAfp());
			st.setInt(12, cliente.getSistemaSalud());
			st.setString(13, cliente.getDireccion());
			st.setString(14, cliente.getComuna());
			st.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
	}

	@Override
	public void delete(Cliente cliente) throws Exception {
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE usuario WHERE id_usuario=?;");// Restrict cascade delete this

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setInt(1, cliente.getIdUsuario());
			st.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
	}

	@Override
	public Cliente readOne(int idCliente) throws Exception {
		Cliente cliente = null;
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario, cliente ");
			sql.append("WHERE cliente.fk_id_usuario=usuario.id_usuario ");
			sql.append("AND cliente.fk_id_usuario=?;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			st.setInt(1, idCliente);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				cliente = new Cliente();
				cliente.setIdUsuario(rs.getInt("id_cliente"));
				cliente.setCorreo(rs.getString("correo"));
				cliente.setClave(rs.getString("clave"));
				cliente.setNombreUsuario(rs.getString("nombre"));
				cliente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				cliente.setRun(rs.getString("run"));
				cliente.setTipo(rs.getString("tipo"));
				cliente.setNombres(rs.getString("nombres"));
				cliente.setApellidos(rs.getString("apellidos"));
				cliente.setTelefono(rs.getString("telefono"));
				cliente.setAfp(rs.getString("afp"));
				cliente.setSistemaSalud(rs.getInt("sistema_salud"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setComuna(rs.getString("comuna"));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
		return cliente;
	}

	@Override
	public ArrayList<Cliente> readAll() throws Exception {
		ArrayList<Cliente> listClientes = new ArrayList<>();
		try {
			this.connect();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM usuario, cliente ");
			sql.append("WHERE cliente.fk_id_usuario=usuario.id_usuario;");

			PreparedStatement st = this.connection.prepareStatement(sql.toString());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdUsuario(rs.getInt("id_cliente"));
				cliente.setCorreo(rs.getString("correo"));
				cliente.setClave(rs.getString("clave"));
				cliente.setNombreUsuario(rs.getString("nombre"));
				cliente.setFechaNacimiento(rs.getString("fecha_nacimiento"));
				cliente.setRun(rs.getString("run"));
				cliente.setTipo(rs.getString("tipo"));
				cliente.setNombres(rs.getString("nombres"));
				cliente.setApellidos(rs.getString("apellidos"));
				cliente.setTelefono(rs.getString("telefono"));
				cliente.setAfp(rs.getString("afp"));
				cliente.setSistemaSalud(rs.getInt("sistema_salud"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setComuna(rs.getString("comuna"));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			throw e;
		} finally {
			this.close();
		}
		return listClientes;
	}
}
