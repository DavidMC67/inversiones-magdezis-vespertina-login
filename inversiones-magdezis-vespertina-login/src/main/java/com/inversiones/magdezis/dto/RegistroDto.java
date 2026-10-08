package com.inversiones.magdezis.dto;

public class RegistroDto {

    private final String nombre;
    private final String apellido;
    private final String correo;
    private final String contrasena;
    private final int idRol;

    public RegistroDto(String nombre, String apellido, String correo, String contrasena, int idRol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getIdRol() {
        return idRol;
    }
}
