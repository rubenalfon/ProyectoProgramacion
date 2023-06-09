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
import java.util.ArrayList;
import java.util.logging.*;
import javax.swing.*;

/**
 *
 * @author Angel
 */
public class ControladorPrincipal implements WindowListener, ActionListener {

    private ControladorMapa mapa;
    private ControladorGregorio gregorio;
    private ControladorVerdaderoFalsoMuseos verdaderoFalso;
    private ControladorVistaQuienLoHizo quienLoHizo;
    private ControladorRecorrido recorrido;
    private VistaInicial vista;
    private int juegoSeleccionado;//num de juego seleccionado
    private boolean inJuego;//Controla si estoy dentro de un juego o no

    public ControladorPrincipal() {
        setFrame();
    }

    private void setFrame() {
        this.vista = new VistaInicial();
        this.vista.setVisible(true);
        this.vista.setLocationRelativeTo(null);
        this.vista.getjButtonColocaMapa().addActionListener(this);
        this.vista.getjButtonVerdaderoFalsoMuseos().addActionListener(this);
        this.vista.getjButtonQuienLoHizo().addActionListener(this);
        this.vista.getjButtonGregorioFernandez().addActionListener(this);
        this.vista.getjButtonRecorrido().addActionListener(this);
        this.vista.getjButtonPuntuaciones().addActionListener(this);
        this.vista.getjButtonSobre().addActionListener(this);
        PantallaInfo.setPosicion(vista);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        this.vista.setVisible(true);
        PantallaInfo.configPantalla(vista);
        PantallaInfo.setPosicion(vista);

        if (inJuego) {//si esta en un juego y cierra la pestaña pues muestra el resultado
            switch (juegoSeleccionado) {
                case 0:
                    break;
                case 1:
                    panelMensaje(gregorio.getPuntuacion(), gregorio.getNumPreguntas());
                    break;
                case 2:
                    panelMensaje(mapa.getPuntuacion(), mapa.getNumPreguntas());
                    break;
                case 3:
                    panelMensaje(verdaderoFalso.getContadorAciertos(), verdaderoFalso.getNumPreguntas());
                    break;
                case 4:
                    panelMensaje(quienLoHizo.getContadorAciertos(), quienLoHizo.getNumPreguntas());
                    break;
            }
        } else {
            this.inJuego = false;
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (((JButton) e.getSource()).getName()) { // En función del boton que escoja este te creara un controlador 
            case "recorrido":
                this.vista.setVisible(false);
                juegoSeleccionado = 0;
                recorrido = new ControladorRecorrido();
                this.recorrido.addWindowListener(this);
                break;

            case "gregorio":
                this.vista.setVisible(false);
                juegoSeleccionado = 1;
                gregorio = new ControladorGregorio(new VistaGregorio(), 5);
                gregorio.getVista().addWindowListener(this);
                break;

            case "mapa":
                this.vista.setVisible(false);
                juegoSeleccionado = 2;
                mapa = new ControladorMapa(new VistaMapa(), 6);
                mapa.getVista().addWindowListener(this);
                break;

            case "verdadero":
                this.vista.setVisible(false);
                this.vista.setVisible(false);
                juegoSeleccionado = 3;
                verdaderoFalso = new ControladorVerdaderoFalsoMuseos(new vistaVerdaderoFalsoMuseos(), 4);
                verdaderoFalso.getVista().addWindowListener(this);
                break;

            case "quien":
                this.vista.setVisible(false);
                this.vista.setVisible(false);
                juegoSeleccionado = 4;
                quienLoHizo = new ControladorVistaQuienLoHizo(new VistaQuienLoHizo(), 5, 5);
                quienLoHizo.getVista().addWindowListener(this);
                break;

            case "puntuaciones":
                mostrarClasificacion();
                break;
            case "sobre":
                String html = "<html>"
                        + "<head><title>Acerca de nuestra aplicación</title><style>.authors {font-weight: bold;}</style></head>"
                        + "<body><h1>Acerca de nuestra aplicación</h1>"
                        + "<p>Bienvenido a nuestra aplicación dedicada a la celebración del Día Internacional de los Museos.<br/>Estamos encantados de presentarte una experiencia interactiva y educativa que te sumergirá en el fascinante mundo del arte y los museos.</p>"
                        + "<h2>Juegos emocionantes:</h2>"
                        + "<h3>1. ¿Quién lo creó?</h3><p>Te mostraremos imágenes de diferentes obras de arte y tendrás que adivinar quién es su autor.<br/>Pon a prueba tus conocimientos artísticos.</p>"
                        + "<h3>2. Verdadero/Falso de Museos</h3><p>Te presentaremos una lista de nombres de museos y deberás determinar si existen o no en el mundo real.<br/>¡Demuestra tu conocimiento sobre museos de todo el mundo!</p>"
                        + "<h3>3. ¿Es de Gregorio Fernández?</h3><p>Te mostraremos dos imágenes de esculturas y deberás adivinar si una de ellas es obra de Gregorio Fernández, un reconocido escultor.<br/>¡Ponte a prueba y descubre su estilo artístico!</p>"
                        + "<h3>4. Coloca en el mapa</h3><p>Te desafiamos a colocar diferentes obras de arte en un mapa.<br/>Pon a prueba tu geografía y conocimientos sobre las obras de arte más famosas del mundo.</p>"
                        + "<h2>Con un modo recorrido</h2>"
                        + "<p>En el que recorrerás todas las pruebas y si lo haces muy bien, ¡Entrarás en el podio!</p>"
                        + "<h2>Autores:</h2><p class=\"authors\">Ángel Paredes Ballesteros y Rubén Alfonso Gonzalo</p>"
                        + "<h2>¡Disfruta del Día Internacional de los Museos con nuestra aplicación!</h2></body></html>";
                JOptionPane.showMessageDialog(vista, html, "Sobre", JOptionPane.PLAIN_MESSAGE);
        }
        this.inJuego = true;
    }

    private void panelMensaje(int puntuacion, int numPreguntas) {
        if ((double) puntuacion < numPreguntas * 0.7) {
            JOptionPane.showMessageDialog(vista, "¡Ánimos! Has acertado  " + puntuacion + " / " + numPreguntas);
        } else {
            JOptionPane.showMessageDialog(vista, "¡Felicidades! Has acertado  " + puntuacion + " / " + numPreguntas);
        }
    }

    private void mostrarClasificacion() {
        try { // Recuperamos de bd las 10 mejores.
            PuntuacionDAO pdao = new PuntuacionDAO();
            ArrayList<Puntuacion> listaMejoresPuntuaciones = pdao.obtenerMejoresPuntuaciones(10);

            if (listaMejoresPuntuaciones.isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "No hay resultados... Pero puedes ser el primero!!", "Clasificación", JOptionPane.PLAIN_MESSAGE);
            } else {
                String cadena = "<html><table><th><td>Nombre</td><td>Aciertos</td><td>Segundos</td></th>";
                int contador = 1;
                for (Puntuacion p : listaMejoresPuntuaciones) {
                    cadena += "<tr><td>" + (String.valueOf(contador) + "º." + "</td>"
                            + "<td>" + p.getNombreUsuario() + "</td>"
                            + "<td>" + String.valueOf(p.getAciertos()) + "</td><td>"
                            + String.valueOf(p.getSegundos()) + "</td></tr>");
                    contador++;
                }
                cadena += "</table></html>";

                JOptionPane.showMessageDialog(this.vista, cadena, "Clasificación", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorRecorrido.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
