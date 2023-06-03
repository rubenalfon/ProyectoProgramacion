/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.modelo;

/**
 *
 * @author Ruben
 */
public class Autor {

    private int idAutor;
    private String nombreAutor;
    private Pais paisAutor;

    public Autor() {
    }

    public Autor(int idAutor, String nombreAutor, Pais paisAutor) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.paisAutor = paisAutor;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public Pais getPaisAutor() {
        return paisAutor;
    }

    public void setPaisAutor(Pais paisAutor) {
        this.paisAutor = paisAutor;
    }

}
