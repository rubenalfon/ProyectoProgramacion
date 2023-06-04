/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelo.Museo;
import com.gf.modelo.Pais;
import com.gf.utils.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ruben
 */
public class MuseoDAO {

    public Museo obtenerMuseoPorId(int idMuseo){
        Museo museo = null;
        String sql = "SELECT * FROM museo WHERE id_museo = ?";

        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql);
            ps.setInt(1, idMuseo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PaisDAO pdao = new PaisDAO();
                museo = new Museo(idMuseo, rs.getString("nombre_museo"), rs.getInt("id_pais"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }
        return museo;
    }

}
