package com.example.demo.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

@Component
@Entity
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // un usuario puede tener muchos viajes
    @ManyToOne
    private Usuario usuario;  

    // un vehículo puede tener muchos viajes
    @ManyToOne
    private Vehiculo vehiculo; 

    // corta / media / larga
    @Enumerated(EnumType.STRING)
    private TipoViaje tipoViaje; 

    // se calcula automáticamente

    @Column
    private Double costoFinal; 

    @Column
    private LocalDateTime fechaHora;

    // para borrado lógico
    @Column
    private Boolean estado; 

    public Viaje() {
        this.estado = true;
        this.fechaHora = LocalDateTime.now();
    }

    // GETTERS Y SETTERS

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public TipoViaje getTipoViaje() {
        return tipoViaje;
    }
    public void setTipoViaje(TipoViaje tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    public Double getCostoFinal() {
        return costoFinal;
    }
    public void setCostoFinal(Double costoFinal) {
        this.costoFinal = costoFinal;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
