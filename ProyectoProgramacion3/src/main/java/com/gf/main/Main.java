/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.main;

import com.gf.dao.ObraDAO;
import com.gf.utils.ConexionBD;
import com.gf.utils.ConvertirArrayListACadena;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alfgonru
 */
public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Hola mundo!");

            ConexionBD conBD = new ConexionBD();

            Connection con = conBD.getConnection();

            ObraDAO odao = new ObraDAO(con);

            System.out.println(odao.obtenerPinturaAleatoria(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,19, 20))).toString());

        } catch (SQLException ex) {
            System.out.println("No se han encontrado");
        } catch (Exception ex) {
            System.out.println("No se han encontrado");
        }
//        ArrayList<Integer> lista = new ArrayList<>();
//        
//        lista.add(2);
//        lista.add(5);
//        lista.add(6);
//        
//
//        System.out.println(ConvertirArrayListCadena.convertir(lista));

    }

}
