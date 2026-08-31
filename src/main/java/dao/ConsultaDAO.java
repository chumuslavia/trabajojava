package dao;

import conexion.Conexion;
import modelo.Consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConsultaDAO {

    private final Conexion conexion;

    public ConsultaDAO() {
        conexion = new Conexion();
    }

    public boolean registrar(Consulta consulta) {

        String sql = """
                INSERT INTO consultas
                (id_paciente, id_usuario, motivo, diagnostico,
                 tratamiento, observaciones)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, consulta.getIdPaciente());
            ps.setInt(2, consulta.getIdUsuario());

            // El formulario no tiene campo "motivo",
            // por eso usamos el diagnóstico como motivo.
            ps.setString(3, consulta.getDiagnostico());

            ps.setString(4, consulta.getDiagnostico());
            ps.setString(5, consulta.getTratamiento());
            ps.setString(6, consulta.getObservaciones());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                "Error al registrar consulta: " + e.getMessage()
            );

            return false;
        }
    }
}