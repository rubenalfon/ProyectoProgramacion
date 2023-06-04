/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelo.Pais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ruben
 */
public class PaisDAO {

    private final Connection con;

    public PaisDAO(Connection con) {
        this.con = con;
    }

    public Pais obtenerPaisPorId(int idPais) throws SQLException {
        Pais pais = null;

        String sql = "SELECT * FROM pais WHERE id_pais = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idPais);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            pais = new Pais(idPais, rs.getString("nombre_pais"));
        }
        return pais;
    }
}
