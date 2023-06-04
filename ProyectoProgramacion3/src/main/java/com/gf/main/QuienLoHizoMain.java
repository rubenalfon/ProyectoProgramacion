/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.main;

import com.gf.controlador.ControladorVistaQuienLoHizo;
import com.gf.vista.VistaQuienLoHizo;

/**
 *
 * @author Ruben
 */
public class QuienLoHizoMain {
    public static void main(String[] args) {
          ControladorVistaQuienLoHizo cvqlh = new ControladorVistaQuienLoHizo(new VistaQuienLoHizo(), 10,1);
    }
   
}
