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
    private Integer dni;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String correo;

    @Column
    private String formaPago;

    @Column
    private String telefono;

    @Column
    private Boolean estado;

    // constructores

    public Usuario () {
    }

    public Usuario (Integer dni, String nombre, String apellido, String correo, String formaPago, String telefono, Boolean estado){

        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo; 
        this.formaPago = formaPago;
        this.telefono = telefono;
        this.estado = estado;
    }

        // metodos getters y setters

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
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

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
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
// @OnetoMany
}