/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controlador;

import com.gf.utils.PantallaInfo;
import com.gf.vista.VistaGregorio;
import com.gf.vista.VistaMapa;
import com.gf.vista.VistaQuienLoHizo;
import com.gf.vista.vistaVerdaderoFalsoMuseos;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Angel
 */
public class ControladorRecorrido extends JFrame implements WindowListener{
    private ControladorGregorio gregorio;
    private ControladorMapa mapa;
    private ControladorVerdaderoFalsoMuseos verdaderoFalso;
    private ControladorVistaQuienLoHizo quien;
    private int puntuacion;
    private int numPreguntas;
    private long seg;
    private LocalDateTime tiempoInicio;
    
    public ControladorRecorrido() {
        this.tiempoInicio=LocalDateTime.now();
        gregorio = new ControladorGregorio(new VistaGregorio(), 5);
        gregorio.getVista().addWindowListener(this);
        this.puntuacion=0;
        this.numPreguntas=0;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }
    @Override
    public void windowClosed(WindowEvent e) {//Si se cierra una ventana en funcion de cual es salta a la siguiente
        if(e.getSource()instanceof VistaGregorio){
            mapa = new ControladorMapa(new VistaMapa(), 5);
            mapa.getVista().addWindowListener(this);

            mostrarProgreso(mapa.getVista(),gregorio.getPuntuacion(), gregorio.getNumPreguntas());
        }else if(e.getSource()instanceof VistaMapa){
            verdaderoFalso= new ControladorVerdaderoFalsoMuseos(new vistaVerdaderoFalsoMuseos(), 5);
            verdaderoFalso.getVista().addWindowListener(this);
            mostrarProgreso(verdaderoFalso.getVista(),mapa.getPuntuacion(), mapa.getNumPreguntas());
            System.out.println(mapa.getNumPreguntas());
        }else if(e.getSource()instanceof vistaVerdaderoFalsoMuseos){
            quien= new ControladorVistaQuienLoHizo(new VistaQuienLoHizo(), 5, 5);
            quien.getVista().addWindowListener(this);
            mostrarProgreso(quien.getVista(),verdaderoFalso.getContadorAciertos(), verdaderoFalso.getNumPreguntas());
        }else if(e.getSource()instanceof VistaQuienLoHizo){
            this.seg =Duration.between(tiempoInicio, LocalDateTime.now()).getSeconds();
            this.puntuacion+=quien.getContadorAciertos();
            this.numPreguntas+=quien.getNumPreguntas();
            PantallaInfo.configPantalla(this);
            PantallaInfo.setPosicion(this);
            this.setVisible(rootPaneCheckingEnabled);
            this.dispose();
            mostrarProgreso(this, puntuacion, numPreguntas,seg);
        }
    }

    public int getNumPreguntas() {
        return numPreguntas;
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

    public int getPuntuacion() {
        return puntuacion;
    }

    public long getSeg() {
        return seg;
    }
    private void mostrarProgreso(JFrame vista,int puntuacion, int numPreguntas){
        this.puntuacion+=puntuacion;
        this.numPreguntas+=numPreguntas;    
        JOptionPane.showMessageDialog(vista, "Por ahora llevas acertadas "+this.puntuacion+"/"+this.numPreguntas);
    }
    private void mostrarProgreso(JFrame vista, int puntuacion, int numPreguntas, long seg){
        JOptionPane.showMessageDialog(vista, "Total "+puntuacion+"/"+numPreguntas,"Tiempo Total: "+Math.round(seg)+" Seg", JOptionPane.PLAIN_MESSAGE);
    }
}
