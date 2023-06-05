/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelo.*;
import com.gf.utils.*;
import java.sql.*;
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

    public Obra obtenerObraAleatoria(ArrayList<Integer> lista) {
        Obra obra = null;
        String sql;
        if (lista == null || lista.isEmpty()) {
            sql = "SELECT * FROM obra ORDER BY RAND () LIMIT 1"; // Obtener una pintura aleatoria.
        } else {
            sql = "SELECT * FROM obra where id_obra not in "
                    + ConvertirArrayListACadena.convertir(lista)
                    + " ORDER BY RAND () LIMIT 1";
        }
        
        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idObra = rs.getInt("id_obra");
                String nombreObra = rs.getString("nombre_obra");
                String descripcionObra = rs.getString("descripcion_obra");
                String disciplina = rs.getString("disciplina");
                String urlObra = rs.getString("url_obra");
                int idAutor = rs.getInt("id_autor");
                int idMuseo = rs.getInt("id_museo");

                AutorDAO adao = new AutorDAO();
                MuseoDAO mdao = new MuseoDAO();
                Autor autor = adao.obtenerAutorPorId(idAutor);
                Museo museo = mdao.obtenerMuseoPorId(idMuseo);

                obra = new Obra(idObra, nombreObra, descripcionObra, disciplina, urlObra, autor, museo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }
        return obra;
    }

    public Obra obtenerPinturaAleatoria(ArrayList<Integer> lista) {
        Obra obra = null;
        String sql = "SELECT * FROM obra where id_obra not in "
                + ConvertirArrayListACadena.convertir(lista)
                + " ORDER BY RAND () LIMIT 1";

        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idObra = rs.getInt("id_obra");
                String nombreObra = rs.getString("nombre_obra");
                String descripcionObra = rs.getString("descripcion_obra");
                String disciplina = rs.getString("disciplina");
                String urlObra = rs.getString("url_obra");
                int idAutor = rs.getInt("id_autor");
                int idMuseo = rs.getInt("id_museo");

                AutorDAO adao = new AutorDAO();
                MuseoDAO mdao = new MuseoDAO();
                Autor autor = adao.obtenerAutorPorId(idAutor);
                Museo museo = mdao.obtenerMuseoPorId(idMuseo);

                obra = new Obra(idObra, nombreObra, descripcionObra, disciplina, urlObra, autor, museo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }
        return obra;
    }

    public Obra obtenerEsculturaAleatoriaDeAutor(ArrayList<Integer> lista, Autor autorDado) {
        Obra obra = null;
        String sql = "SELECT * FROM obra where disciplina like 'Escultura' and id_obra not in "
                + ConvertirArrayListACadena.convertir(lista)
                + "and id_autor = ? ORDER BY RAND () LIMIT 1";

        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idObra = rs.getInt("id_obra");
                String nombreObra = rs.getString("nombre_obra");
                String descripcionObra = rs.getString("descripcion_obra");
                String disciplina = rs.getString("disciplina");
                String urlObra = rs.getString("url_obra");
                int idAutor = rs.getInt("id_autor");
                int idMuseo = rs.getInt("id_museo");

                AutorDAO adao = new AutorDAO();
                MuseoDAO mdao = new MuseoDAO();
                Autor autor = adao.obtenerAutorPorId(idAutor);
                Museo museo = mdao.obtenerMuseoPorId(idMuseo);

                obra = new Obra(idObra, nombreObra, descripcionObra, disciplina, urlObra, autor, museo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }
        return obra;
    }

    public Obra obtenerEsculturaAleatoriaNoDeAutor(ArrayList<Integer> lista, Autor autorDado) {
        Obra obra = null;
        String sql = "SELECT * FROM obra where disciplina like 'Escultura' and id_obra not in "
                + ConvertirArrayListACadena.convertir(lista)
                + "and id_autor != ? ORDER BY RAND () LIMIT 1";

        try {
            Connection con = DatabaseManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idObra = rs.getInt("id_obra");
                String nombreObra = rs.getString("nombre_obra");
                String descripcionObra = rs.getString("descripcion_obra");
                String disciplina = rs.getString("disciplina");
                String urlObra = rs.getString("url_obra");
                int idAutor = rs.getInt("id_autor");
                int idMuseo = rs.getInt("id_museo");

                AutorDAO adao = new AutorDAO();
                MuseoDAO mdao = new MuseoDAO();
                Autor autor = adao.obtenerAutorPorId(idAutor);
                Museo museo = mdao.obtenerMuseoPorId(idMuseo);

                obra = new Obra(idObra, nombreObra, descripcionObra, disciplina, urlObra, autor, museo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }
        return obra;
    }
}
