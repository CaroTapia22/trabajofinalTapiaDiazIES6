package com.example.demo.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import com.example.demo.model.Vehiculo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.OneToOne;



@Component
@Entity
public class Conductor {

    @Id
    private Integer dni;

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

    // 1:1: UN CONDUCTOR TIENE  UN VEHICULO
    @OneToOne(mappedBy = "conductor")
    private Vehiculo vehiculo;


    //agregar @OneToMany con Viaje

    public Conductor() {
    }

    public Conductor(Integer dni, String nombre, String apellido,
                     String licencia, String telefono, LocalDate fechaRegistro, Boolean estado) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.licencia = licencia;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    // Getter y Setters
    public Integer getDni() { return dni; }
    public void setDni(Integer dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getLicencia() { return licencia; }
    public void setLicencia(String licencia) { this.licencia = licencia; }


    public String getTelefono() { 
        return telefono; 
    }
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }


    public LocalDate getFechaRegistro() { 
        return fechaRegistro;
     }
    public void setFechaRegistro(LocalDate fechaRegistro) {
         this.fechaRegistro = fechaRegistro;
     }


    public Boolean getEstado() { 
        return estado; 
    }
    public void setEstado(Boolean estado) {
         this.estado = estado;
     }


    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

}
