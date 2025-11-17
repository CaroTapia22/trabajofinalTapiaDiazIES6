package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import jakarta.validation.Valid;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

@Qualifier("servicioUsuarioMySQL")
@Controller
public class UsuarioController {

    @Autowired
    UsuarioService UsuarioService;

    // Mostrar formulario vacío
    @GetMapping("/usuario")
    public ModelAndView getUsuario() {
        ModelAndView carrito = new ModelAndView("usuario");

        carrito.addObject("nuevoUsuario", UsuarioService.crearNuevoUsuario());
        carrito.addObject("band", false); // modo crear

        return carrito;
    }

    // Guardar usuario nuevo
    @PostMapping("/guardarUsuario")
    public ModelAndView saveUsuario(
            @Valid @ModelAttribute("nuevoUsuario") Usuario usuarioParaGuardar,
            BindingResult result) {

        ModelAndView modelAndView = new ModelAndView();

        System.out.println("estoy ingresando al metodo de guardar");

        if (result.hasErrors()) {
            modelAndView.setViewName("usuario");
            modelAndView.addObject("nuevoUsuario", usuarioParaGuardar);
            modelAndView.addObject("band", false); // modo creación
            return modelAndView;
        }

        try {
            UsuarioService.agregarUsuario(usuarioParaGuardar);
            modelAndView.setViewName("listaUsuarios");
            modelAndView.addObject("correcto", "Usuario registrado con éxito");
        } catch (Exception e) {
            modelAndView.setViewName("usuario");
            modelAndView.addObject("errorUsuario",
                    "Error al guardar el usuario: " + e.getMessage());
            modelAndView.addObject("nuevoUsuario", usuarioParaGuardar);
            modelAndView.addObject("band", false);
            return modelAndView;
        }

        modelAndView.addObject("lista", UsuarioService.listarTodosUsuariosActivos());
        System.out.println("estoy saliendo al metodo de guardar");

        return modelAndView;
    }

    // Eliminar usuario
    @GetMapping("/eliminarUsuario/{dni}")
    public ModelAndView eliminarUsuario(@PathVariable(name = "dni") String dni) throws Exception {
        ModelAndView carritoDeEliminar = new ModelAndView("listaUsuarios");
        UsuarioService.borrarUsuario(dni);
        carritoDeEliminar.addObject("lista", UsuarioService.listarTodosUsuariosActivos());

        return carritoDeEliminar;
    }

    // Buscar usuario para modificar
    @GetMapping("/modificarUsuario/{dni}")
    public ModelAndView buscarUsuarioParaModificar(@PathVariable(name = "dni") String dni) throws Exception {
        ModelAndView carritoParaModificarUsuario = new ModelAndView("usuario");
        carritoParaModificarUsuario.addObject("nuevoUsuario", UsuarioService.buscarUnUsuario(dni));
        carritoParaModificarUsuario.addObject("band", true); // modo edición

        return carritoParaModificarUsuario;
    }

    // Modificar usuario
    @PostMapping("/modificarUsuario")
    public ModelAndView modificarUsuario(
            @Valid @ModelAttribute("nuevoUsuario") Usuario usuarioModificado,
            BindingResult result) {

        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.setViewName("usuario");
            modelAndView.addObject("nuevoUsuario", usuarioModificado);
            modelAndView.addObject("band", true); // modo edición
            return modelAndView;
        }

        UsuarioService.agregarUsuario(usuarioModificado);
        modelAndView.setViewName("listaUsuarios");
        modelAndView.addObject("lista", UsuarioService.listarTodosUsuariosActivos());

        return modelAndView;
    }

    // Lista completa
    @GetMapping("/listarUsuarios")
    public ModelAndView listarUsuariosActivos() {
        ModelAndView carritoParaMostrarUsuarios = new ModelAndView("listaUsuarios");
        carritoParaMostrarUsuarios.addObject("lista", UsuarioService.listarTodosUsuariosActivos());

        return carritoParaMostrarUsuarios;
    }

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }
}
