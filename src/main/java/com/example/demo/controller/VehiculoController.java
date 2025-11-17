package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Vehiculo;
import com.example.demo.model.TipoVehiculo;
import com.example.demo.service.VehiculoServiceI;
import com.example.demo.service.ConductorServiceI;

@Controller
public class VehiculoController {

    @Autowired
    VehiculoServiceI vehiculoService;

    @Autowired
    ConductorServiceI conductorService;

    // LISTAR TODOS LOS VEHICULOS
    @GetMapping("/listarVehiculos")
    public String listarVehiculos(Model model) {

        model.addAttribute("listaVehiculos", vehiculoService.listarTodosVehiculos());

        return "listaVehiculos";
    }

    // FORMULARIO NUEVO
    @GetMapping("/nuevoVehiculo")
    public String nuevoVehiculo(Model model) {

        Vehiculo vehiculo = vehiculoService.crearNuevoVehiculo();

        model.addAttribute("nuevoVehiculo", vehiculo);
        model.addAttribute("band", false);  // en falso para crear
        model.addAttribute("tipos", TipoVehiculo.values());
        model.addAttribute("conductores", conductorService.listarTodosConductoresActivos());

        return "formularioVehiculo";
    }

    // GUARDAR NUEVO
    @PostMapping("/guardarVehiculo")
    public String guardarVehiculo(Vehiculo vehiculo) {

        vehiculoService.agregarVehiculo(vehiculo);
        return "redirect:/listarVehiculos";
    }

    // EDITAR
    @GetMapping("/editarVehiculo")
    public String editarVehiculo(@RequestParam("patente") String patente, Model model) throws Exception {

        Vehiculo vehiculoEncontrado = vehiculoService.buscarVehiculo(patente);

        model.addAttribute("nuevoVehiculo", vehiculoEncontrado);
        model.addAttribute("band", true); // epara editar en true
        model.addAttribute("tipos", TipoVehiculo.values());
        model.addAttribute("conductores", conductorService.listarTodosConductoresActivos());

        return "formularioVehiculo";
    }

    // MODIFICAR
    @PostMapping("/modificarVehiculo")
    public String modificarVehiculo(Vehiculo vehiculo) {

        vehiculoService.modificarVehiculo(vehiculo);
        return "redirect:/listarVehiculos";
    }

    // BORRADO LOGICO
    @GetMapping("/borrarVehiculo")
    public String borrarVehiculo(@RequestParam("patente") String patente) throws Exception {

        vehiculoService.borrarVehiculo(patente);
        return "redirect:/listarVehiculos";
    }
}

