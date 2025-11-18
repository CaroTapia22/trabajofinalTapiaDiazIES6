package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Viaje;

public interface ViajeServiceI {

    List<Viaje> listarTodosViajes();
    List<Viaje> listarViajesActivos();

    void agregarViaje(Viaje viaje);
    void modificarViaje(Viaje viaje);
    void borrarViaje(Integer id) throws Exception;

    Viaje buscarViaje(Integer id) throws Exception;
    Viaje crearNuevoViaje();
}