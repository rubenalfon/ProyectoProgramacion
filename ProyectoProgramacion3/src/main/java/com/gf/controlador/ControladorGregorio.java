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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
    private ObraDAO obraDao;
    
    public ControladorGregorio(VistaGregorio vista, int preguntas) {
        this.vista = vista;
        this.obraDao= new ObraDAO();
        this.pantallaCarga=new PantallaDeCarga(this.vista);
        cargarObras(preguntas);
    }
    private void cargarObras(int preguntas){
        HashMap<Integer,ImageIcon> obrasGregorio = new HashMap<>();
        HashMap<Integer,ImageIcon> obrasNoGregorio = new HashMap<>();      
        obrasGregorio.put(1, null);
        obrasNoGregorio.put(1, null);
        AutorDAO autorDao = new AutorDAO();
        for (int i = 0; i < preguntas; i++) {
            try {
                Obra obra =obraDao.obtenerEsculturaAleatoriaDeAutor(new ArrayList<>(obrasGregorio.keySet()),autorDao.obtenerAutorPorId(1));
                obrasGregorio.put(obra.getIdObra(), new ImageIcon(new URL(obra.getUrlObra())));
                Obra obra2 = obraDao.obtenerEsculturaAleatoriaNoDeAutor(new ArrayList<>(obrasNoGregorio.keySet()),autorDao.obtenerAutorPorId(1));
                obrasNoGregorio.put(obra2.getIdObra(), new ImageIcon(new URL(obra2.getUrlObra())));
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
        vista.setVisible(true);
        System.out.println(obrasGregorio.toString());
        System.out.println(obrasNoGregorio.toString());
    }
    
}
