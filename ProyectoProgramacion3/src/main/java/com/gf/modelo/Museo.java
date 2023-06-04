/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.modelo;

/**
 *
 * @author Ruben
 */
public class Museo {

    private int idMuseo;
    private String nombreMuseo;
    private int idPais;

    public Museo() {
    }

    public Museo(int idMuseo, String nombreMuseo, int paisMuseo) {
        this.idMuseo = idMuseo;
        this.nombreMuseo = nombreMuseo;
        this.idPais = paisMuseo;
    }

    public int getIdMuseo() {
        return idMuseo;
    }

    public void setIdMuseo(int idMuseo) {
        this.idMuseo = idMuseo;
    }

    public String getNombreMuseo() {
        return nombreMuseo;
    }

    public void setNombreMuseo(String nombreMuseo) {
        this.nombreMuseo = nombreMuseo;
    }

    public int getPaisMuseo() {
        return idPais;
    }

    public void setPaisMuseo(int paisMuseo) {
        this.idPais = paisMuseo;
    }

    @Override
    public String toString() {
        return "Museo{" + "idMuseo=" + idMuseo + ", nombreMuseo=" + nombreMuseo + ", paisMuseo=" + idPais + '}';
    }

}
