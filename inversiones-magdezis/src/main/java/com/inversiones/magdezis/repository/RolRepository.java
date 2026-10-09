package com.inversiones.magdezis.repository;

import com.inversiones.magdezis.config.DatabaseConnection;
import com.inversiones.magdezis.model.Rol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolRepository {

    public List<Rol> listar() throws SQLException {
        List<Rol> roles = new ArrayList<>();
        String sql = "select id_rol, nombre_rol from roles order by id_rol";
        try (Connection conexion = DatabaseConnection.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                roles.add(new Rol(rs.getInt("id_rol"), rs.getString("nombre_rol")));
            }
        }
        return roles;
    }
}
