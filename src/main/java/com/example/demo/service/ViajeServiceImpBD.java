package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Viaje;
import com.example.demo.repository.ViajeRepository;

@Service
public class ViajeServiceImpBD implements ViajeServiceI {

    @Autowired
    private ViajeRepository viajeRepository;

    @Override
    public List<Viaje> listarTodosViajes() {
        return (List<Viaje>) viajeRepository.findAll();
    }

    @Override
    public List<Viaje> listarViajesActivos() {
        return viajeRepository.findByEstado(true);
    }

    @Override
    public void agregarViaje(Viaje viaje) {
        viaje.setEstado(true);
        viajeRepository.save(viaje);
    }

    @Override
    public void modificarViaje(Viaje viaje) {
        viajeRepository.save(viaje);
    }

    @Override
    public void borrarViaje(Integer id) throws Exception {
        Viaje viaje = viajeRepository.findById(id)
                .orElseThrow(() -> new Exception("Viaje no encontrado"));
        viaje.setEstado(false);
        viajeRepository.save(viaje);
    }

    @Override
    public Viaje buscarViaje(Integer id) throws Exception {
        return viajeRepository.findById(id)
                .orElseThrow(() -> new Exception("Viaje no encontrado"));
    }

    @Override
    public Viaje crearNuevoViaje() {
        return new Viaje();
    }
}