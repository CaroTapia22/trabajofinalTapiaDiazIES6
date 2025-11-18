package com.example.demo.model;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;




@Component
@Entity
public class Usuario {
    // atributos privados

    @Id
    @NotBlank(message = "El campo DNI es obligatorio")
    @Size(min=7, max=10, message = "El DNI debe tener entre 7 y 10 caracteres")
    @Pattern(regexp= "\\d+", message = "El DNI solo puede tener numeros")
    private String dni;

    @Column
    @NotBlank(message = "El campo nombre es obligatorio")
    @Size(min=3, max= 20, message = "El nombre debe teber entre 3 y 20 caracteres")
    private String nombre;

    @Column
    @NotBlank(message = "El campo apellido es obligatorio" )
    @Size(min=3, max=20, message= "El apellido debe tener entre 3 y 20 caracteres")
    private String apellido;

    @Column
    @NotBlank(message= "El campo correo electronico es obligatorio")
    @Email(message = "Debe ingresae un correo valido")
    private String correo;


    @Column
    @NotBlank(message= "El campo telefono es obligatorio")
    @Pattern(regexp="\\+?\\d{7,15}", message= "El telefono debe tener entre 7 y 15 digitos")
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

}

// agregar relación con Viaje cuando este creada
// @MonytoMany