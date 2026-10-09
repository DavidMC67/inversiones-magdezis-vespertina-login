package com.inversiones.magdezis.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Credentials.URL, Credentials.USER, Credentials.PASSWORD);
    }
}
