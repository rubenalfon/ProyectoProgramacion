/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controlador;

import com.gf.dao.*;
import com.gf.modelo.Obra;
import com.gf.utils.PantallaInfo;
import com.gf.vista.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.net.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author parbalan
 */
public class ControladorGregorio implements ComponentListener, ActionListener {

    private VistaGregorio vista;//Vista del juego
    private int puntuacion;//Puntuacion totla
    private HashMap<Integer, ImageIcon> obrasImg;
    private ArrayList<Integer> idsObraGregorio; // Esta lista y la de abajo iran disminuyendo el tamaño a medida que avance el juego para asi mantener un control de las obras que se van mostrando
    private ArrayList<Integer> idsObraNoGregorio;//
    private ObraDAO obraDao;
    private PantallaDeCarga pantalla;
    private boolean fallado;

    public ControladorGregorio(VistaGregorio vista, int preguntas) {
        this.vista = vista;
        this.vista.setVisible(false);
        this.pantalla = new PantallaDeCarga();
        this.obraDao = new ObraDAO();

        cargarObras(preguntas);

        PantallaInfo.configPantalla(this.vista);
        PantallaInfo.setPosicion(this.vista);
        this.vista.addComponentListener(this);
        this.vista.getjButton1().addActionListener(this);
        this.vista.getjButton2().addActionListener(this);
        PantallaInfo.setPuntuacionPantalla(this.vista.getPuntuacion(), puntuacion, getNumPreguntas());

        insertarImagenEnBoton();
    }

    private void cargarObras(int preguntas) {//Cargo todas las imagenes requeridas en un hasmap
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
                this.pantalla.dispose();
                this.vista.dispose();
                JOptionPane.showMessageDialog(null, "Error a la hora de conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        this.pantalla.dispose();
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
        this.vista.getjButton1().setBorder(null);
        this.vista.getjButton2().setBorder(null);
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
        try {//Si es del gregorio aumenta puntuacion
            if (obraDao.obtenerObraId(Integer.valueOf(((JButton) e.getSource()).getName())).getAutor().getNombreAutor().equals("Gregorio Fernández")) {
                if(this.fallado){
                    this.fallado=false;
                     this.vista.getjButton1().setBorder(null);
                     this.vista.getjButton2().setBorder(null);
                }else{
                    puntuacion++;
                    PantallaInfo.setPuntuacionPantalla(this.vista.getPuntuacion(), puntuacion, getNumPreguntas());
                }
                if (!insertarImagenEnBoton()) {//si el metodo de botones devuelve falso, es que ha habido una execepcion ya que se quedo 
                    this.vista.dispose();

                }
                
            }else{
                
                ((JButton) e.getSource()).setBorder(new LineBorder(Color.RED,10));
                if(e.getSource()==this.vista.getjButton1()) this.vista.getjButton2().setBorder(new LineBorder(Color.GREEN,10));
                else  this.vista.getjButton1().setBorder(new LineBorder(Color.GREEN,10));
                this.fallado=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorGregorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
