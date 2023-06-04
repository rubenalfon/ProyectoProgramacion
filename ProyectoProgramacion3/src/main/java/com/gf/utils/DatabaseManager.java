/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ruben
 */
public class DatabaseManager {

    private static Connection conn;
    private static DatabaseManager instance;

    private final String MYSQL_BD_URL = "jdbc:mysql://localhost:3306/dim_gf";
    private final String MYSQL_USER = "root";
    private final String MYSQL_PASSWORD = "";

    private DatabaseManager() throws SQLException {
        conn = DriverManager.getConnection(this.MYSQL_BD_URL, this.MYSQL_USER, this.MYSQL_PASSWORD);
    }

    public static Connection getConnection() throws SQLException {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        instance = null;
    }
}
