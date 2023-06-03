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
    private Pais paisMuseo;

    public Museo() {
    }

    public Museo(int idMuseo, String nombreMuseo, Pais paisMuseo) {
        this.idMuseo = idMuseo;
        this.nombreMuseo = nombreMuseo;
        this.paisMuseo = paisMuseo;
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

    public Pais getPaisMuseo() {
        return paisMuseo;
    }

    public void setPaisMuseo(Pais paisMuseo) {
        this.paisMuseo = paisMuseo;
    }

}
