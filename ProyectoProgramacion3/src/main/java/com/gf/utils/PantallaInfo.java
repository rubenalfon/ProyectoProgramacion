/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Angel
 */
public class PantallaInfo {

    private static JFrame vista;
    
    public static void setPosicion(JFrame vista){
        PantallaInfo.vista=vista;
    }



    public static void configPantalla(JFrame vista){
        vista.setSize( PantallaInfo.vista.getSize());
        vista.setLocation(PantallaInfo.vista.getLocation().x+(PantallaInfo.vista.getSize().width- vista.getSize().width)/2
                ,PantallaInfo.vista.getLocation().y +(PantallaInfo.vista.getSize().height- vista.getSize().height)/2);
        vista.setVisible(true);
    }
    public static ImageIcon reEscalarImagen(ImageIcon img, int nuevoAncho, int nuevoAlto) {
        if (img == null) {
            return null;
        }

        Image imagenOriginal = img.getImage();

        BufferedImage imagenEscalada = new BufferedImage(nuevoAncho, nuevoAlto, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = imagenEscalada.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.drawImage(imagenOriginal, 0, 0, nuevoAncho, nuevoAlto, null);
        g2d.dispose();


        return new ImageIcon(imagenEscalada);
    }
    public static void setPuntuacionPantalla(JLabel label,int aciertos, int total){
        label.setText(aciertos+"/"+total);
    }
}
