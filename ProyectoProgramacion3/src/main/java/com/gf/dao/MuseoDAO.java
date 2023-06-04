/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelo.Museo;
import com.gf.utils.ConvertirArrayListACadena;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ruben
 */
public class MuseoDAO {

    private final Connection con;

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

    public Museo obtenerMuseoAleatorio(ArrayList lista) throws SQLException {
        Museo museo = null;

        String sql = "SELECT * FROM museo where id_museo not in "
                + ConvertirArrayListACadena.convertir(lista)
                + " ORDER BY RAND () LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            PaisDAO pdao = new PaisDAO(con);
            museo = new Museo(rs.getInt("id_museo"), rs.getString("nombre_museo"), pdao.obtenerPaisPorId(rs.getInt("id_pais")));
        }
        return museo;
    }
}
