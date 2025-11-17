package com.example.demo.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;




@Component
@Entity
public class Usuario {

    // atributos privados

    @Id
    private String dni;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String correo;


    @Column
    private String telefono;

    @Column
    private Boolean estado;

    // constructores

    public Usuario () {
    }

    public Usuario (String dni, String nombre, String apellido, String correo, String telefono, Boolean estado){

        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo; 
        this.telefono = telefono;
        this.estado = estado;
    }

        // metodos getters y setters

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }



// agregar relación con Viaje cuando este creada
// @MonytoMany
}