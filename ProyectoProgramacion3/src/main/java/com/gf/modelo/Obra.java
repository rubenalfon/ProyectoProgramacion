/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.modelo;

/**
 *
 * @author Ruben
 */
public class Obra {

    private int idObra;
    private String nombreObra;
    private String descripcionObra;
    private String disciplinaObra;
    private String urlObra;
    private int idAutor;
    private int idMuseo;

    public Obra() {
    }

    public Obra(int idObra, String nombreObra, String descripcionObra, String disciplinaObra, String urlObra, int idAutor, int idMuseo) {
        this.idObra = idObra;
        this.nombreObra = nombreObra;
        this.descripcionObra = descripcionObra;
        this.disciplinaObra = disciplinaObra;
        this.urlObra = urlObra;
        this.idAutor = idAutor;
        this.idMuseo = idMuseo;
    }

    

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public String getNombreObra() {
        return nombreObra;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    public String getDescripcionObra() {
        return descripcionObra;
    }

    public void setDescripcionObra(String descripcionObra) {
        this.descripcionObra = descripcionObra;
    }

    public String getDisciplinaObra() {
        return disciplinaObra;
    }

    public void setDisciplinaObra(String disciplinaObra) {
        this.disciplinaObra = disciplinaObra;
    }

    public String getUrlObra() {
        return urlObra;
    }

    public void setUrlObra(String urlObra) {
        this.urlObra = urlObra;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getIdMuseo() {
        return idMuseo;
    }

    public void setIdMuseo(int idMuseo) {
        this.idMuseo = idMuseo;
    }

    @Override
    public String toString() {
        return "Obra{" + "idObra=" + idObra + ", nombreObra=" + nombreObra + ", descripcionObra=" + descripcionObra + ", disciplinaObra=" + disciplinaObra + ", urlObra=" + urlObra + ", idAutor=" + idAutor + ", idMuseo=" + idMuseo + '}';
    }

    
    
    

}
