/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.main;

import com.gf.dao.ObraDAO;
import com.gf.utils.ConexionBD;
import com.gf.utils.ConvertirArrayListACadena;
import com.gf.utils.DatabaseManager;
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
            System.out.println("Hola mundo!");


            ObraDAO odao = new ObraDAO();
            ArrayList<Integer> lista = new ArrayList<>();
        
            lista.add(2);
            lista.add(5);
            lista.add(6);
            System.out.println(ConvertirArrayListACadena.convertir(lista));
            System.out.println(odao.obtenerObraAleatoria(lista));
        

//        
//
//        System.out.println(ConvertirArrayListCadena.convertir(lista));

    }

}
