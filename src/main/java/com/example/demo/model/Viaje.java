package com.example.demo.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Component
@Entity
public class Viaje {

    // atributos privados

    @Id
    private Integer nroSolicitud;

    @Column
    private String origen;

    @Column
    private String destino;

    @Column
    private Double costoViaje;

    @Column
    private Boolean estadoViaje;

    // constructores

    public Viaje () {
    }

    public Viaje(Integer nroSolicitud, String origen, String destino, Double costoViaje, Boolean estadoViaje) {
        this.nroSolicitud = nroSolicitud;
        this.origen = origen;
        this.destino = destino;
        this.costoViaje = costoViaje;
        this.estadoViaje = estadoViaje;
    }

    // metodos getters y setters

    public Integer getNroSolicitud() {
        return nroSolicitud;
    }

    public void setNroSolicitud(Integer nroSolicitud) {
        this.nroSolicitud = nroSolicitud;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getCostoViaje() {
        return costoViaje;
    }

    public void setCostoViaje(Double costoViaje) {
        this.costoViaje = costoViaje;
    }

    public Boolean getEstadoViaje() {
        return estadoViaje;
    }

    public void setEstadoViaje(Boolean estadoViaje) {
        this.estadoViaje = estadoViaje;
    }
    
    // falta agregar relación con usuario 
    // MonytoMony


}
