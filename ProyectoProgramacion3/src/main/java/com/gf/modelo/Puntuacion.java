/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gf.modelo;

/**
 *
 * @author alfgonru
 */
public class Puntuacion {

    private int idPuntuacion;
    private String nombreUsuario;
    private int aciertos;
    private int puntosTotales;
    private int segundos;

    public Puntuacion() {
    }

    public Puntuacion(int idPuntuacion, String nombreUsuario, int aciertos, int puntosTotales, int segundos) {
        this.idPuntuacion = idPuntuacion;
        this.nombreUsuario = nombreUsuario;
        this.aciertos = aciertos;
        this.puntosTotales = puntosTotales;
        this.segundos = segundos;
    }

    public int getIdPuntuacion() {
        return idPuntuacion;
    }

    public void setIdPuntuacion(int idPuntuacion) {
        this.idPuntuacion = idPuntuacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(int puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    @Override
    public String toString() {
        return "Puntuacion{" + "idPuntuacion=" + idPuntuacion + ", nombreUsuario=" + nombreUsuario + ", aciertos=" + aciertos + ", puntosTotales=" + puntosTotales + ", segundos=" + segundos + '}';
    }
}
