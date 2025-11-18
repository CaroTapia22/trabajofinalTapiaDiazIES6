package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Conductor;

@Repository
public interface ConductorRepository extends CrudRepository<Conductor, Integer> {

    // listar conductores activos
    List<Conductor> findByEstado(Boolean estado);
}

