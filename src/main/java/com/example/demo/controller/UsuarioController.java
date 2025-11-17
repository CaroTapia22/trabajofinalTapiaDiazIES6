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

    @GetMapping("/usuario")
    public ModelAndView getUsuario() {
        ModelAndView carrito = new ModelAndView("usuario");

        
        carrito.addObject("nuevoUsuario", UsuarioService.crearNuevoUsuario());
        carrito.addObject("band", false);
        return carrito;
    }

    @PostMapping("/guardarUsuario")
    public ModelAndView saveUsuario(@Valid @ModelAttribute("nuevoUsuario") Usuario usarioParaGuardar,
            BindingResult result) {
        System.out.println("estoy ingresando al metodo de guardar");
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("usuario");
            modelAndView.addObject("nuevoUsuario", usarioParaGuardar);
        } else {
            try {
                UsuarioService.agregarUsuario(usarioParaGuardar);
                modelAndView.setViewName("listaUsuarios");
                modelAndView.addObject("correcto", "Usuario registrado con éxito");
            } catch (Exception e) {
                modelAndView.addObject("errorUsuario", "Error al guardar el usuario: " + e.getMessage());
            }
            modelAndView.addObject("lista", UsuarioService.listarTodosUsuariosActivos());
            System.out.println("estoy saliendo al metodo de guardar");
        }

        return modelAndView;
    }

    @GetMapping("/eliminarUsuario/{dni}")
    public ModelAndView eliminarUsuario(@PathVariable(name = "dni") String dni) throws Exception {
        ModelAndView carritoDeEliminar = new ModelAndView("listaUsuarios");
        UsuarioService.borrarUsuario(dni);
        carritoDeEliminar.addObject("lista", UsuarioService.listarTodosUsuariosActivos());

        return carritoDeEliminar;
    }

    @GetMapping("/modificarUsuario/{dni}")
    public ModelAndView buscarUsuarioParaModificar(@PathVariable(name = "dni") String dni) throws Exception {
        ModelAndView carritoParaModificarUsuario = new ModelAndView("usuario");
        carritoParaModificarUsuario.addObject("nuevoUsuario", UsuarioService.buscarUnUsuario(dni));
        carritoParaModificarUsuario.addObject("band", true);
        return carritoParaModificarUsuario;
    }

    @PostMapping("/modificarUsuario")
    public ModelAndView modificarEstudiante(@ModelAttribute("nuevoUsuario") Usuario usuarioModificado) {
        ModelAndView listadoEditado = new ModelAndView("listaUsuarios");
        UsuarioService.agregarUsuario(usuarioModificado);
        listadoEditado.addObject("lista", UsuarioService.listarTodosUsuariosActivos());

        return listadoEditado;
    }

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
