/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controlador;

import com.gf.dao.ObraDAO;
import com.gf.modelo.Obra;
import com.gf.vista.VistaQuienLoHizo;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Ruben
 */
public class ControladorVistaQuienLoHizo implements MouseListener {

    private final VistaQuienLoHizo vista;
    private Connection con = null;

    private int indiceObraActual;
    private ArrayList<Obra> listaObras;

    public ControladorVistaQuienLoHizo(VistaQuienLoHizo vista, int numeroObras) {
        this.vista = vista;

//        vista.getjPanelImagen().setBackground(Color.red);
//        ImageIcon imagen = new ImageIcon("./src/files/Fondo.png");
//        Image imagenEscalada = imagen.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
//        vista.getjLabelImagen().setIcon(new ImageIcon(imagenEscalada));
        URL url;
//        try {
////            url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/The_Legend_of_Zelda_Ocarina_of_Time.svg/1920px-The_Legend_of_Zelda_Ocarina_of_Time.svg.png");
////            BufferedImage img = ImageIO.read(url);
////            vista.getjLabelImagen().setIcon(new ImageIcon(img));
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(ControladorVistaQuienLoHizo.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ControladorVistaQuienLoHizo.class.getName()).log(Level.SEVERE, null, ex);
//        }

        ConexionBD cbd = new ConexionBD();
        try {
            this.con = cbd.getConnection(); // Cambiar -----------------------------------
        } catch (SQLException ex) {
            Logger.getLogger(ControladorVistaQuienLoHizo.class.getName()).log(Level.SEVERE, null, ex);
        }
        iniciar(numeroObras);
        siguienteImagen();

        for (Obra obra : listaObras) {
            System.out.println(obra.toString());
        }
    }

    private void iniciar(int numeroObras) {
        ObraDAO odao = new ObraDAO(con);
        this.listaObras = new ArrayList<>();
        ArrayList<Integer> listaIdUsados = new ArrayList<>();
        Obra obra;

        for (int i = 0; i < numeroObras; i++) {
            try {
                obra = odao.obtenerObraAleatoria(listaIdUsados);
                this.listaObras.add(obra);
                listaIdUsados.add(obra.getIdObra());

            } catch (SQLException ex) {
                Logger.getLogger(ControladorVistaQuienLoHizo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void siguienteImagen() {
        try {
            URL url;
            System.err.println(this.listaObras.get(this.indiceObraActual).getUrlObra());
            url = new URL(this.listaObras.get(this.indiceObraActual).getUrlObra());
            BufferedImage img = ImageIO.read(url);
            vista.getjLabelImagen().setIcon(new ImageIcon(img));

            this.indiceObraActual++;
        } catch (MalformedURLException ex) {
            Logger.getLogger(ControladorVistaQuienLoHizo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControladorVistaQuienLoHizo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
System.out.println("2");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("a");
        siguienteImagen();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
