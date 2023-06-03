/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelo.Autor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Ruben
 */
public class AutorDAO {

    private Connection con;

    public AutorDAO(Connection con) {
        this.con = con;
    }

    public Autor obtenerAutorPorId(int idAutor) throws SQLException {
        Autor autor = null;

        String sql = "SELECT * FROM autor WHERE id_autor = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idAutor);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            PaisDAO pdao = new PaisDAO(con);
            autor = new Autor(idAutor, rs.getString("nombre_autor"), pdao.obtenerPaisPorId(rs.getInt("id_pais")));
        }
        return autor;
    }
}
