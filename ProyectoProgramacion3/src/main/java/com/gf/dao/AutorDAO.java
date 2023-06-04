/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelo.Autor;

import com.gf.utils.ConvertirArrayListACadena;

import com.gf.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Ruben
 */
public class AutorDAO {


    public Autor obtenerAutorPorId(int idAutor) {

        Autor autor = null;
        String sql = "SELECT * FROM autor WHERE id_autor = ?";
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(sql);
            ps.setInt(1, idAutor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PaisDAO pdao = new PaisDAO();
                autor = new Autor(idAutor, rs.getString("nombre_autor"), rs.getInt("id_pais"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }
        return autor;
        
    }

    public Autor obtenerAutorAleatorio(ArrayList lista) throws SQLException {
        Autor autor = null;

        String sql = "SELECT * FROM autor where id_autor not in "
                + ConvertirArrayListACadena.convertir(lista)
                + " ORDER BY RAND () LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            PaisDAO pdao = new PaisDAO(con);
            autor = new Autor(rs.getInt("id_autor"), rs.getString("nombre_autor"), pdao.obtenerPaisPorId(rs.getInt("id_pais")));
        }
        return autor;
    }
}
