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
    private int idPais;

    public Autor() {
    }

    public Autor(int idAutor, String nombreAutor, int paisAutor) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.idPais = paisAutor;
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

    public int getPaisAutor() {
        return idPais;
    }

    public void setPaisAutor(int paisAutor) {
        this.idPais = paisAutor;
    }

    @Override
    public String toString() {
        return "Autor{" + "idAutor=" + idAutor + ", nombreAutor=" + nombreAutor + ", paisAutor=" + idPais + '}';
    }

}
