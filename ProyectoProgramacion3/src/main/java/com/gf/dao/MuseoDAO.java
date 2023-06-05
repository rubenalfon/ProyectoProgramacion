/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelo.Museo;
import com.gf.utils.ConvertirArrayListACadena;
import com.gf.utils.DatabaseManager;
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

    public Museo obtenerMuseoPorId(int idMuseo) {
        Museo museo = null;
        String sql = "SELECT * FROM museo WHERE id_museo = ?";

        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMuseo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PaisDAO pdao = new PaisDAO();

                museo = new Museo(rs.getInt("id_museo"), rs.getString("nombre_museo"), pdao.obtenerPaisPorId(rs.getInt("id_pais")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }
        return museo;
    }

    public Museo obtenerMuseoAleatorio(ArrayList lista) {
        Museo museo = null;
        String sql;

        if (lista == null || lista.isEmpty()) {
            sql = "SELECT * FROM museo where id_museo ORDER BY RAND () LIMIT 1"; // Obtener una pintura aleatoria.
        } else {
            sql = "SELECT * FROM museo where id_museo not in "
                    + ConvertirArrayListACadena.convertir(lista)
                    + " ORDER BY RAND () LIMIT 1";
        }

        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PaisDAO pdao = new PaisDAO();
                museo = new Museo(rs.getInt("id_museo"), rs.getString("nombre_museo"), pdao.obtenerPaisPorId(rs.getInt("id_pais")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }
        return museo;
    }
}
