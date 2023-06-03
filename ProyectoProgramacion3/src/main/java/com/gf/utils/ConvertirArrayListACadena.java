/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.utils;

import java.util.ArrayList;

/**
 * Convierte un ArrayList a String con un formato para SQL.
 *
 * @author Ruben
 */
public class ConvertirArrayListACadena {

    public static String convertir(ArrayList<Integer> lista) {
        String cadena = "";
        for (Integer numero : lista) {
            cadena += " ," + String.valueOf(numero);
        }
        
        cadena = "(" + cadena.substring(2) + ")";
        return cadena;
    }

}
