/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ruben
 */
public class ConexionBD {

    private Connection conn;
    private final String MYSQL_BD_URL = "jdbc:mysql://localhost:3306/dim_gf";
    private final String MYSQL_USER = "root";
    private final String MYSQL_PASSWORD = "";

    public ConexionBD() {
    }
    
    

    public Connection getConnection() throws SQLException {
        this.conn = DriverManager.getConnection(this.MYSQL_BD_URL, this.MYSQL_USER, this.MYSQL_PASSWORD);
        return this.conn;
    }
}
