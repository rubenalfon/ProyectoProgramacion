/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controlador;

import com.gf.dao.*;
import com.gf.modelo.Obra;
import com.gf.utils.PantallaInfo;
import com.gf.vista.*;
import java.awt.event.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

/**
 *
 * @author parbalan
 */
public class ControladorGregorio implements ComponentListener, ActionListener {

    private VistaGregorio vista;
    private PantallaDeCarga pantallaCarga;
    private int puntuacion;
    HashMap<Integer, ImageIcon> obrasImg;
    ArrayList<Integer> idsObraGregorio; // Esta lista y la de abajo iran disminuyendo el tamaño a medida que avance el juego para asi 
    ArrayList<Integer> idsObraNoGregorio;//
    private ObraDAO obraDao;

    public ControladorGregorio(VistaGregorio vista, int preguntas) {
        this.vista = vista;
        this.vista.setVisible(false);

        this.obraDao = new ObraDAO();
        this.pantallaCarga = new PantallaDeCarga();

        cargarObras(preguntas);

        PantallaInfo.configPantalla(this.vista);
        PantallaInfo.setPosicion(this.vista);
        this.vista.addComponentListener(this);
        this.vista.getjButton1().addActionListener(this);
        this.vista.getjButton2().addActionListener(this);
        PantallaInfo.setPuntuacionPantalla(this.vista.getPuntuacion(), puntuacion, getNumPreguntas());

        insertarImagenEnBoton();
    }

    private void cargarObras(int preguntas) {
        this.obrasImg = new HashMap<>();
        AutorDAO autorDao = new AutorDAO();
        this.idsObraGregorio = new ArrayList<>();
        this.idsObraNoGregorio = new ArrayList<>();
        for (int i = 0; i < preguntas; i++) {
            try {
                Obra obra = obraDao.obtenerEsculturaAleatoriaDeAutor(new ArrayList<>(obrasImg.keySet()), autorDao.obtenerAutorPorId(1));
                obrasImg.put(obra.getIdObra(), new ImageIcon(new URL(obra.getUrlObra())));
                this.idsObraGregorio.add(obra.getIdObra());
                Obra obra2 = obraDao.obtenerEsculturaAleatoriaNoDeAutor(new ArrayList<>(obrasImg.keySet()), autorDao.obtenerAutorPorId(1));
                obrasImg.put(obra2.getIdObra(), new ImageIcon(new URL(obra2.getUrlObra())));
                this.idsObraNoGregorio.add(obra2.getIdObra());
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                this.vista.dispose();
                this.pantallaCarga.dispose();
                JOptionPane.showMessageDialog(null, "Error a la hora de conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        this.pantallaCarga.dispose();
        this.pantallaCarga = null;
        System.out.println(this.vista);
        System.out.println(obrasImg.toString());
    }

    private boolean insertarImagenEnBoton() {
        if (idsObraGregorio.isEmpty()) {
            return false;
        }
        ArrayList mezcla = new ArrayList();

        mezcla.add(idsObraGregorio.get(0));
        mezcla.add(idsObraNoGregorio.get(0));
        idsObraGregorio.remove(0);
        idsObraNoGregorio.remove(0);
        Collections.shuffle(mezcla);
        this.vista.getjButton1().setIcon(PantallaInfo.reEscalarImagen(obrasImg.get(mezcla.get(0)), this.vista.getjButton1().getSize().width, this.vista.getjButton1().getSize().height));
        this.vista.getjButton2().setIcon(PantallaInfo.reEscalarImagen(obrasImg.get(mezcla.get(1)), this.vista.getjButton1().getSize().width, this.vista.getjButton1().getSize().height));
        this.vista.getjButton1().setName(String.valueOf(mezcla.get(0)));
        this.vista.getjButton2().setName(String.valueOf(mezcla.get(1)));

        System.out.println(idsObraGregorio);
        System.out.println(idsObraNoGregorio);
        return true;
    }

    public VistaGregorio getVista() {
        return vista;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getNumPreguntas() {
        return this.obrasImg.size() / 2;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.vista.getjButton1().setIcon(PantallaInfo.reEscalarImagen((ImageIcon) this.vista.getjButton1().getIcon(), this.vista.getjButton1().getSize().width, this.vista.getjButton1().getSize().height));
        this.vista.getjButton2().setIcon(PantallaInfo.reEscalarImagen((ImageIcon) this.vista.getjButton2().getIcon(), this.vista.getjButton1().getSize().width, this.vista.getjButton1().getSize().height));
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ObraDAO obraDao = new ObraDAO();
        System.out.println(((JButton) e.getSource()).getName());
        try {
            if (obraDao.obtenerObraId(Integer.valueOf(((JButton) e.getSource()).getName())).getAutor().getNombreAutor().equals("Gregorio Fernández")) {
                puntuacion++;
                PantallaInfo.setPuntuacionPantalla(this.vista.getPuntuacion(), puntuacion, getNumPreguntas());
            }
            if (!insertarImagenEnBoton()) {
                this.vista.dispose();

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorGregorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
