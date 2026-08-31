package dao;

import conexion.Conexion;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private final Conexion conexion;

    public UsuarioDAO() {
        conexion = new Conexion();
    }

    // =========================================================
    // LOGIN
    // =========================================================
    public Usuario login(String usuario, String password) {

        String sql = """
                SELECT id_usuario, usuario, nombre, password,
                       rol, estado, fecha_registro
                FROM usuarios
                WHERE usuario = ?
                AND password = ?
                AND estado = 'Activo'
                """;

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Usuario user = new Usuario();

                    user.setIdUsuario(rs.getInt("id_usuario"));
                    user.setUsuario(rs.getString("usuario"));
                    user.setNombre(rs.getString("nombre"));
                    user.setPassword(rs.getString("password"));
                    user.setRol(rs.getString("rol"));
                    user.setEstado(rs.getString("estado"));

                    Timestamp timestamp = rs.getTimestamp("fecha_registro");

                    if (timestamp != null) {
                        user.setFechaRegistro(timestamp.toLocalDateTime());
                    }

                    return user;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al realizar login: " + e.getMessage());
        }

        return null;
    }

    // =========================================================
    // REGISTRAR USUARIO
    // =========================================================
    public boolean registrar(Usuario usuario) {

        String sql = """
                INSERT INTO usuarios
                (usuario, nombre, password, rol, estado)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getRol());
            ps.setString(5, usuario.getEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    // =========================================================
    // LISTAR USUARIOS
    // =========================================================
    public List<Usuario> listar() {

        List<Usuario> lista = new ArrayList<>();

        String sql = """
                SELECT id_usuario, usuario, nombre, password,
                       rol, estado, fecha_registro
                FROM usuarios
                ORDER BY id_usuario
                """;

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Usuario user = new Usuario();

                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setUsuario(rs.getString("usuario"));
                user.setNombre(rs.getString("nombre"));
                user.setPassword(rs.getString("password"));
                user.setRol(rs.getString("rol"));
                user.setEstado(rs.getString("estado"));

                Timestamp timestamp = rs.getTimestamp("fecha_registro");

                if (timestamp != null) {
                    user.setFechaRegistro(timestamp.toLocalDateTime());
                }

                lista.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }

        return lista;
    }

    // =========================================================
    // BUSCAR USUARIO POR ID
    // =========================================================
    public Usuario buscarPorId(int idUsuario) {

        String sql = """
                SELECT id_usuario, usuario, nombre, password,
                       rol, estado, fecha_registro
                FROM usuarios
                WHERE id_usuario = ?
                """;

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Usuario user = new Usuario();

                    user.setIdUsuario(rs.getInt("id_usuario"));
                    user.setUsuario(rs.getString("usuario"));
                    user.setNombre(rs.getString("nombre"));
                    user.setPassword(rs.getString("password"));
                    user.setRol(rs.getString("rol"));
                    user.setEstado(rs.getString("estado"));

                    Timestamp timestamp = rs.getTimestamp("fecha_registro");

                    if (timestamp != null) {
                        user.setFechaRegistro(timestamp.toLocalDateTime());
                    }

                    return user;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }

        return null;
    }

    // =========================================================
    // MODIFICAR USUARIO
    // =========================================================
    public boolean modificar(Usuario usuario) {

        String sql = """
                UPDATE usuarios
                SET usuario = ?,
                    nombre = ?,
                    password = ?,
                    rol = ?
                WHERE id_usuario = ?
                """;

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getRol());
            ps.setInt(5, usuario.getIdUsuario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al modificar usuario: " + e.getMessage());
            return false;
        }
    }

    // =========================================================
    // CAMBIAR ESTADO
    // =========================================================
    public boolean cambiarEstado(int idUsuario, String estado) {

        String sql = """
                UPDATE usuarios
                SET estado = ?
                WHERE id_usuario = ?
                """;

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, idUsuario);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al cambiar estado: " + e.getMessage());
            return false;
        }
    }
}