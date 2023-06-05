/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controlador;

import com.gf.dao.MuseoDAO;
import com.gf.dao.ObraDAO;
import com.gf.dao.PaisDAO;
import com.gf.modelo.Autor;
import com.gf.modelo.Obra;
import com.gf.modelo.Pais;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.GeoPosition;
import com.gf.utils.ReverseGeocoding;
import com.gf.vista.PantallaDeCarga;
import com.gf.vista.VistaMapa;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;

/**
 *
 * @author parbalan
 */
public class ControladorMapa implements MouseListener, ActionListener {

    private VistaMapa vista;
    private int numImagenes;
    private int puntuacion;
    private ObraDAO odao;
    private PantallaDeCarga pantalla;

    public ControladorMapa(VistaMapa vista, int numImagenes) {
        this.vista = vista;
        this.vista.setMinimumSize(new Dimension(((Toolkit.getDefaultToolkit().getScreenSize().width / 10) * numImagenes), Toolkit.getDefaultToolkit().getScreenSize().height / 3));
        this.vista.getPanelContenedorImagenes().setPreferredSize(new Dimension(this.vista.getSize().width, Toolkit.getDefaultToolkit().getScreenSize().height / 12 + 10));
        this.pantalla= new PantallaDeCarga(this.vista);
        this.numImagenes = numImagenes;
        this.odao = new ObraDAO();
        setMapListeners();
        crearImagenes();
        this.vista.getPuntuacion().setText("0/" + numImagenes);
    }

    private void setMapListeners() {
        MouseInputListener mm = new PanMouseInputListener(this.vista.getMapViewer());
        this.vista.getMapViewer().addMouseListener(mm);
        this.vista.getMapViewer().addMouseMotionListener(mm);
        this.vista.getMapViewer().addMouseListener(this);
        this.vista.getMapViewer().addMouseWheelListener(new ZoomMouseWheelListenerCursor(this.vista.getMapViewer()));
    }

    private Image imagenRescalada(URL url, Dimension dimension) {
        ImageIcon imagen = new ImageIcon(url);
        Image imagenEscalada = imagen.getImage().getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
        return imagenEscalada;
    }

    private void crearImagenes() {
        ArrayList<Integer> idobras = new ArrayList<>();
        for (int i = 0; i < numImagenes; i++) {
            JButton button = new JButton();
            button.setBackground(Color.red);
            button.setForeground(Color.red);
            button.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 11, Toolkit.getDefaultToolkit().getScreenSize().height / 12));
            button.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 11, Toolkit.getDefaultToolkit().getScreenSize().height / 12));
            button.setMinimumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 11, Toolkit.getDefaultToolkit().getScreenSize().height / 12));
            button.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width / 11, Toolkit.getDefaultToolkit().getScreenSize().height / 12));
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setOpaque(true);
            button.addActionListener(this);
            button.setBorder(null);
            Obra obra = odao.obtenerObraAleatoria(idobras);
            idobras.add(obra.getIdObra());
            button.setName(obra.getMuseo().getPais().getNombrePais());
            System.out.println(obra.getUrlObra());
            System.out.println(obra.getNombreObra());
            try {
                button.setIcon(new ImageIcon(imagenRescalada(new URL(obra.getUrlObra()), button.getSize())));
            } catch (MalformedURLException ex) {
                Logger.getLogger(ControladorMapa.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("nyah");
            this.vista.getPanelContenedorImagenes().add(button);
        }
        this.vista.setBounds(this.pantalla.getBounds());
        this.vista.setLocation(this.pantalla.getLocation());
        this.vista.setVisible(true);
        this.pantalla.dispose();
        
    }

    private void resetearBotones() {
        for (Component component : this.vista.getPanelContenedorImagenes().getComponents()) {
            if (component instanceof JButton) {
                if (((JButton) component).getBorder() != null) {
                    ((JButton) component).setBorder(null);
                }
            }
        }
    }
    
    private JButton botonSeleccionado() {
        for (Component component : this.vista.getPanelContenedorImagenes().getComponents()) {
            if (component instanceof JButton) {
                if (((JButton) component).getBorder() != null) {
                    return (JButton) component;
                }
            }
        }
        return null;
    }
    public int getPuntuacion(){
        return puntuacion;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        GeoPosition clickedPosition = this.vista.getMapViewer().convertPointToGeoPosition(e.getPoint());
        String pais = ReverseGeocoding.getCountryName(clickedPosition.getLatitude(), clickedPosition.getLongitude());
        if (e.getClickCount() == 2) {
            System.out.println(clickedPosition);
            System.out.println(pais);
        }
        JButton uwu = botonSeleccionado();
        if (uwu != null) {
            numImagenes--;
            uwu.setBorder(null);
            if (uwu.getName().equals(pais)) {
                uwu.setForeground(Color.red);
                System.out.println("siuuuu");
                puntuacion++;
            } else {
                uwu.setForeground(Color.red);
            }
            uwu.setEnabled(false);
            ajustarPuntuacionPantalla();
        }
        if (numImagenes == 0) {
            this.vista.dispose();
            this.vista=null;
        }
    }

    public int getNumImagenes() {
        return numImagenes;
    }

    private void ajustarPuntuacionPantalla() {
        this.vista.getPuntuacion().setText(puntuacion + "" + this.vista.getPuntuacion().getText().substring(this.vista.getPuntuacion().getText().indexOf('/'), this.vista.getPuntuacion().getText().indexOf('/') + 2));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getBorder() == null) {
            resetearBotones();
            ((JButton) e.getSource()).setBorder(new LineBorder(Color.CYAN, 3));
            System.out.println(((JButton) e.getSource()).getName());
        } else if (((JButton) e.getSource()).getBorder() != null) {
            ((JButton) e.getSource()).setBorder(null);
        }
    }
}
