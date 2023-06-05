/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controlador;

import com.gf.dao.*;
import com.gf.modelo.*;
import com.gf.vista.vistaVerdaderoFalsoMuseos;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author Ruben
 */
public class ControladorVerdaderoFalsoMuseos implements MouseListener, ActionListener {

    private final vistaVerdaderoFalsoMuseos vista;
    private Connection con = null;

    private int indiceMuseoActual;
    private ArrayList<Museo> listaMuseos;

    private MuseoDAO mdao;

    public ControladorVerdaderoFalsoMuseos(vistaVerdaderoFalsoMuseos vista, int numeroMuseos) {
        this.vista = vista;
        this.mdao = new MuseoDAO();

        iniciar(numeroMuseos);
        siguienteMuseo();

        for (Museo museo : listaMuseos) {
            System.out.println("  " + museo.toString());
        }
    }

    private void iniciar(int numeroObras) {
        this.listaMuseos = new ArrayList<>();

        ArrayList<Integer> listaIdUsados = new ArrayList<>();

        inhabilitarBotones();

        for (int i = 0; i < numeroObras; i++) {
            Museo museo = mdao.obtenerMuseoAleatorio(listaIdUsados);
            this.listaMuseos.add(museo);
            listaIdUsados.add(museo.getIdMuseo());
        }
        JButton boton = this.vista.getjButtonFalso();
        boton.addActionListener(this);
        boton = this.vista.getjButtonVerdadero();
        boton.addActionListener(this);

        this.vista.getjButtonFalso().setFocusPainted(false);
        this.vista.getjButtonVerdadero().setFocusPainted(false);
    }

    private void siguienteMuseo() {
        this.vista.getjLabelMuseo().setText(this.listaMuseos.get(indiceMuseoActual).getNombreMuseo());
        habilitarBotones();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.indiceMuseoActual + 1 == this.listaMuseos.size()) {
            JOptionPane.showMessageDialog(vista, "Has completado todas las obras");
        } else {
            if (this.listaMuseos.get(indiceMuseoActual).getPais().getNombrePais().equals("Inexistente") && ((JButton) e.getSource()).getText().equals("Falso")) {
                inhabilitarBotones();
                System.out.println("BienF");
                this.indiceMuseoActual++;
                siguienteMuseo();
            } else if (!(this.listaMuseos.get(indiceMuseoActual).getPais().getNombrePais().equals("Inexistente")) && ((JButton) e.getSource()).getText().equals("Verdadero")) {
                inhabilitarBotones();
                System.out.println("BienV");
                this.indiceMuseoActual++;
                siguienteMuseo();
            } else {
                System.out.println("Mal");
                ((JButton) e.getSource()).setBackground(Color.decode("#ffcccb"));
            }
        }

    }

    private void inhabilitarBotones() {
        this.vista.getjButtonVerdadero().setEnabled(false);
        this.vista.getjButtonFalso().setEnabled(false);
    }

    private void habilitarBotones() {
        this.vista.getjButtonVerdadero().setEnabled(true);
        this.vista.getjButtonFalso().setEnabled(true);
        this.vista.getjButtonVerdadero().setBackground(Color.WHITE);
        this.vista.getjButtonFalso().setBackground(Color.WHITE);
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
}
