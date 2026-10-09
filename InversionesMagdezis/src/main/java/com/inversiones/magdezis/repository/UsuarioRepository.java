package com.inversiones.magdezis.repository;

import com.inversiones.magdezis.config.DatabaseConnection;
import com.inversiones.magdezis.model.Rol;
import com.inversiones.magdezis.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepository {

    public boolean existeCorreo(String correo) throws SQLException {
        String sql = "select count(*) from usuarios where correo_usuario = ?";
        try (Connection conexion = DatabaseConnection.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public void guardar(Usuario usuario) throws SQLException {
        String sql = "insert into usuarios (nombre_usuario, apellido_usuario, correo_usuario, contrasena_usuario, id_rol) values (?, ?, ?, ?, ?)";
        try (Connection conexion = DatabaseConnection.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getApellidoUsuario());
            ps.setString(3, usuario.getCorreoUsuario());
            ps.setString(4, usuario.getContrasenaUsuario());
            ps.setInt(5, usuario.getRol().getIdRol());
            ps.executeUpdate();
        }
    }

    public Usuario buscarPorCorreo(String correo) throws SQLException {
        String sql = "select u.id_usuario, u.nombre_usuario, u.apellido_usuario, u.correo_usuario, u.contrasena_usuario, r.id_rol, r.nombre_rol "
                + "from usuarios u inner join roles r on u.id_rol = r.id_rol where u.correo_usuario = ?";
        try (Connection conexion = DatabaseConnection.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setApellidoUsuario(rs.getString("apellido_usuario"));
                usuario.setCorreoUsuario(rs.getString("correo_usuario"));
                usuario.setContrasenaUsuario(rs.getString("contrasena_usuario"));
                usuario.setRol(new Rol(rs.getInt("id_rol"), rs.getString("nombre_rol")));
                return usuario;
            }
        }
    }
}
