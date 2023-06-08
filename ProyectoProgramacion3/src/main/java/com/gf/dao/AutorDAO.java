/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelo.Autor;
import com.gf.utils.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Ruben
 */
public class AutorDAO {

    public Autor obtenerAutorPorId(int idAutor) throws SQLException {
        Autor autor = null;
        String sql = "SELECT * FROM autor WHERE id_autor = ?";

        Connection con = DatabaseManager.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idAutor);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            PaisDAO pdao = new PaisDAO();
            autor = new Autor(idAutor, rs.getString("nombre_autor"), pdao.obtenerPaisPorId(rs.getInt("id_pais")));
        }

        DatabaseManager.closeConnection();
        return autor;
    }

    public Autor obtenerAutorAleatorio(ArrayList lista) throws SQLException {
        Autor autor = null;
        String sql = "SELECT * FROM autor where id_autor not in "
                + ConvertirArrayListACadena.convertir(lista)
                + " ORDER BY RAND () LIMIT 1"; // Obtener una pintura aleatoria.

        Connection con = DatabaseManager.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            PaisDAO pdao = new PaisDAO();
            autor = new Autor(rs.getInt("id_autor"), rs.getString("nombre_autor"), pdao.obtenerPaisPorId(rs.getInt("id_pais")));
        }

        DatabaseManager.closeConnection();

        return autor;
    }
}
