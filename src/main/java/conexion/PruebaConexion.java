package conexion;

import java.sql.Connection;

public class PruebaConexion {

    public static void main(String[] args) {

        Conexion conexion = new Conexion();

        Connection con = conexion.conectar();

        if (con != null) {
            System.out.println("TODO CORRECTO");
        } else {
            System.out.println("NO SE PUDO CONECTAR");
        }
    }
}