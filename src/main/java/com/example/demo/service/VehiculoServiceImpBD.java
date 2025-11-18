package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Vehiculo;
import com.example.demo.repository.VehiculoRepository;

@Service
public class VehiculoServiceImpBD implements VehiculoServiceI {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> listarTodosVehiculos() {
        return (List<Vehiculo>) vehiculoRepository.findAll();
    }

    @Override
    public List<Vehiculo> listarTodosVehiculosActivos() {
        return vehiculoRepository.findByEstado(true);
    }

    @Override
    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculo.setEstado(true);  //cada vez que se cree por primera vez sera activo por defecto
        vehiculoRepository.save(vehiculo);
    }

    @Override
    public void modificarVehiculo(Vehiculo vehiculo) {
        vehiculoRepository.save(vehiculo);
    }

    @Override
    public void borrarVehiculo(String patente) throws Exception {
        Vehiculo vehiculoBorrar = vehiculoRepository.findById(patente)
        .orElseThrow(() -> new Exception("Vehículo no encontrado"));
        vehiculoBorrar.setEstado(false);     
        vehiculoRepository.save(vehiculoBorrar);
    }

    @Override
    public Vehiculo buscarVehiculo(String patente) throws Exception {
        return vehiculoRepository.findById(patente)
                .orElseThrow(() -> new Exception("Vehículo no encontrado"));
    }

    @Override
    public Vehiculo crearNuevoVehiculo() {
        return new Vehiculo();
    }
}

