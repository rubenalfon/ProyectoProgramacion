/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.modelo;

/**
 *
 * @author Ruben
 */
public class Pais {

    private int idPais;
    private String NombrePais;

    public Pais() {
    }

    public Pais(int idPais, String NombrePais) {
        this.idPais = idPais;
        this.NombrePais = NombrePais;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return NombrePais;
    }

    public void setNombrePais(String NombrePais) {
        this.NombrePais = NombrePais;
    }

    @Override
    public String toString() {
        return "Pais{" + "idPais=" + idPais + ", NombrePais=" + NombrePais + '}';
    }
}
