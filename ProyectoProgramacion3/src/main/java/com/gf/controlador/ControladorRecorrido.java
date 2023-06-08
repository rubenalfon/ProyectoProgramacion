/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controlador;

import com.gf.dao.PuntuacionDAO;
import com.gf.modelo.Puntuacion;
import com.gf.utils.PantallaInfo;
import com.gf.vista.VistaGregorio;
import com.gf.vista.VistaMapa;
import com.gf.vista.VistaQuienLoHizo;
import com.gf.vista.vistaVerdaderoFalsoMuseos;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
            mostrarClasificacion();
        }
    }

    private void mostrarProgreso(JFrame vista, int puntuacion, int numPreguntas) { // Muestra los aciertos sobre los puntos totales
        this.puntuacion += puntuacion;
        this.numPreguntas += numPreguntas;
        if (puntuacion < numPreguntas / 2) {
            JOptionPane.showMessageDialog(vista, "¡Ánimo! Has acertado por el momento " + puntuacion + " de " + numPreguntas);
        } else {
            JOptionPane.showMessageDialog(vista, "¡Felicidades! Has acertado  " + puntuacion + " de " + numPreguntas);
        }
    }

    private void mostrarClasificacion() {
        try { // Recuperamos de bd las 10 mejores.
            PuntuacionDAO pdao = new PuntuacionDAO();
            ArrayList<Puntuacion> listaMejoresPuntuaciones = pdao.obtenerMejoresPuntuaciones(10);

            String cadena = "<html><table><th><td>Nombre</td><td>Aciertos</td><td>Segundos</td></th>";
            int contador = 1;
            for (Puntuacion p : listaMejoresPuntuaciones) {
                cadena += "<tr><td>" + (String.valueOf(contador) + "º." + "</td>"
                        + "<td>" + p.getNombreUsuario() + "</td>"
                        + "<td>" + String.valueOf(p.getAciertos()) + "</td><td>"
                        + String.valueOf(p.getSegundos()) + "</td></tr>");
                contador++;
            }
            cadena += "<tr><td colspan='4'><h1>Tu puntuación</h1></td></tr>";
            cadena += "<tr><td colspan='2'>Tú</td><td>" + this.puntuacion + "</td><td>" + Math.round(seg) + "</td></tr>";
            cadena += "</table>";

            if (this.puntuacion >= listaMejoresPuntuaciones.get(listaMejoresPuntuaciones.size() - 1).getAciertos()) {
                cadena += "<h1>Introduce tu nombre: </h1></html>";
                String nombre = JOptionPane.showInputDialog(this, cadena, "Clasificación", JOptionPane.PLAIN_MESSAGE);
                guardarPuntuacion(nombre);
            } else {
                cadena += "</html>";
                JOptionPane.showMessageDialog(this, cadena, "Clasificación", JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(this, "Puntuación guardada correctamente", "Guardado", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorRecorrido.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarPuntuacion(String nombre) {
        try {
            PuntuacionDAO pdao = new PuntuacionDAO();
            pdao.guardarPuntuacion(new Puntuacion(0, nombre, this.puntuacion, this.numPreguntas, Math.round(this.seg)));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar la puntuación", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
