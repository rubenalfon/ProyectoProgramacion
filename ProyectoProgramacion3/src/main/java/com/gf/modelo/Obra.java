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
    private Autor autor;
    private Museo museo;

    public Obra() {
    }

    public Obra(int idObra, String nombreObra, String descripcionObra, String disciplinaObra, String urlObra, Autor autor, Museo museo) {
        this.idObra = idObra;
        this.nombreObra = nombreObra;
        this.descripcionObra = descripcionObra;
        this.disciplinaObra = disciplinaObra;
        this.urlObra = urlObra;
        this.autor = autor;
        this.museo = museo;
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Museo getMuseo() {
        return museo;
    }

    public void setMuseo(Museo museo) {
        this.museo = museo;
    }

    @Override
    public String toString() {
        return "Obra{" + "idObra=" + idObra + ", nombreObra=" + nombreObra + ", descripcionObra=" + descripcionObra + ", disciplinaObra=" + disciplinaObra + ", urlObra=" + urlObra + ", autor=" + autor + ", museo=" + museo + '}';
    }
}
