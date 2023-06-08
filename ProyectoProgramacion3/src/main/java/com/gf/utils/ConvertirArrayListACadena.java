/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.utils;

import java.util.ArrayList;

/**
 *
 *
 * @author Ruben
 */
public class ConvertirArrayListACadena {

    public static String convertir(ArrayList<Integer> lista) { // Convierte un ArrayList a String con un formato para SQL.
        String cadena = "";

        if (lista != null && !lista.isEmpty()) {
            for (Integer numero : lista) {
                cadena += " ," + String.valueOf(numero);
            }
            cadena = "(" + cadena.substring(2) + ")";
        }
        return cadena;
    }
}
