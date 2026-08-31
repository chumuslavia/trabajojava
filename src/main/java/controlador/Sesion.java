package controlador;

import modelo.Usuario;

public class Sesion {

    private static Usuario usuarioActual;

    public static void iniciarSesion(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }

    public static boolean haySesion() {
        return usuarioActual != null;
    }

    public static boolean esOwner() {
        return haySesion()
                && "Owner".equalsIgnoreCase(usuarioActual.getRol());
    }

    public static boolean esAdministrador() {
        return haySesion()
                && "Administrador".equalsIgnoreCase(usuarioActual.getRol());
    }
}