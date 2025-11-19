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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioServiceI;

import jakarta.validation.Valid;

@Controller
public class UsuarioController {

    @Qualifier("servicioUsuarioMySQL")
    @Autowired
    private UsuarioServiceI usuarioService;

   // FORMULARIO VACIO
@GetMapping({"/usuario", "/nuevoUsuario"})
public ModelAndView getUsuario() {
    ModelAndView carrito = new ModelAndView("usuario");
    carrito.addObject("nuevoUsuario", usuarioService.crearNuevoUsuario());
    carrito.addObject("band", false);
    return carrito;
}


    // GUARDAR USUARIO NUEVO
    @PostMapping("/guardarUsuario")
    public ModelAndView saveUsuario(
            @Valid @ModelAttribute("nuevoUsuario") Usuario usuarioParaGuardar,
            BindingResult result) {

        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.setViewName("usuario");
            modelAndView.addObject("nuevoUsuario", usuarioParaGuardar);
            modelAndView.addObject("band", false);  
            return modelAndView;
        }

        try {
            usuarioService.agregarUsuario(usuarioParaGuardar);
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

        modelAndView.addObject("lista", usuarioService.listarTodosUsuariosActivos());
        return modelAndView;
    }

    // ELIMINAR (BORRADO LÓGICO)
    @GetMapping("/eliminarUsuario/{dni}")
    public ModelAndView eliminarUsuario(@PathVariable(name = "dni") String dni) throws Exception {
        ModelAndView carritoDeEliminar = new ModelAndView("listaUsuarios");

        usuarioService.borrarUsuario(dni);
        carritoDeEliminar.addObject("lista", usuarioService.listarTodosUsuariosActivos());

        return carritoDeEliminar;
    }

    // BUSCAR USUARIO PARA MODIFICAR
    @GetMapping("/modificarUsuario/{dni}")
    public ModelAndView buscarUsuarioParaModificar(@PathVariable(name = "dni") String dni) throws Exception {
        ModelAndView carritoParaModificarUsuario = new ModelAndView("usuario");

        carritoParaModificarUsuario.addObject("nuevoUsuario", usuarioService.buscarUnUsuario(dni));
        carritoParaModificarUsuario.addObject("band", true);  

        return carritoParaModificarUsuario;
    }

    // MODIFICAR USUARIO
    @PostMapping("/modificarUsuario")
    public ModelAndView modificarUsuario(
            @Valid @ModelAttribute("nuevoUsuario") Usuario usuarioModificado,
            BindingResult result,
            RedirectAttributes redirectAttrs) {

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("usuario");
            mav.addObject("nuevoUsuario", usuarioModificado);
            mav.addObject("band", true);   
            return mav;
        }

        try {
            usuarioService.modificarUsuario(usuarioModificado);  
            redirectAttrs.addFlashAttribute("correcto", "Usuario modificado correctamente.");
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("errorUsuario",
                    "Error al modificar el usuario: " + e.getMessage());
        }

        ModelAndView listadoEditado = new ModelAndView("listaUsuarios");
        listadoEditado.addObject("lista", usuarioService.listarTodosUsuariosActivos());

        return listadoEditado;
    }

    // LISTAR USUARIOS ACTIVOS
    @GetMapping("/listarUsuarios")
    public ModelAndView listarUsuariosActivos() {
        ModelAndView carritoParaMostrarUsuarios = new ModelAndView("listaUsuarios");
        carritoParaMostrarUsuarios.addObject("lista", usuarioService.listarTodosUsuariosActivos());
        return carritoParaMostrarUsuarios;
    }

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }
}