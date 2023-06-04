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
import com.gf.vista.VistaMapa;
import java.awt.Image;
import java.awt.Toolkit;
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

/**
 *
 * @author parbalan
 */
public class ControladorMapa implements MouseListener, ActionListener {

    private VistaMapa vista;
    private int numImagenes;
    private ObraDAO odao;
    private PaisDAO pdao;
    private MuseoDAO mdao;
    public ControladorMapa(VistaMapa vista, int numImagenes) {
        this.vista = vista;
        this.numImagenes = numImagenes;
        this.odao = new ObraDAO();
        this.pdao = new PaisDAO();
        this.mdao = new MuseoDAO();
        this.vista.setMinimumSize(new Dimension(((Toolkit.getDefaultToolkit().getScreenSize().width / 12) * numImagenes), Toolkit.getDefaultToolkit().getScreenSize().height/3));
        this.vista.getPanelContenedorImagenes().setPreferredSize(new Dimension(this.vista.getSize().width, Toolkit.getDefaultToolkit().getScreenSize().height/13+10));        
        setMapListeners();
        crearImagenes();
        this.vista.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    private void setMapListeners() {
        MouseInputListener mm = new PanMouseInputListener(this.vista.getMapViewer());
        this.vista.getMapViewer().addMouseListener(mm);
        this.vista.getMapViewer().addMouseMotionListener(mm);
        this.vista.getMapViewer().addMouseListener(this);
    }

    private Image imagenRescalada(URL url, Dimension dimension){
        ImageIcon imagen = new ImageIcon(url);
        Image imagenEscalada = imagen.getImage().getScaledInstance(dimension.width,dimension.height, Image.SCALE_DEFAULT);
        return imagenEscalada;
    }
    private void crearImagenes(){
        ArrayList<Integer> idobras = new ArrayList<>();
        for (int i = 0; i < numImagenes; i++) {
            
            
            JButton button = new JButton();
            button.setBackground(Color.red);
            button.setForeground(Color.red);
            button.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/13,Toolkit.getDefaultToolkit().getScreenSize().height/13));
            button.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/13,Toolkit.getDefaultToolkit().getScreenSize().height/13));
            button.setMinimumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/13,Toolkit.getDefaultToolkit().getScreenSize().height/13));
            button.setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/13,Toolkit.getDefaultToolkit().getScreenSize().height/13));
            System.out.println(button.getSize());
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setOpaque(true);
            button.addActionListener(this);
            button.setBorder(null);
            Obra obra = odao.obtenerObraAleatoria(idobras);
            idobras.add(obra.getIdObra());
            button.setName(String.valueOf(pdao.obtenerPaisPorId(mdao.obtenerMuseoPorId(obra.getIdMuseo()).getPaisMuseo()).getNombrePais()));
            try {
                button.setIcon(new ImageIcon(imagenRescalada(new URL(obra.getUrlObra()), button.getSize())));
            } catch (MalformedURLException ex) {
                Logger.getLogger(ControladorMapa.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("nyah");
            this.vista.getPanelContenedorImagenes().add(button);
        }
        
        this.vista.getPanelContenedorImagenes().setBackground(Color.GRAY);
        
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

    @Override
    public void mouseClicked(MouseEvent e) {
        GeoPosition clickedPosition = this.vista.getMapViewer().convertPointToGeoPosition(e.getPoint());
        String pais= ReverseGeocoding.getCountryName(clickedPosition.getLatitude(), clickedPosition.getLongitude());
        if (e.getClickCount() == 2) {
            System.out.println(clickedPosition);
            System.out.println(pais);
        }
        JButton uwu = botonSeleccionado();
        if (uwu != null) {
            uwu.setBorder(null);
            if(uwu.getName().equals(pais)){
                System.out.println("siuuuu");
                uwu.setVisible(false);
                uwu.setEnabled(false);
            }
            
        }
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