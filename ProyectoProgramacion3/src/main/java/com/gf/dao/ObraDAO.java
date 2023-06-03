/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.dao;

import com.gf.modelo.Obra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Objetivos:
 * 
 * Recoger una pintura aleatoria
 * Recoger una imagen aleatoria (imagen = escultura)
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
    
    public Obra obtenerObraAleatoria () throws SQLException {
        Obra obra = null;
        
        String sql = "SELECT * FROM obra ORDER BY RAND () LIMIT 1"; // Obtener una obra aleatoria.
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            AutorDAO adao = new AutorDAO(con);
            MuseoDAO mdao = new MuseoDAO(con);
            obra = new Obra(rs.getInt("id_obra"), 
                    rs.getString("nombre_obra"), 
                    rs.getString("nombre_obra"), 
                    rs.getString("disciplina"), 
                    rs.getString("url"), 
                    adao.obtenerAutorPorId(rs.getInt("id_autor")),
                    mdao.obtenerMuseoPorId(rs.getInt("id_museo")));
        }
        
        return obra;
    }
}
