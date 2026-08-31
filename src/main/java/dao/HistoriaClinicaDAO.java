package dao;

import conexion.Conexion;
import modelo.HistoriaClinica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HistoriaClinicaDAO {

    public List<HistoriaClinica> listarPorPaciente(int idPaciente) {

        List<HistoriaClinica> lista = new ArrayList<>();

        String sql = """
            SELECT h.id_historia,
                   h.id_paciente,
                   h.id_consulta,
                   h.id_usuario,
                   u.nombre,
                   h.motivo_consulta,
                   h.diagnostico,
                   h.tratamiento,
                   h.observaciones,
                   h.fecha_registro
            FROM historia_clinica h
            INNER JOIN usuarios u
                ON h.id_usuario = u.id_usuario
            WHERE h.id_paciente = ?
            ORDER BY h.fecha_registro DESC
        """;

        Conexion conexion = new Conexion();

        try (
            Connection con = conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idPaciente);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                HistoriaClinica historia = new HistoriaClinica();

                historia.setIdHistoria(
                        rs.getInt("id_historia")
                );

                historia.setIdPaciente(
                        rs.getInt("id_paciente")
                );

                int idConsulta = rs.getInt("id_consulta");

                if (rs.wasNull()) {
                    historia.setIdConsulta(null);
                } else {
                    historia.setIdConsulta(idConsulta);
                }

                historia.setIdUsuario(
                        rs.getInt("id_usuario")
                );

                historia.setNombreUsuario(
                        rs.getString("nombre")
                );

                historia.setMotivoConsulta(
                        rs.getString("motivo_consulta")
                );

                historia.setDiagnostico(
                        rs.getString("diagnostico")
                );

                historia.setTratamiento(
                        rs.getString("tratamiento")
                );

                historia.setObservaciones(
                        rs.getString("observaciones")
                );

                historia.setFechaRegistro(
                        rs.getTimestamp("fecha_registro")
                );

                lista.add(historia);
            }

        } catch (Exception e) {

            System.out.println(
                    "ERROR AL LISTAR HISTORIAS CLÍNICAS: "
                    + e.getMessage()
            );
        }

        return lista;
    }
}