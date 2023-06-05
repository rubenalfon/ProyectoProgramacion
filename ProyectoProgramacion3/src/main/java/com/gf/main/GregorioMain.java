/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.main;

import com.gf.controlador.ControladorGregorio;
import com.gf.vista.VistaGregorio;

/**
 *
 * @author parbalan
 */
public class GregorioMain {
    public static void main(String[] args) {
            ControladorGregorio contro= new ControladorGregorio(new VistaGregorio(), 5);

    }
}
