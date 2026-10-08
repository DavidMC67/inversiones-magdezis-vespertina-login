package com.inversiones.magdezis.dto;

public class LoginDto {

    private final String correo;
    private final String contrasena;

    public LoginDto(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }
}
