/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelo.Museo;
import com.gf.modelo.Pais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ruben
 */
public class MuseoDAO {

    private Connection con;

    public MuseoDAO(Connection con) {
        this.con = con;
    }

    public Museo obtenerMuseoPorId(int idMuseo) throws SQLException {
        Museo museo = null;
        String sql = "SELECT * FROM museo WHERE id_museo = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idMuseo);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            PaisDAO pdao = new PaisDAO(con);
            museo = new Museo(idMuseo, rs.getString("nombre_museo"), pdao.obtenerPaisPorId(rs.getInt("id_pais")));
        }
        return museo;
    }

}
