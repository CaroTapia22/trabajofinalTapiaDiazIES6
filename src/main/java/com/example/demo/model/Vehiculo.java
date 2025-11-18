package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Vehiculo {

    @Id
    @Column(length = 10)
    private String patente;     
    @Column
    @NotBlank(message = "La marca es un campo requerido")
    private String marca;
    @Column
    @NotBlank(message = "El modelo es un campo requerido")
    private String modelo;
    @Column
    @NotBlank(message = "El color es un campo requerido")
    private String color;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoVehiculo tipo;  // X LUXE Y PREMIUM

    @Column
    private Boolean estado;

    // RELACION 1:1 CON CONDUCTOR 
    @OneToOne
    @JoinColumn(name = "dni_conductor", referencedColumnName = "dni", unique = true)
    private Conductor conductor;

    public Vehiculo() {}

    public Vehiculo(String patente, String marca, String modelo, String color,
                    TipoVehiculo tipo, Boolean estado, Conductor conductor) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipo = tipo;
        this.estado = estado;
        this.conductor = conductor;
    }
 
    // GET Y SETTERS
    public String getPatente() { return patente; }
    public void setPatente(String patente) 
    { this.patente = patente; }

    public String getMarca() { return marca; }
    public void setMarca(String marca)
     { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo)
     { this.modelo = modelo; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public TipoVehiculo getTipo() { return tipo; }
    public void setTipo(TipoVehiculo tipo) { this.tipo = tipo; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }

    public Conductor getConductor() { return conductor; }
    public void setConductor(Conductor conductor) 
    { this.conductor = conductor; }
}
