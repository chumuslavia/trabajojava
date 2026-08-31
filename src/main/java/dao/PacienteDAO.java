package dao;

import conexion.Conexion;
import modelo.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // LISTAR TODOS LOS PACIENTES
    public List<Paciente> listar() {

        List<Paciente> lista = new ArrayList<>();

        String sql = """
                SELECT id_paciente, dni, nombres, apellidos,
                       fecha_nacimiento, sexo, telefono,
                       direccion, correo
                FROM pacientes
                ORDER BY id_paciente DESC
                """;

        try (
            Connection con = new Conexion().conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Paciente paciente = new Paciente();

                paciente.setIdPaciente(rs.getInt("id_paciente"));
                paciente.setDni(rs.getString("dni"));
                paciente.setNombres(rs.getString("nombres"));
                paciente.setApellidos(rs.getString("apellidos"));
                paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setDireccion(rs.getString("direccion"));
                paciente.setCorreo(rs.getString("correo"));

                lista.add(paciente);
            }

        } catch (Exception e) {
            System.out.println("Error al listar pacientes: " + e.getMessage());
        }

        return lista;
    }

    // BUSCAR PACIENTES
    public List<Paciente> buscar(String texto) {

        List<Paciente> lista = new ArrayList<>();

        String sql = """
                SELECT id_paciente, dni, nombres, apellidos,
                       fecha_nacimiento, sexo, telefono,
                       direccion, correo
                FROM pacientes
                WHERE dni LIKE ?
                   OR nombres LIKE ?
                   OR apellidos LIKE ?
                ORDER BY id_paciente DESC
                """;

        try (
            Connection con = new Conexion().conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            String busqueda = "%" + texto + "%";

            ps.setString(1, busqueda);
            ps.setString(2, busqueda);
            ps.setString(3, busqueda);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Paciente paciente = new Paciente();

                    paciente.setIdPaciente(rs.getInt("id_paciente"));
                    paciente.setDni(rs.getString("dni"));
                    paciente.setNombres(rs.getString("nombres"));
                    paciente.setApellidos(rs.getString("apellidos"));
                    paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    paciente.setSexo(rs.getString("sexo"));
                    paciente.setTelefono(rs.getString("telefono"));
                    paciente.setDireccion(rs.getString("direccion"));
                    paciente.setCorreo(rs.getString("correo"));

                    lista.add(paciente);
                }
            }

        } catch (Exception e) {
            System.out.println("Error al buscar pacientes: " + e.getMessage());
        }

        return lista;
    }

    // BUSCAR PACIENTE POR ID
    public Paciente buscarPorId(int idPaciente) {

        Paciente paciente = null;

        String sql = """
                SELECT id_paciente, dni, nombres, apellidos,
                       fecha_nacimiento, sexo, telefono,
                       direccion, correo
                FROM pacientes
                WHERE id_paciente = ?
                """;

        try (
            Connection con = new Conexion().conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idPaciente);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    paciente = new Paciente();

                    paciente.setIdPaciente(rs.getInt("id_paciente"));
                    paciente.setDni(rs.getString("dni"));
                    paciente.setNombres(rs.getString("nombres"));
                    paciente.setApellidos(rs.getString("apellidos"));
                    paciente.setFechaNacimiento(
                            rs.getDate("fecha_nacimiento")
                    );
                    paciente.setSexo(rs.getString("sexo"));
                    paciente.setTelefono(rs.getString("telefono"));
                    paciente.setDireccion(rs.getString("direccion"));
                    paciente.setCorreo(rs.getString("correo"));
                }
            }

        } catch (Exception e) {
            System.out.println(
                    "Error al buscar paciente por ID: "
                    + e.getMessage()
            );
        }

        return paciente;
    }

    // INSERTAR PACIENTE
    public boolean insertar(Paciente paciente) {

        String sql = """
                INSERT INTO pacientes
                (dni, nombres, apellidos, fecha_nacimiento,
                 sexo, telefono, direccion, correo)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (
            Connection con = new Conexion().conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, paciente.getDni());
            ps.setString(2, paciente.getNombres());
            ps.setString(3, paciente.getApellidos());
            ps.setDate(4, paciente.getFechaNacimiento());
            ps.setString(5, paciente.getSexo());
            ps.setString(6, paciente.getTelefono());
            ps.setString(7, paciente.getDireccion());
            ps.setString(8, paciente.getCorreo());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(
                    "Error al insertar paciente: "
                    + e.getMessage()
            );
            return false;
        }
    }

    // MODIFICAR PACIENTE
    public boolean modificar(Paciente paciente) {

        String sql = """
                UPDATE pacientes
                SET dni = ?,
                    nombres = ?,
                    apellidos = ?,
                    fecha_nacimiento = ?,
                    sexo = ?,
                    telefono = ?,
                    direccion = ?,
                    correo = ?
                WHERE id_paciente = ?
                """;

        try (
            Connection con = new Conexion().conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, paciente.getDni());
            ps.setString(2, paciente.getNombres());
            ps.setString(3, paciente.getApellidos());
            ps.setDate(4, paciente.getFechaNacimiento());
            ps.setString(5, paciente.getSexo());
            ps.setString(6, paciente.getTelefono());
            ps.setString(7, paciente.getDireccion());
            ps.setString(8, paciente.getCorreo());

            // AQUÍ SE UTILIZA EL ID DEL PACIENTE
            ps.setInt(9, paciente.getIdPaciente());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(
                    "Error al modificar paciente: "
                    + e.getMessage()
            );
            return false;
        }
    }

    // ELIMINAR PACIENTE
    public boolean eliminar(int idPaciente) {

        String sql = """
                DELETE FROM pacientes
                WHERE id_paciente = ?
                """;

        try (
            Connection con = new Conexion().conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idPaciente);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(
                    "Error al eliminar paciente: "
                    + e.getMessage()
            );
            return false;
        }
    }
}