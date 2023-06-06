/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.controlador;

import com.gf.dao.AutorDAO;
import com.gf.dao.ObraDAO;
import com.gf.modelo.Obra;
import com.gf.vista.PantallaDeCarga;
import com.gf.vista.VistaGregorio;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author parbalan
 */
public class ControladorGregorio {
    private VistaGregorio vista;
    private PantallaDeCarga pantallaCarga;
    private int puntuacion;
    HashMap<Integer,ImageIcon> obrasImg;
    ArrayList<Integer> idsObraGregorio;
    ArrayList<Integer> idsObraNoGregorio;
    private ObraDAO obraDao;
    
    public ControladorGregorio(VistaGregorio vista, int preguntas) {
        this.vista = vista;
        this.obraDao= new ObraDAO();
        this.pantallaCarga=new PantallaDeCarga(this.vista);
        cargarObras(preguntas);
        insertarImagenEnBoton();
    }
    private void cargarObras(int preguntas){
        this.obrasImg = new HashMap<>();    
        AutorDAO autorDao = new AutorDAO();
        this.idsObraGregorio= new ArrayList<>();
        this.idsObraNoGregorio= new ArrayList<>();
        for (int i = 0; i < preguntas; i++) {
            try {
                Obra obra =obraDao.obtenerEsculturaAleatoriaDeAutor(new ArrayList<>(obrasImg.keySet()),autorDao.obtenerAutorPorId(1));
                obrasImg.put(obra.getIdObra(), new ImageIcon(new URL(obra.getUrlObra())));
                this.idsObraGregorio.add(obra.getIdObra());
                Obra obra2 = obraDao.obtenerEsculturaAleatoriaNoDeAutor(new ArrayList<>(obrasImg.keySet()),autorDao.obtenerAutorPorId(1));
                obrasImg.put(obra2.getIdObra(), new ImageIcon(new URL(obra2.getUrlObra())));
                this.idsObraNoGregorio.add(obra2.getIdObra());
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        this.vista.setBounds(this.pantallaCarga.getBounds());
        this.vista.setLocation(this.pantallaCarga.getLocation());
        this.vista.setVisible(true);
        this.pantallaCarga.dispose();
        this.pantallaCarga=null;
        vista.setVisible(true);
        System.out.println(obrasImg.toString());
    }
    private void insertarImagenEnBoton(){
        try{
            ArrayList mezcla = new ArrayList();
            mezcla.add(idsObraGregorio.get(0));
            mezcla.add(idsObraNoGregorio.get(0));
            idsObraGregorio.remove(0);
            idsObraGregorio.remove(0);
            Collections.shuffle(mezcla);
            this.vista.getjButton1().setIcon(reEscalarImagen(obrasImg.get(mezcla.get(0))));
            this.vista.getjButton2().setIcon(reEscalarImagen(obrasImg.get(mezcla.get(1))));
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    private ImageIcon reEscalarImagen(ImageIcon img){
        Image imagenEscalada = img.getImage().getScaledInstance(this.vista.getjButton1().getSize().width, this.vista.getjButton1().getSize().height, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }
    
}
