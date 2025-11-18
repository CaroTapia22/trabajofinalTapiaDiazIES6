package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Vehiculo;

@Repository
public interface VehiculoRepository extends CrudRepository<Vehiculo, String> {
    List<Vehiculo> findByEstado(Boolean estado); // LISTA SOLO LOS VEHICULOS ACTIVOS
}


