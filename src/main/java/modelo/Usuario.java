package modelo;

import java.time.LocalDateTime;

public class Usuario {

    private int idUsuario;
    private String usuario;
    private String nombre;
    private String password;
    private String rol;
    private String estado;
    private LocalDateTime fechaRegistro;

    public Usuario() {
    }

    public Usuario(int idUsuario, String usuario, String nombre,
                   String password, String rol, String estado,
                   LocalDateTime fechaRegistro) {

        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}