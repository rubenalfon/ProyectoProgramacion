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
    private Pais pais;

    public Autor() {
    }

    public Autor(int idAutor, String nombreAutor, Pais pais) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.pais = pais;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Autor{" + "idAutor=" + idAutor + ", nombreAutor=" + nombreAutor + ", pais=" + pais + '}';
    }

    
}
