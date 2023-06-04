/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controlador;

import com.gf.dao.*;
import com.gf.modelo.*;
import com.gf.vista.VistaQuienLoHizo;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Ruben
 */
public class ControladorVistaQuienLoHizo implements MouseListener, ActionListener {

    private final VistaQuienLoHizo vista;
    private Connection con = null;
    private ArrayList<JButton> listaBotones;

    private int indiceObraActual;
    private int numeroBotones;
    private ArrayList<Obra> listaObras;
    private ArrayList<Autor> listaAutores;

    private ObraDAO odao;
    private AutorDAO adao;

    public ControladorVistaQuienLoHizo(VistaQuienLoHizo vista, int numeroObras, int numeroBotones) {
        this.vista = vista;
        this.odao = new ObraDAO();
        this.adao = new AutorDAO();
        this.numeroBotones = numeroBotones;

        iniciar(numeroObras);
        siguienteImagen();

        for (Obra obra : listaObras) {
            System.out.println("  " + obra.toString());
        }
        
        this.vista.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                try {
                    ponerImagen();
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ControladorVistaQuienLoHizo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private void iniciar(int numeroObras) {
        this.listaObras = new ArrayList<>();
        this.listaBotones = new ArrayList<>();

        ArrayList<Integer> listaIdUsados = new ArrayList<>();

        for (int i = 0; i < this.numeroBotones; i++) {
            JButton boton = new JButton(String.valueOf(i + 1));
            boton.setPreferredSize(new Dimension(100, 40));
//            boton.setSize(new Dimension(100, 40));
//            boton.setMinimumSize(new Dimension(100, 40));
//            boton.setMaximumSize(new Dimension(100, 40));
            boton.addActionListener(this);
            vista.getjPanelBotones().add(boton);
            this.listaBotones.add(boton);
        }

        for (int i = 0; i < numeroObras; i++) {
            Obra obra = odao.obtenerObraAleatoria(listaIdUsados);
            this.listaObras.add(obra);
            listaIdUsados.add(obra.getIdObra());
        }
    }

    private void siguienteImagen() {
        try {
            ponerImagen();
            this.listaAutores = null;
            recogerAutores(this.numeroBotones);
            System.out.println(this.listaAutores.size());
            ponerBotones();

        } catch (MalformedURLException ex) {
            Logger.getLogger(ControladorVistaQuienLoHizo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ponerImagen() throws MalformedURLException {
        try {
            Dimension d = new Dimension(vista.getjLabelImagen().getSize());
            URL url = new URL(this.listaObras.get(this.indiceObraActual).getUrlObra());

            this.vista.getjLabelImagen().setIcon(new ImageIcon(imagenRescalada(url, d)));

        } catch (MalformedURLException ex) {
            Logger.getLogger(ControladorVistaQuienLoHizo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Image imagenRescalada(URL url, Dimension dimension) {
        ImageIcon imagen = new ImageIcon(url);

        double escala1 = dimension.getWidth() / imagen.getIconWidth();
        double escala2 = dimension.getHeight() / imagen.getIconHeight();
        double escala = Math.min(escala1, escala2);

        Image imagenEscalada = imagen.getImage().getScaledInstance((int) (imagen.getIconWidth() * escala), (int) (imagen.getIconHeight() * escala), Image.SCALE_DEFAULT);
        return imagenEscalada;
    }

    private void ponerBotones() {
        for (int i = 0; i < listaBotones.size(); i++) {
            listaBotones.get(i).setText(this.listaAutores.get(i).getNombreAutor());
        }
    }

    private void recogerAutores(int numAutores) {
        this.listaAutores = new ArrayList<>();
        ArrayList<Integer> listaNoUsar = new ArrayList<>(Arrays.asList(this.listaObras.get(this.indiceObraActual).getAutor().getIdAutor()));
        listaAutores.add(this.listaObras.get(this.indiceObraActual).getAutor());
        for (int i = 1; i < numAutores; i++) {
            Autor autor = this.adao.obtenerAutorAleatorio(listaNoUsar);

            listaNoUsar.add(autor.getIdAutor());
            listaAutores.add(autor);
        }
        Collections.shuffle(listaAutores);
    }

    //
    //
    //
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("    _" + this.listaObras.get(this.indiceObraActual).getNombreObra());
        if (((JButton) e.getSource()).getText().equals(this.listaObras.get(this.indiceObraActual).getAutor().getNombreAutor())) {
            this.indiceObraActual++;
            siguienteImagen();

        } else {

            System.out.println(((JButton) e.getSource()).getText() + " -+ " + (this.listaObras.get(this.indiceObraActual).getAutor().getNombreAutor()));
        }
    }
}
