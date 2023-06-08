/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controlador;

import com.gf.dao.PuntuacionDAO;
import com.gf.modelo.Puntuacion;
import com.gf.utils.PantallaInfo;
import com.gf.vista.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Angel
 */
public class ControladorRecorrido extends JFrame implements WindowListener {

    private ControladorGregorio gregorio;
    private ControladorMapa mapa;
    private ControladorVerdaderoFalsoMuseos verdaderoFalso;
    private ControladorVistaQuienLoHizo quien;
    private int puntuacion;
    private int numPreguntas;
    private long seg;
    private LocalDateTime tiempoInicio;

    public ControladorRecorrido() {
        this.setVisible(true);
        this.tiempoInicio = LocalDateTime.now();
        gregorio = new ControladorGregorio(new VistaGregorio(), 5);
        gregorio.getVista().addWindowListener(this);
        this.puntuacion = 0;
        this.numPreguntas = 0;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public int getNumPreguntas() {
        return numPreguntas;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public long getSeg() {
        return seg;
    }

    @Override
    public void windowClosed(WindowEvent e) { // Cuando se cierre una ventana de un juego, se abrirá la del siguiente
        if (e.getSource() instanceof VistaGregorio) {
            mapa = new ControladorMapa(new VistaMapa(), 5);
            mapa.getVista().addWindowListener(this);
            mostrarProgreso(mapa.getVista(), gregorio.getPuntuacion(), gregorio.getNumPreguntas());

        } else if (e.getSource() instanceof VistaMapa) {
            verdaderoFalso = new ControladorVerdaderoFalsoMuseos(new vistaVerdaderoFalsoMuseos(), 5);
            verdaderoFalso.getVista().addWindowListener(this);
            mostrarProgreso(verdaderoFalso.getVista(), mapa.getPuntuacion(), mapa.getNumPreguntas());
            System.out.println(mapa.getNumPreguntas());

        } else if (e.getSource() instanceof vistaVerdaderoFalsoMuseos) {
            quien = new ControladorVistaQuienLoHizo(new VistaQuienLoHizo(), 5, 5);
            quien.getVista().addWindowListener(this);
            mostrarProgreso(quien.getVista(), verdaderoFalso.getContadorAciertos(), verdaderoFalso.getNumPreguntas());

        } else if (e.getSource() instanceof VistaQuienLoHizo) {
            this.seg = Duration.between(tiempoInicio, LocalDateTime.now()).getSeconds();
            this.puntuacion += quien.getContadorAciertos();
            this.numPreguntas += quien.getNumPreguntas();
            PantallaInfo.configPantalla(this);
            PantallaInfo.setPosicion(this);
            this.setVisible(rootPaneCheckingEnabled);
            this.dispose();
            gestionarPuntuaciones();
        }
    }

    private void mostrarProgreso(JFrame vista, int puntuacion, int numPreguntas) { // Muestra los aciertos sobre los puntos totales
        this.puntuacion += puntuacion;
        this.numPreguntas += numPreguntas;
        JOptionPane.showMessageDialog(vista, "Por ahora llevas acertadas " + this.puntuacion + "/" + this.numPreguntas);
    }

    private void gestionarPuntuaciones() {
        mostrarProgresoFinal(this, puntuacion, numPreguntas, seg);

        try { // Recuperamos de bd las 10 mejores y comprobamos si el usuario lo ha hecho mejor que alguno de ellos.
            PuntuacionDAO pdao = new PuntuacionDAO();
            ArrayList<Puntuacion> listaMejoresPuntuaciones = pdao.obtenerMejoresPuntuaciones(10);
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorRecorrido.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void mostrarProgresoFinal(JFrame vista, int puntuacion, int numPreguntas, long seg) { // Muestra los aciertos sobre los puntos totales, y el tiempo desde el inicio
        JOptionPane.showMessageDialog(vista, "Total " + puntuacion + "/" + numPreguntas, "Tiempo Total: " + Math.round(seg) + " Seg", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
