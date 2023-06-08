/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controlador;
import com.gf.dao.ObraDAO;
import com.gf.modelo.Obra;
import com.gf.utils.PantallaInfo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.viewer.GeoPosition;
import com.gf.utils.*;
import com.gf.vista.PantallaDeCarga;
import com.gf.vista.VistaMapa;
import java.awt.Toolkit;
import java.net.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;

/**
 *
 * @author parbalan
 */
public class ControladorMapa implements MouseListener, ActionListener {

    private VistaMapa vista;
    private int numPreguntas;
    private int numImagenes;
    private int puntuacion;
    private ObraDAO odao;
    private PantallaDeCarga pantalla;

    public ControladorMapa(VistaMapa vista, int numImagenes) {
        this.vista = vista;
        this.vista.setVisible(false);
        this.vista.setMinimumSize(new Dimension(((Toolkit.getDefaultToolkit().getScreenSize().width / 10) * numImagenes), Toolkit.getDefaultToolkit().getScreenSize().height / 2));
        this.vista.getPanelContenedorImagenes().setPreferredSize(new Dimension(this.vista.getSize().width, Toolkit.getDefaultToolkit().getScreenSize().height / 12 + 10));
        this.numImagenes = numImagenes;
        this.odao = new ObraDAO();
        this.pantalla= new PantallaDeCarga();
        this.numPreguntas=numImagenes;
        setMapListeners();
        crearImagenes();
        
        PantallaInfo.configPantalla(this.vista);
        PantallaInfo.setPosicion(this.vista);
        PantallaInfo.setPuntuacionPantalla(this.vista.getPuntuacion(),puntuacion , numPreguntas);
    }

    private void setMapListeners() {
        MouseInputListener mm = new PanMouseInputListener(this.vista.getMapViewer());
        this.vista.getMapViewer().addMouseListener(mm);
        this.vista.getMapViewer().addMouseMotionListener(mm);
        this.vista.getMapViewer().addMouseListener(this);
        this.vista.getMapViewer().addMouseWheelListener(new ZoomMouseWheelListenerCursor(this.vista.getMapViewer()));
    }


    public VistaMapa getVista() {
        return vista;
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
            try {
                Obra obra = odao.obtenerObraAleatoria(idobras);
                idobras.add(obra.getIdObra());
                button.setToolTipText(obra.getNombreObra());
                button.setName(obra.getMuseo().getPais().getNombrePais());
                System.out.println(obra.getUrlObra());
                System.out.println(obra.getNombreObra());
                try {
                    button.setIcon(PantallaInfo.reEscalarImagen(new ImageIcon(new URL(obra.getUrlObra())), button.getSize().width, button.getSize().height));
                } catch (MalformedURLException ex) {
                    Logger.getLogger(ControladorMapa.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                this.vista.dispose();
                this.pantalla.dispose();
                JOptionPane.showMessageDialog(null, "Error a la hora de conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
                
                return;
            }
            this.vista.getPanelContenedorImagenes().add(button);
        }
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
        JButton uwu = botonSeleccionado();
        if (uwu != null) {
            numImagenes--;
            uwu.setBorder(null);
            if (uwu.getName().equals(pais)) {
                uwu.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GREEN));
                System.out.println("siuuuu");
                puntuacion++;
            } else {
                uwu.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.RED));
            }
            uwu.setEnabled(false);
            PantallaInfo.setPuntuacionPantalla(this.vista.getPuntuacion(),puntuacion , numPreguntas);
        }
        if (numImagenes == 0) {
            this.vista.dispose();
        }
    }

    public int getNumPreguntas() {
        return numPreguntas;
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
