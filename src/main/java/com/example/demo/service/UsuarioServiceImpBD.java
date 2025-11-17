package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
@Qualifier("servicioUsuarioMySQL")
public class UsuarioServiceImpBD implements UsuarioService {
    
    //inyeccion de dependecia
    @Autowired
    private UsuarioRepository usuarioRepository;

    // sobreescribiendo
    @Override
    public void borrarUsuario(String dni) throws Exception {
        Usuario usuarioBorrar = usuarioRepository.findById(dni)
                .orElseThrow(() -> new Exception("usuario no encontrado"));
        usuarioBorrar.setEstado(false);
        usuarioRepository.save(usuarioBorrar);
    }

    @Override
    public void agregarUsuario(Usuario usuario) {
        usuario.setEstado(true);
        usuarioRepository.save(usuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarTodosUsuario() {
        //agregamos conversion a (List<Usuario>)
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUnUsuario(String dni) throws Exception {
        return usuarioRepository.findById(dni)
                .orElseThrow(() -> new Exception("usuario no encontrado"));
    }

    @Override
    public Usuario buscarUnUsuarioPorNombre(String nombre) {
        throw new UnsupportedOperationException("Unimplemented method 'buscarUnUsuarioPorNombre'");
    }

    @Override
    public Usuario crearNuevoUsuario() {
        return new Usuario();   
    }

    @Override
    public List<Usuario> listarTodosUsuariosActivos() {
        return usuarioRepository.findByEstado(true);
    }
}
