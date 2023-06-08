/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelo.Puntuacion;
import com.gf.utils.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alfgonru
 */
public class PuntuacionDAO {

    public ArrayList<Puntuacion> obtenerMejoresPuntuaciones(int num) throws SQLException {
        ArrayList<Puntuacion> listaPuntuaciones = null;
        String sql = "SELECT * FROM puntuacion ORDER BY aciertos DESC LIMIT ?";

        Connection con = DatabaseManager.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, num);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            listaPuntuaciones.add(new Puntuacion(
                    rs.getInt("id_puntuacion"),
                    rs.getString("nombre_usuario"),
                    rs.getInt("aciertos"),
                    rs.getInt("puntos_totales"),
                    rs.getInt("segundos")));
        }
        DatabaseManager.closeConnection();
        return listaPuntuaciones;
    }

}
