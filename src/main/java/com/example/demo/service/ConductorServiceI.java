package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Conductor;

public interface ConductorServiceI {

    public List<Conductor> listarTodosConductores();
    public List<Conductor> listarTodosConductoresActivos();

    public void borrarConductor(Integer dni) throws Exception;  // borrado lógico
    public void agregarConductor(Conductor conductor);
    public void modificarConductor(Conductor conductor);

    public Conductor buscarConductor(Integer dni) throws Exception;
    public Conductor buscarUnoPorNombreConductor(String nombre);

    public Conductor crearNuevoConductor();
}

