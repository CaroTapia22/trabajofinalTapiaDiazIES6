package com.example.demo.service;

//diferentes importaciones
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.model.Usuario;

@Service
public interface UsuarioService {
    //declaración de metodos
    //nominativos solo nombre
    
    public void borrarUsuario(String dni) throws Exception;
    public void agregarUsuario(Usuario usuario);
    public void modificarUsuario(Usuario usuario);
    public List<Usuario> listarTodosUsuario();
    public Usuario buscarUnUsuario(String dni) throws Exception;
    public Usuario buscarUnUsuarioPorNombre(String nombre);
    public Usuario crearNuevoUsuario();
    public List<Usuario> listarTodosUsuariosActivos();




}
