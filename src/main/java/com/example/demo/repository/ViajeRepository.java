package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Viaje;

@Repository
public interface ViajeRepository extends CrudRepository<Viaje, Integer> {

    List<Viaje> findByEstado(Boolean estado);

}