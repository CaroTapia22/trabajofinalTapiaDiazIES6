package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Conductor;
import com.example.demo.service.ConductorServiceI;

@Controller
public class ConductorController {

    @Autowired
    ConductorServiceI conductorService;

    //lita de todos los conductores
    @GetMapping("/listarConductores")
    public String listarConductores(Model model) {

        model.addAttribute("listaConductores", conductorService.listarTodosConductores());

        return "listaConductores";
    }

    // formulario para conductor nuevo
    @GetMapping("/nuevoConductor")
    public String nuevoConductor(Model model) {

        Conductor conductor = conductorService.crearNuevoConductor();

        model.addAttribute("nuevoConductor", conductor);
        model.addAttribute("band", false);  // false para crear nuevo conductor

        return "formularioConductor";
    }

    // guarda conductor nuevo
    @PostMapping("/guardarConductor")
    public String guardarConductor(Conductor conductor) {

        conductorService.agregarConductor(conductor);
        return "redirect:/listarConductores";
    }

    // editar
    @GetMapping("/editarConductor")
    public String editarConductor(@RequestParam("dni") Integer dni, Model model) throws Exception {

        Conductor conductorEncontrado = conductorService.buscarConductor(dni);

        model.addAttribute("nuevoConductor", conductorEncontrado);
        model.addAttribute("band", true); // true en edición

        return "formularioConductor";
    }

    @PostMapping("/modificarConductor")
    public String modificarConductor(Conductor conductor) {

        conductorService.modificarConductor(conductor);
        return "redirect:/listarConductores";
    }

    // BORRADO LOGICO
    @GetMapping("/borrarConductor")
    public String borrarConductor(@RequestParam("dni") Integer dni) throws Exception {

        conductorService.borrarConductor(dni);
        return "redirect:/listarConductores";
    }
}
