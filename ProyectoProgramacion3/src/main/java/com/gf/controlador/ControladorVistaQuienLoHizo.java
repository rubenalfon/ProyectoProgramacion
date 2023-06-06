/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controlador;

import com.gf.dao.*;
import com.gf.modelo.*;
import com.gf.vista.VistaQuienLoHizo;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Ruben
 */
public class ControladorVistaQuienLoHizo implements MouseListener, ActionListener {

    private final VistaQuienLoHizo vista; // La vista del juego
    private ArrayList<JButton> listaBotones; // Lista de los botones de nombres de autores

    private int indiceObraActual; // El indice de la obra actual que se está jugando
    private int numeroBotones; // El numero de botones que se van a mostrar al usuario por cada obra
    private int UltimaObraFalladaId; // El id de la ultima obra que el usuario ha fallado
    private int contadorAciertos;  // Cuenta las veces que el usuario acierta un autor
    private ArrayList<Obra> listaObras; // Lista con todas las obras que se van a jugar
    private ArrayList<Autor> listaAutores; // Lista de los autores expuestos para adivinar en la obra actual

    private ObraDAO odao; // DAO de obra
    private AutorDAO adao; // DAO de autores

    public ControladorVistaQuienLoHizo(VistaQuienLoHizo vista, int numeroObras, int numeroBotones) {
        this.vista = vista;
        this.odao = new ObraDAO();
        this.adao = new AutorDAO();
        this.numeroBotones = numeroBotones;

        this.listaObras = new ArrayList<>();
        this.listaBotones = new ArrayList<>();

        iniciar(numeroObras);

        // Hacer que las imagenes reescalen al cambiar de tamaño la ventana
        this.vista.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ponerImagenActual();
            }
        });
    }

    private void iniciar(int numeroObras) {
        for (int i = 0; i < this.numeroBotones; i++) { // Generar los botones
            JButton boton = new JButton(String.valueOf(i + 1));
            boton.setPreferredSize(new Dimension(100, 40));
            boton.setFocusPainted(false);
            boton.addActionListener(this);
            this.vista.getjPanelBotones().add(boton);
            this.listaBotones.add(boton);
        }
        inhabilitarBotones();

        ArrayList<Integer> listaIdUsados = new ArrayList<>();
        for (int i = 0; i < numeroObras; i++) { // Pedir las obras a BD
            Obra obra = this.odao.obtenerObraAleatoria(listaIdUsados);
            this.listaObras.add(obra);
            listaIdUsados.add(obra.getIdObra());
        }
        siguienteImagen();
    }

    private void siguienteImagen() {
        ponerImagenActual();
        recogerAutores(this.numeroBotones);
        ponerBotones();
        mostrarPuntuacion();
        habilitarBotones();
    }

    private void ponerImagenActual() { // Pone en pantalla la imagen del incideObraActual
        try {
            Dimension d = new Dimension(this.vista.getjLabelImagen().getSize());
            URL url = new URL(this.listaObras.get(this.indiceObraActual).getUrlObra());

            Icon imagen = new ImageIcon(imagenRescalada(new ImageIcon(url), d));
            this.vista.getjLabelImagen().setIcon(imagen);

        } catch (MalformedURLException ex) {
            Dimension d = new Dimension(this.vista.getjLabelImagen().getSize());
            Icon imagen = new ImageIcon(imagenRescalada(new ImageIcon("./src/files/imagenNoEncontrada.jpg"), d));
            this.vista.getjLabelImagen().setIcon(imagen);
        }
    }

    private Image imagenRescalada(ImageIcon imagen, Dimension dimension) { // Reescala la imagen al tamaño máximo sin cambiar la relacion de aspecto

        double escalaAncho = dimension.getWidth() / imagen.getIconWidth();
        double escalaAlto = dimension.getHeight() / imagen.getIconHeight();
        double escalaFinal = Math.min(escalaAncho, escalaAlto);

        Image imagenEscalada = imagen.getImage().getScaledInstance((int) (imagen.getIconWidth() * escalaFinal),
                (int) (imagen.getIconHeight() * escalaFinal), Image.SCALE_DEFAULT);

        return imagenEscalada;
    }

    private void recogerAutores(int numAutores) { // Recoge de la BD autores a mostrar en los botones
        this.listaAutores = new ArrayList<>();

        // La lista de id de autores que no tiene que devolver el dao, empieza con el id del autor de la obra.
        ArrayList<Integer> listaNoUsar = new ArrayList<>(Arrays.asList(this.listaObras.get(this.indiceObraActual).getAutor().getIdAutor()));

        this.listaAutores.add(this.listaObras.get(this.indiceObraActual).getAutor());
        for (int i = 1; i < numAutores; i++) {
            Autor autor = this.adao.obtenerAutorAleatorio(listaNoUsar);

            listaNoUsar.add(autor.getIdAutor());
            this.listaAutores.add(autor);
        }
        Collections.shuffle(this.listaAutores);
    }

    private void ponerBotones() { // Pone el texto de los botones según la listaAutores
        for (int i = 0; i < this.listaBotones.size(); i++) {
            this.listaBotones.get(i).setText(this.listaAutores.get(i).getNombreAutor());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getText().equals(this.listaObras.get(this.indiceObraActual).getAutor().getNombreAutor())) { // Si acierta

            if (UltimaObraFalladaId != this.listaObras.get(this.indiceObraActual).getIdObra()) { // Si la ha acertado despues de no fallar.
                this.contadorAciertos++;
                this.vista.getjLabelPuntuacion().setForeground(Color.decode("#317f43")); // Verde
            }
            inhabilitarBotones();

            if (this.indiceObraActual + 1 == this.listaObras.size()) { // Si es la ultima obra
                mostrarPuntuacion();
                JOptionPane.showMessageDialog(vista, "Has completado todas las obras. Has obtenido " + this.contadorAciertos + " de " + this.listaObras.size() + ".");
            } else {
                this.indiceObraActual++;
                siguienteImagen();
            }

        } else {
            ((JButton) e.getSource()).setBackground(Color.decode("#ffcccb")); // Rojo
            UltimaObraFalladaId = this.listaObras.get(this.indiceObraActual).getIdObra();
            this.vista.getjLabelPuntuacion().setForeground(null); // El color por defecto

            for (JButton boton : listaBotones) { // Poner en verde el boton correcto
                if (boton.getText().equals(this.listaObras.get(this.indiceObraActual).getAutor().getNombreAutor())) {
                    boton.setBackground(Color.decode("#00e059")); // Verde
                }
            }
        }
    }

    private void inhabilitarBotones() {
        for (JButton boton : this.listaBotones) {
            boton.setEnabled(false);
            boton.setBackground(Color.decode("#3d3d3d")); // Gris
        }
    }

    private void habilitarBotones() {
        for (JButton boton : this.listaBotones) {
            boton.setEnabled(true);
            boton.setBackground(Color.WHITE);
        }
    }

    private void mostrarPuntuacion() {
        this.vista.getjLabelPuntuacion().setText(this.contadorAciertos + "/" + this.listaObras.size());
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
