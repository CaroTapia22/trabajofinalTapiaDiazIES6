package com.example.demo.service;

//diferentes importaciones
import java.util.List;
import com.example.demo.model.Usuario;

public interface UsuarioServiceI {
    //declaración de metodos
    //nominativos solo nombre
    
    public void borrarUsuario(String dni) throws Exception;
    public void agregarUsuario(Usuario usuario);
    public void modificarUsuario(Usuario usuario);
    public List<Usuario> listarTodosUsuarios();
    public Usuario buscarUnUsuario(String dni) throws Exception;
    public Usuario buscarUnUsuarioPorNombre(String nombre);
    public Usuario crearNuevoUsuario();
    public List<Usuario> listarTodosUsuariosActivos();




}
