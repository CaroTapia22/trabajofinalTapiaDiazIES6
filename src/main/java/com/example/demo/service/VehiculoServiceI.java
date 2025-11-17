package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Vehiculo;

public interface VehiculoServiceI {

  List<Vehiculo> listarTodosVehiculos();
  List<Vehiculo> listarTodosVehiculosActivos();

    void agregarVehiculo(Vehiculo vehiculo);

    void modificarVehiculo(Vehiculo vehiculo);

    void borrarVehiculo(String patente) throws Exception; //BORRADO LOGICO

    Vehiculo buscarVehiculo(String patente) throws Exception;

    Vehiculo crearNuevoVehiculo();
}

