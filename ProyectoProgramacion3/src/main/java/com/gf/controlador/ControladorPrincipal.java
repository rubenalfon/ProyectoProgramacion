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
import java.util.logging.Level;
import java.util.logging.Logger;
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
                String html = """
                              <html>
                              <html>
                              <head>
                                <meta charset="UTF-8">
                                <title>Aplicaci\u00f3n de Museos</title>
                              </head>
                              <body>
                                <h1>Aplicaci\u00f3n de Museos</h1>
                                <h2>D\u00eda Internacional de los Museos</h2>
                                <p>En conmemoraci\u00f3n del "D\u00eda Internacional de los Museos" que se celebra el 18 de mayo, nuestro ciclo DAM quiere aportar su granito de arena y apoyar al ciclo de Turismo en esta celebraci\u00f3n.</p>
                                
                                <h3>Juegos</h3>
                                <ol>
                                  <li>
                                    <h4>\u00bfQui\u00e9n lo hizo?</h4>
                                    <p>A partir de unas im\u00e1genes de obras de arte gen\u00e9ricas, hay que adivinar su autor.</p>
                                  </li>
                                  <li>
                                    <h4>Verdadero/Falso de Museos</h4>
                                    <p>Se mostrar\u00e1 una lista de nombres de museos y el usuario tiene que decir si existen o no.</p>
                                  </li>
                                  <li>
                                    <h4>Gregorio Fern\u00e1ndez</h4>
                                    <p>El usuario tiene que adivinar cu\u00e1l de dos im\u00e1genes de esculturas planteadas corresponde a la autor\u00eda de Gregorio Fern\u00e1ndez.</p>
                                  </li>
                                  <li>
                                    <h4>Coloca en el mapa</h4>
                                    <p>El usuario tiene que colocar en un mapa europeo obras de arte.</p>
                                  </li>
                                </ol>
                                
                                <h3>Descripci\u00f3n de la Aplicaci\u00f3n</h3>
                                <p>Se plantea realizar una aplicaci\u00f3n Java que recoja 4 de los juegos que formaban la gymkana organizada por los chicos de turismo, y en los que participaron todos los alumnos del centro.</p>
                                
                                <h3>Fecha de Celebraci\u00f3n</h3>
                                <p>El "D\u00eda Internacional de los Museos" se celebra cada a\u00f1o el 18 de mayo.</p>
                              </body>
                              </html>""";
                JOptionPane.showMessageDialog(vista, html);
        }
        this.inJuego = true;
    }

    private void panelMensaje(int puntuacion, int numPreguntas) {
        System.out.println(puntuacion + "-" + numPreguntas / 2);
        if (puntuacion < numPreguntas / 2) {
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
