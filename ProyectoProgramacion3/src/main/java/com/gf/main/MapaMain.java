/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.main;

import com.gf.controlador.ControladorMapa;
import com.gf.vista.VistaMapa;

/**
 *
 * @author parbalan
 */
public class MapaMain {
    public static void main(String[] args) {
        ControladorMapa  controler= new ControladorMapa(new VistaMapa(),6);
    }
}
