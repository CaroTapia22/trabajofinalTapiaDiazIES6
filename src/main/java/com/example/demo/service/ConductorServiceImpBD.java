package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.model.Conductor;
import com.example.demo.repository.ConductorRepository;

@Service
@Qualifier("servicioConductorMySQL")
public class ConductorServiceImpBD implements ConductorServiceI {

    @Autowired
    private Conductor nuevoConductor;     

    @Autowired
    private ConductorRepository conductorRepository;

    @Override
    public void borrarConductor(Integer id) throws Exception {
        Conductor conductorBorrar = conductorRepository.findById(id)
                .orElseThrow(() -> new Exception("Conductor no encontrado"));
        conductorBorrar.setEstado(false);   // BORRADO LOGICO
        conductorRepository.save(conductorBorrar);
    }

    @Override
    public void agregarConductor(Conductor conductor) {
        conductor.setEstado(true);  //CADA VEZ QUE SE GUARDE SE CREE PO PRIMERA VEZ, POR DEFECTO SERA TRUE
        conductorRepository.save(conductor);
    }

    @Override
    public void modificarConductor(Conductor conductor) {
        conductorRepository.save(conductor);
    }

    @Override
    public List<Conductor> listarTodosConductores() {
        return (List<Conductor>) conductorRepository.findAll();
    }

    @Override
    public Conductor buscarConductor(Integer id) throws Exception {
        return conductorRepository.findById(id)
                .orElseThrow(() -> new Exception("Conductor no encontrado"));
    }

    @Override
    public Conductor buscarUnoPorNombreConductor(String nombre) {
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnoPorNombreConductor'");
    }

    @Override
    public Conductor crearNuevoConductor() {
        return nuevoConductor;
    }

    @Override
    public List<Conductor> listarTodosConductoresActivos() {
        return conductorRepository.findByEstado(true);
    }
}
