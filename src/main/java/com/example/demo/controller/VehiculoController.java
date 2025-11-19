package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Vehiculo;
import com.example.demo.model.Conductor;
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
    public ModelAndView listarVehiculos() {

        ModelAndView carritoParaMostrarVehiculos = new ModelAndView("listaVehiculos");
        carritoParaMostrarVehiculos.addObject("listaVehiculos", vehiculoService.listarTodosVehiculos());

        return carritoParaMostrarVehiculos;
    }

    // FORMULARIO NUEVO
    @GetMapping("/nuevoVehiculo")
    public ModelAndView nuevoVehiculo() {

        Vehiculo vehiculo = vehiculoService.crearNuevoVehiculo();

        ModelAndView carritoNuevoVehiculo = new ModelAndView("formularioVehiculo");
        carritoNuevoVehiculo.addObject("nuevoVehiculo", vehiculo);
        carritoNuevoVehiculo.addObject("band", false);  // en falso para crear
        carritoNuevoVehiculo.addObject("tipos", TipoVehiculo.values());
        carritoNuevoVehiculo.addObject("conductores", conductorService.listarTodosConductoresActivos());

        return carritoNuevoVehiculo;
    }


    // EDITAR
    @GetMapping("/editarVehiculo")
    public ModelAndView editarVehiculo(@RequestParam("patente") String patente) throws Exception {

        Vehiculo vehiculoEncontrado = vehiculoService.buscarVehiculo(patente);

        ModelAndView carritoParaEditarVehiculo = new ModelAndView("formularioVehiculo");
        carritoParaEditarVehiculo.addObject("nuevoVehiculo", vehiculoEncontrado);
        carritoParaEditarVehiculo.addObject("band", true); // para editar en true
        carritoParaEditarVehiculo.addObject("tipos", TipoVehiculo.values());
        carritoParaEditarVehiculo.addObject("conductores", conductorService.listarTodosConductoresActivos());

        return carritoParaEditarVehiculo;
    }

    // GUARDAR NUEVO
@PostMapping("/guardarVehiculo")
public ModelAndView guardarVehiculo(@ModelAttribute("nuevoVehiculo") Vehiculo vehiculo) throws Exception {

    // Resolver el conductor por DNI (si viene alguno seleccionado)
    if (vehiculo.getConductor() != null && vehiculo.getConductor().getDni() != null) {
        Conductor conductor = conductorService.buscarConductor(vehiculo.getConductor().getDni());
        vehiculo.setConductor(conductor);
    } else {
        vehiculo.setConductor(null);
    }

    vehiculoService.agregarVehiculo(vehiculo);

    ModelAndView mav = new ModelAndView();
    mav.setViewName("redirect:/listarVehiculos");
    return mav;
}

   // MODIFICAR
   @PostMapping("/modificarVehiculo")
   public ModelAndView modificarVehiculo(@ModelAttribute("nuevoVehiculo") Vehiculo vehiculo) throws Exception {

    if (vehiculo.getConductor() != null && vehiculo.getConductor().getDni() != null) {
        Conductor conductor = conductorService.buscarConductor(vehiculo.getConductor().getDni());
        vehiculo.setConductor(conductor);
    } else {
        vehiculo.setConductor(null);
    }

    vehiculoService.modificarVehiculo(vehiculo);

    ModelAndView mav = new ModelAndView();
    mav.setViewName("redirect:/listarVehiculos");
    return mav;
}


    // BORRADO LOGICO
    @GetMapping("/borrarVehiculo")
    public ModelAndView borrarVehiculo(@RequestParam("patente") String patente) throws Exception {

        vehiculoService.borrarVehiculo(patente);

        ModelAndView listadoVehiculos = new ModelAndView("redirect:/listarVehiculos");
        return listadoVehiculos;
    }

    // Lista solo los vehiculos activos disponibles para viaje
    @GetMapping("/vehiculosDisponibles")
    public ModelAndView vehiculosDisponibles() {

        ModelAndView carritoVehiculosDisponibles = new ModelAndView("vehiculosDisponibles");
        carritoVehiculosDisponibles.addObject("listaVehiculos", vehiculoService.listarTodosVehiculosActivos());

        return carritoVehiculosDisponibles;
    }

}
