/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.main;

import com.gf.dao.PuntuacionDAO;
import com.gf.modelo.Puntuacion;
import java.sql.SQLException;

/**
 *
 * @author alfgonru
 */
public class MAINCHUNGO {
    public static void main(String[] args) throws SQLException {
        PuntuacionDAO pdao = new PuntuacionDAO();
    
    pdao.guardarPuntuacion(new Puntuacion(0, "Ganadoro", 20, 40, 50));
    }
}
