/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controlador;

import com.gf.utils.PantallaInfo;
import com.gf.vista.Vista;
import com.gf.vista.VistaGregorio;
import com.gf.vista.VistaInicial;
import com.gf.vista.VistaMapa;
import com.gf.vista.VistaQuienLoHizo;
import com.gf.vista.vistaVerdaderoFalsoMuseos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Angel
 */
public class ControladorPrincipal implements WindowListener,ActionListener{
    private ControladorMapa mapa;
    private ControladorGregorio gregorio;
    private ControladorVerdaderoFalsoMuseos verdaderoFalso;
    private ControladorVistaQuienLoHizo quienLoHizo;
    private ControladorRecorrido recorrido;
    private VistaInicial vista;
    private int juegoSeleccionado;

    public ControladorPrincipal() {
        setFrame();
    }
    private void setFrame(){
        this.vista= new VistaInicial();
        this.vista.setVisible(true);
        this.vista.setLocationRelativeTo(null);
        this.vista.getjButtonColocaMapa().addActionListener(this);
        this.vista.getjButtonVerdaderoFalsoMuseos().addActionListener(this);
        this.vista.getjButtonQuienLoHizo().addActionListener(this);
        this.vista.getjButtonGregorioFernandez().addActionListener(this);
        this.vista.getjButtonRecorrido().addActionListener(this);
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
        System.out.println(e.getSource());
        if((e.getSource())==vista) return;
        switch (juegoSeleccionado) {
            case 0:
                break;
            case 1:
                panelMensaje(gregorio.getVista(), gregorio.getPuntuacion(), gregorio.getNumPreguntas());
                break;
            case 2:
                panelMensaje(mapa.getVista(), mapa.getPuntuacion(), mapa.getNumPreguntas());
                break;
            case 3:
                panelMensaje(verdaderoFalso.getVista(), verdaderoFalso.getContadorAciertos(), verdaderoFalso.getNumPreguntas());
                break;                
            case 4:
                panelMensaje(quienLoHizo.getVista(), quienLoHizo.getContadorAciertos(), quienLoHizo.getNumPreguntas());
                break;
  
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.vista.setVisible(false);
        switch (((JButton)e.getSource()).getName()) {
            case "recorrido":
                juegoSeleccionado=0;
                System.out.println("uwu");
                recorrido= new ControladorRecorrido();
                this.recorrido.addWindowListener(this);
                break;
            case "gregorio":
                juegoSeleccionado=1;
                gregorio = new ControladorGregorio(new VistaGregorio(), 6);
                gregorio.getVista().addWindowListener(this);
                break;
            case "mapa":
                juegoSeleccionado=2;
                System.out.println("mapa");
                mapa = new ControladorMapa(new VistaMapa(), 6);
                mapa.getVista().addWindowListener(this);
                break;
            case "verdadero":
                juegoSeleccionado=3;
                verdaderoFalso = new ControladorVerdaderoFalsoMuseos(new vistaVerdaderoFalsoMuseos(), 4);
                verdaderoFalso.getVista().addWindowListener(this);
                break;
            case "quien":
                juegoSeleccionado=4;
                quienLoHizo= new ControladorVistaQuienLoHizo(new VistaQuienLoHizo(), 5, 5);
                quienLoHizo.getVista().addWindowListener(this);
                break;
                
        }
        
        System.out.println(((JButton)e.getSource()).getName());
    }
    private void panelMensaje(JFrame vista, int puntuacion, int numPreguntas){
        JOptionPane.showMessageDialog(vista, "¡Felicidades! Has acertado  " + puntuacion + " / " + numPreguntas);
    }
    

}
