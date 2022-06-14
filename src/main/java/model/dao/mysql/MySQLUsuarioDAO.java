package model.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entities.Usuario;
import model.dao.interfaces.IUsuario;


public class MySQLUsuarioDAO extends Conexion implements IUsuario {

    @Override
    public void register(Usuario usuario) throws Exception {
        try {
            this.connect();
            PreparedStatement st = this.connection.prepareStatement("INSERT INTO usuario(nombre, fecha_nacimiento, run) VALUES (?,?,?)");
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
    public void modify(Usuario usuario) throws Exception {
        try {
            this.connect();
            PreparedStatement st = this.connection.prepareStatement("UPDATE usuario SET nombre=?, fecha_nacimiento=?, run=? WHERE id_usuario = ?)");
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
    public void remove(Usuario usuario) throws Exception {
        try {
            this.connect();
            PreparedStatement st = this.connection.prepareStatement("DELETE usuario WHERE id_usuario = ?)");
            st.setInt(1, usuario.getIdUsuario());
        } catch (Exception e) {
            throw e;
        } finally {
            this.close();
        }
    }

    @Override
    public List<Usuario> getOne(int idUsuario) throws Exception {
        List<Usuario> listUsuarios = new ArrayList<>();
        try {
            this.connect();
            PreparedStatement st = this.connection.prepareStatement("SELECT * FROM usuario WHERE id_usuario=?)");
            st.setInt(1, idUsuario);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre"));
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

    @Override
    public List<Usuario> getAll() throws Exception {
        List<Usuario> listUsuarios = new ArrayList<>();
        try {
            this.connect();
            PreparedStatement st = this.connection.prepareStatement("SELECT * FROM usuario)");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre"));
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
}
