package com.example.demo.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;


@Component
@Entity
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank(message = "El nombre es un campo requerido")
    private String nombre;
    @Column
    @NotBlank(message = "El apellido es un campo requerido")
    @Size(min = 3, max = 20, message = "El apellido debe tener entre 3 y 20 caracteres")
    private String apellido;
    @Column
    @NotBlank(message = "La licencia es un campo requerido")
    private String licencia;
    @Column
    @Pattern(regexp = "\\d+", message = "Solo se permite ingresar números")
    private String telefono;
    @Column
    private LocalDate fechaRegistro;
    @Column
    private Boolean estado;   


    //agregar @OneToOne con Vehiculo
    //agregar @OneToMany con Viaje

    public Conductor() {
    }

    public Conductor(Integer id, String nombre, String apellido,
                     String licencia, String telefono, LocalDate fechaRegistro, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.licencia = licencia;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    // Getter y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getLicencia() { return licencia; }
    public void setLicencia(String licencia) { this.licencia = licencia; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public Boolean getActivo() { return estado; }
    public void setActivo(Boolean estado) { this.estado = estado; }
}
