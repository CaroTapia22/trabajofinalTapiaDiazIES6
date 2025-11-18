package com.example.demo.repository;


import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Usuario;

// JpaRepository

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,String>{
	public List<Usuario> findByEstado (Boolean estado);

      

}


