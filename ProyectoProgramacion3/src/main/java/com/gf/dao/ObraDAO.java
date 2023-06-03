/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelo.Autor;
import com.gf.modelo.Obra;
import com.gf.utils.ConvertirArrayListACadena;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Objetivos:
 *
 * Recoger una pintura aleatoria
 *
 * Recoger una escultura aleatoria, una del gregorio y otra de no
 *
 * REcoger una pintura aleatoria que no esté en la lista dada.
 *
 *
 *
 * @author Ruben
 */
public class ObraDAO {

    Connection con;

    public ObraDAO(Connection con) {
        this.con = con;
    }

    public Obra obtenerPinturaAleatoria(ArrayList lista) throws SQLException {
        Obra obra = null;

        String sql = "SELECT * FROM obra where disciplina like 'Pintura' and id_obra not in "
                + ConvertirArrayListACadena.convertir(lista)
                + " ORDER BY RAND () LIMIT 1"; // Obtener una pintura aleatoria.
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            AutorDAO adao = new AutorDAO(con);
            MuseoDAO mdao = new MuseoDAO(con);
            obra = new Obra(rs.getInt("id_obra"),
                    rs.getString("nombre_obra"),
                    rs.getString("nombre_obra"),
                    rs.getString("disciplina"),
                    rs.getString("url_obra"),
                    adao.obtenerAutorPorId(rs.getInt("id_autor")),
                    mdao.obtenerMuseoPorId(rs.getInt("id_museo")));
        }
        return obra;
    }

    public Obra obtenerEsculturaAleatoriaDeAutor(ArrayList lista, Autor autor) throws SQLException {
        Obra obra = null;

        String sql = "SELECT * FROM obra where disciplina like 'Escultura' and id_obra not in "
                + ConvertirArrayListACadena.convertir(lista)
                + "and id_autor = ? ORDER BY RAND () LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, autor.getIdAutor());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            AutorDAO adao = new AutorDAO(con);
            MuseoDAO mdao = new MuseoDAO(con);
            obra = new Obra(rs.getInt("id_obra"),
                    rs.getString("nombre_obra"),
                    rs.getString("nombre_obra"),
                    rs.getString("disciplina"),
                    rs.getString("url_obra"),
                    adao.obtenerAutorPorId(rs.getInt("id_autor")),
                    mdao.obtenerMuseoPorId(rs.getInt("id_museo")));
        }
        return obra;
    }

    public Obra obtenerEsculturaAleatoriaNoDeAutor(ArrayList lista, Autor autor) throws SQLException {
        Obra obra = null;

        String sql = "SELECT * FROM obra where disciplina like 'Escultura' and id_obra not in "
                + ConvertirArrayListACadena.convertir(lista)
                + "and id_autor != ? ORDER BY RAND () LIMIT 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, autor.getIdAutor());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            AutorDAO adao = new AutorDAO(con);
            MuseoDAO mdao = new MuseoDAO(con);
            obra = new Obra(rs.getInt("id_obra"),
                    rs.getString("nombre_obra"),
                    rs.getString("nombre_obra"),
                    rs.getString("disciplina"),
                    rs.getString("url_obra"),
                    adao.obtenerAutorPorId(rs.getInt("id_autor")),
                    mdao.obtenerMuseoPorId(rs.getInt("id_museo")));
        }
        return obra;
    }
}
