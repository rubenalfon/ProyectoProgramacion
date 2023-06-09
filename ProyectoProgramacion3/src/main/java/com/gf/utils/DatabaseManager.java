/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.utils;

import java.sql.*;
import java.util.logging.*;

/**
 *
 * @author Ruben
 */
public class DatabaseManager {

    private static Connection conn; // La conexión a utilizar

    private static final String MYSQL_BD_URL = "jdbc:mysql://localhost:3306/dim_gf"; // url de la BD
    private static final String MYSQL_USER = "root"; // Usuario con el que conectarse a la BD
    private static final String MYSQL_PASSWORD = ""; // Contraseña con la que conectarse a la BD

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(MYSQL_BD_URL, MYSQL_USER, MYSQL_PASSWORD);
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            getConnection().close();
            conn = null;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
