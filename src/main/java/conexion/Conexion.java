package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/clinica";

    private static final String USUARIO = "root";

    private static final String PASSWORD = "";

    public Connection conectar() {

        Connection con = null;

        try {

            con = DriverManager.getConnection(
                    URL,
                    USUARIO,
                    PASSWORD
            );

            System.out.println("CONEXION EXITOSA A LA BASE DE DATOS");

        } catch (SQLException e) {

            System.out.println("ERROR DE CONEXION");
            System.out.println(e.getMessage());

        }

        return con;
    }
}