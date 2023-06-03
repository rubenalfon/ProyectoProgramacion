package com.gf.main;

import javax.swing.*;
import java.awt.*;

public class Prueba extends JFrame {

    public Prueba() {
        // Crear el panel de fondo personalizado
        FondoPanel fondoPanel = new FondoPanel();

        // Establecer el panel de fondo como el contenido principal del JFrame
        setContentPane(fondoPanel);

        // Configurar la ventana JFrame
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Prueba());
    }

    // Clase personalizada para el panel de fondo
    static class FondoPanel extends JPanel {
        private Image imagenFondo;

        public FondoPanel() {
            // Cargar la imagen de fondo
            ImageIcon imageIcon = new ImageIcon("./src/files/Gui_principal.png");
            imagenFondo = imageIcon.getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dibujar la imagen de fondo
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
