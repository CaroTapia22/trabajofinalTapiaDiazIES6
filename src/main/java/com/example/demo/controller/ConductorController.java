package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Conductor;
import com.example.demo.service.ConductorServiceI;

import jakarta.validation.Valid;

@Controller
public class ConductorController {

    @Autowired
    private ConductorServiceI conductorService;

    // LISTA DE CONDUCTORES (solo activos)
    @GetMapping("/listarConductores")
    public ModelAndView listarConductores() {

    ModelAndView mav = new ModelAndView("listaConductores");
    mav.addObject("listaConductores", conductorService.listarTodosConductoresActivos());
    return mav;
    }
    

    // FORMULARIO NUEVO CONDUCTOR
    @GetMapping("/nuevoConductor")
    public ModelAndView nuevoConductor() {

        ModelAndView carritoNuevoConductor = new ModelAndView("formularioConductor");
        carritoNuevoConductor.addObject("nuevoConductor", conductorService.crearNuevoConductor());
        carritoNuevoConductor.addObject("band", false); 

        return carritoNuevoConductor;
    }

    // GUARDAR CONDUCTOR NUEVO
    @PostMapping("/guardarConductor")
    public ModelAndView guardarConductor(
            @Valid @ModelAttribute("nuevoConductor") Conductor conductor,
            BindingResult result) {

        ModelAndView carritoGuardar = new ModelAndView();

        if (result.hasErrors()) {
            carritoGuardar.setViewName("formularioConductor");
            carritoGuardar.addObject("nuevoConductor", conductor);
            carritoGuardar.addObject("band", false); 
            return carritoGuardar;
        }

        conductorService.agregarConductor(conductor);
        carritoGuardar.setViewName("redirect:/listarConductores");

        return carritoGuardar;
    }

    // BUSCAR CONDUCTOR PARA EDITAR
    @GetMapping("/editarConductor")
    public ModelAndView editarConductor(@RequestParam("dni") Integer dni) throws Exception {

        ModelAndView carritoParaEditarConductor = new ModelAndView("formularioConductor");
        carritoParaEditarConductor.addObject("nuevoConductor", conductorService.buscarConductor(dni));
        carritoParaEditarConductor.addObject("band", true); // modo editar

        return carritoParaEditarConductor;
    }

    // MODIFICAR CONDUCTOR
    @PostMapping("/modificarConductor")
    public ModelAndView modificarConductor(
            @Valid @ModelAttribute("nuevoConductor") Conductor conductor,
            BindingResult result) {

        ModelAndView carritoModificar = new ModelAndView();

        if (result.hasErrors()) {
            carritoModificar.setViewName("formularioConductor");
            carritoModificar.addObject("nuevoConductor", conductor);
            carritoModificar.addObject("band", true); // modo editar
            return carritoModificar;
        }

        conductorService.modificarConductor(conductor);
        carritoModificar.setViewName("redirect:/listarConductores");

        return carritoModificar;
    }

    // BORRADO LOGICO
    @GetMapping("/borrarConductor")
    public ModelAndView borrarConductor(@RequestParam("dni") Integer dni) throws Exception {

        conductorService.borrarConductor(dni);

        ModelAndView listadoConductores = new ModelAndView("redirect:/listarConductores");
        return listadoConductores;
    }
}


