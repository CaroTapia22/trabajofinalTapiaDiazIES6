package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Conductor;

public interface ConductorServiceI {

    List<Conductor> listarTodosConductores();
    List<Conductor> listarTodosConductoresActivos();

    void borrarConductor(Integer dni) throws Exception;  // borrado lógico
    void agregarConductor(Conductor conductor);
    void modificarConductor(Conductor conductor);

    Conductor buscarConductor(Integer dni) throws Exception;
    Conductor buscarUnoPorNombreConductor(String nombre);

    Conductor crearNuevoConductor();
}

