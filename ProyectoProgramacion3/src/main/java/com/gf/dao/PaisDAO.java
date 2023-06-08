/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelo.Pais;
import com.gf.utils.DatabaseManager;
import java.sql.*;

/**
 *
 * @author Ruben
 */
public class PaisDAO {

    public Pais obtenerPaisPorId(int idPais) throws SQLException {
        Pais pais = null;
        String sql = "SELECT * FROM pais WHERE id_pais = ?";
        PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql);
        ps.setInt(1, idPais);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            pais = new Pais(idPais, rs.getString("nombre_pais"));
        }
        DatabaseManager.closeConnection();

        return pais;
    }
}
