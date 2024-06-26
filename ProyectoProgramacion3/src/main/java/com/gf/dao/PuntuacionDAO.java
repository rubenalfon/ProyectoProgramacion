/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelo.Puntuacion;
import com.gf.utils.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author alfgonru
 */
public class PuntuacionDAO {

    public ArrayList<Puntuacion> obtenerMejoresPuntuaciones(int num) throws SQLException {
        ArrayList<Puntuacion> listaPuntuaciones = new ArrayList<>();
        String sql = "SELECT * FROM puntuacion ORDER BY aciertos DESC, segundos ASC  LIMIT ?";

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

    public boolean guardarPuntuacion(Puntuacion puntuacion) throws SQLException {
        boolean confirmacion = false;
        String sql = "INSERT INTO puntuacion (nombre_usuario, aciertos, puntos_totales, segundos) values (?,?,?,?)";

        Connection con = DatabaseManager.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, puntuacion.getNombreUsuario());
        ps.setInt(2, puntuacion.getAciertos());
        ps.setInt(3, puntuacion.getPuntosTotales());
        ps.setInt(4, puntuacion.getSegundos());

        if (ps.executeUpdate() == 1) {
            confirmacion = true;
        }

        DatabaseManager.closeConnection();
        return confirmacion;
    }
}
