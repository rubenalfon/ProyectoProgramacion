/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controlador;

import com.gf.dao.*;
import com.gf.modelo.*;
import com.gf.utils.PantallaInfo;
import com.gf.vista.vistaVerdaderoFalsoMuseos;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

/**
 *
 * @author Ruben
 */
public class ControladorVerdaderoFalsoMuseos implements MouseListener, ActionListener {

    private final vistaVerdaderoFalsoMuseos vista; // La vista del juego

    private int indiceMuseoActual; // El indice de la obra actual que se está jugando
    private ArrayList<Museo> listaMuseos; // Lista con todos los museos que se van a jugar

    private int UltimoMuseoFalladoId; // El id del ultimo museo que el usuario ha fallado
    private int contadorAciertos; // Cuenta las veces que el usuario acierta un museo.

    private MuseoDAO mdao; // DAO de museos

    public ControladorVerdaderoFalsoMuseos(vistaVerdaderoFalsoMuseos vista, int numeroMuseos) {
        this.vista = vista;
        this.vista.setDefaultCloseOperation(vista.DISPOSE_ON_CLOSE);
        this.mdao = new MuseoDAO();
        PantallaInfo.configPantalla(this.vista);
        PantallaInfo.setPosicion(this.vista);
        this.listaMuseos = new ArrayList<>();

        this.vista.getjButtonFalso().addActionListener(this);
        this.vista.getjButtonVerdadero().addActionListener(this);

        iniciar(numeroMuseos);
    }

    public vistaVerdaderoFalsoMuseos getVista() {
        return vista;
    }

    public int getNumPreguntas() {
        return listaMuseos.size();
    }

    public int getContadorAciertos() {
        return contadorAciertos;
    }

    private void iniciar(int numeroObras) {
        inhabilitarBotones();

        ArrayList<Integer> listaIdUsados = new ArrayList<>();
        for (int i = 0; i < numeroObras; i++) { // Pedir los museos a BD
            try {
                Museo museo = mdao.obtenerMuseoAleatorio(listaIdUsados);
                this.listaMuseos.add(museo);
                listaIdUsados.add(museo.getIdMuseo());
            } catch (SQLException ex) {
                Logger.getLogger(ControladorVerdaderoFalsoMuseos.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error a la hora de conectar con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        cargarMuseoActual();
    }

    private void cargarMuseoActual() { // Poner el museo actual
        this.vista.getjLabelMuseo().setText(this.listaMuseos.get(indiceMuseoActual).getNombreMuseo());

        mostrarPuntuacion();
        habilitarBotones();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.listaMuseos.get(indiceMuseoActual).getPais().getNombrePais().equals("Inexistente") && ((JButton) e.getSource()).getText().equals("Falso")) {
            acertar();
        } else if (!(this.listaMuseos.get(indiceMuseoActual).getPais().getNombrePais().equals("Inexistente")) && ((JButton) e.getSource()).getText().equals("Verdadero")) {
            acertar();
        } else { // Si falla
            ((JButton) e.getSource()).setBackground(Color.decode("#501a0e")); // Rojo
            UltimoMuseoFalladoId = this.listaMuseos.get(this.indiceMuseoActual).getIdMuseo();
            this.vista.getjLabelPuntuacion().setForeground(null); // Poner el color por defecto

            if (((JButton) e.getSource()).getText().equals("Falso")) { // Poner en verde el boton correcto
                this.vista.getjButtonVerdadero().setBackground(Color.decode("#1b4722")); // Verde
            } else {
                this.vista.getjButtonFalso().setBackground(Color.decode("#1b4722")); // Verde
            }

        }
    }

    private void acertar() {
        if (UltimoMuseoFalladoId != this.listaMuseos.get(this.indiceMuseoActual).getIdMuseo()) { // Si no la ha acertado despues de fallar.
            this.contadorAciertos++;
            this.vista.getjLabelPuntuacion().setForeground(Color.decode("#1f6a2f")); // Verde
        }
        inhabilitarBotones();
        if (this.indiceMuseoActual + 1 == this.listaMuseos.size()) {
            mostrarPuntuacion();
            this.vista.dispose();
        } else {
            this.indiceMuseoActual++;
            cargarMuseoActual();
        }
    }

    private void inhabilitarBotones() {
        this.vista.getjButtonVerdadero().setEnabled(false);
        this.vista.getjButtonFalso().setEnabled(false);
    }

    private void habilitarBotones() {
        this.vista.getjButtonVerdadero().setEnabled(true);
        this.vista.getjButtonFalso().setEnabled(true);
        this.vista.getjButtonVerdadero().setBackground(new Color(74, 74, 74, 255)); // Gris
        this.vista.getjButtonFalso().setBackground(new Color(74, 74, 74, 255)); // Gris
    }

    private void mostrarPuntuacion() {
        this.vista.getjLabelPuntuacion().setText(this.contadorAciertos + "/" + this.listaMuseos.size());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}