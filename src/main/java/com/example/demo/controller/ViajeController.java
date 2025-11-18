package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Usuario;
import com.example.demo.model.Vehiculo;
import com.example.demo.model.Viaje;
import com.example.demo.model.TipoViaje;
import com.example.demo.model.TipoVehiculo;
import com.example.demo.service.UsuarioServiceI;
import com.example.demo.service.VehiculoServiceI;
import com.example.demo.service.ViajeServiceI;

@Controller
public class ViajeController {

    @Autowired
    private UsuarioServiceI usuarioService;

    @Autowired
    private VehiculoServiceI vehiculoService;

    @Autowired
    private ViajeServiceI viajeService;

    //INICIAR FLUJO: SELECCIONAR USUARIO
    @GetMapping("/iniciarViaje")
    public ModelAndView seleccionarUsuarioParaViaje() {

        ModelAndView carritoSeleccionUsuario = new ModelAndView("seleccionarUsuarioViaje");
        carritoSeleccionUsuario.addObject("listaUsuarios", usuarioService.listarTodosUsuariosActivos());

        return carritoSeleccionUsuario;
    }

    // LUEGO DE ELEGIR USUARIO → MOSTRAR VEHÍCULOS DISPONIBLES
    @GetMapping("/seleccionarUsuarioParaViaje")
    public ModelAndView mostrarVehiculosParaUsuario(@RequestParam("dni") String dniUsuario) throws Exception {

        Usuario usuarioSeleccionado = usuarioService.buscarUnUsuario(dniUsuario);

        ModelAndView carritoVehiculosParaViaje = new ModelAndView("vehiculosParaViaje");
        carritoVehiculosParaViaje.addObject("usuarioSeleccionado", usuarioSeleccionado);
        carritoVehiculosParaViaje.addObject("listaVehiculos", vehiculoService.listarTodosVehiculosActivos());

        return carritoVehiculosParaViaje;
    }

    // DESPUES DE ELEGIR VEHICULO EL FORMULARIO DE VIAJE
    @GetMapping("/nuevoViaje")
    public ModelAndView nuevoViaje(@RequestParam("dniUsuario") String dniUsuario,
                                   @RequestParam("patente") String patenteVehiculo) throws Exception {

        Usuario usuarioSeleccionado = usuarioService.buscarUnUsuario(dniUsuario);
        Vehiculo vehiculoSeleccionado = vehiculoService.buscarVehiculo(patenteVehiculo);

        Viaje viajeNuevo = viajeService.crearNuevoViaje();
        viajeNuevo.setUsuario(usuarioSeleccionado);
        viajeNuevo.setVehiculo(vehiculoSeleccionado);

        ModelAndView carritoNuevoViaje = new ModelAndView("formularioViaje");
        carritoNuevoViaje.addObject("nuevoViaje", viajeNuevo);
        carritoNuevoViaje.addObject("usuarioSeleccionado", usuarioSeleccionado);
        carritoNuevoViaje.addObject("vehiculoSeleccionado", vehiculoSeleccionado);
        carritoNuevoViaje.addObject("tiposViaje", TipoViaje.values());

        // datos ocultos para volver a setear usuario/vehiculo en el POST
        carritoNuevoViaje.addObject("dniUsuario", dniUsuario);
        carritoNuevoViaje.addObject("patenteVehiculo", patenteVehiculo);

        return carritoNuevoViaje;
    }

    // GUARDAR VIAJE (CALCULA COSTO)
    @PostMapping("/guardarViaje")
    public ModelAndView guardarViaje(
            @ModelAttribute("nuevoViaje") Viaje viajeParaGuardar,
            @RequestParam("dniUsuario") String dniUsuario,
            @RequestParam("patenteVehiculo") String patenteVehiculo) throws Exception {

        Usuario usuario = usuarioService.buscarUnUsuario(dniUsuario);
        Vehiculo vehiculo = vehiculoService.buscarVehiculo(patenteVehiculo);

        viajeParaGuardar.setUsuario(usuario);
        viajeParaGuardar.setVehiculo(vehiculo);

        // CALCULO DEL COSTO SEGUN TIPO VIAJE + TIPO VEHICULO
        Double costo = calcularCosto(viajeParaGuardar.getTipoViaje(), vehiculo.getTipo());
        viajeParaGuardar.setCostoFinal(costo);

        viajeService.agregarViaje(viajeParaGuardar);

        ModelAndView detalleViaje = new ModelAndView("detalleViaje");
        detalleViaje.addObject("viaje", viajeParaGuardar);
        detalleViaje.addObject("usuario", usuario);
        detalleViaje.addObject("vehiculo", vehiculo);
        detalleViaje.addObject("conductor", vehiculo.getConductor());

        return detalleViaje;
    }

    // LISTAR VIAJES
    @GetMapping("/listarViajes")
    public ModelAndView listarViajes() {

        ModelAndView carritoListaViajes = new ModelAndView("listaViajes");
        carritoListaViajes.addObject("listaViajes", viajeService.listarViajesActivos());

        return carritoListaViajes;
    }

    // BORRADO LOGICO DE VIAJE
    @GetMapping("/borrarViaje")
    public ModelAndView borrarViaje(@RequestParam("id") Integer id) throws Exception {

        viajeService.borrarViaje(id);
        ModelAndView listado = new ModelAndView("redirect:/listarViajes");

        return listado;
    }

    // METODO AUXILIAR PARA CALCULAR EL COSTO
    private Double calcularCosto(TipoViaje tipoViaje, TipoVehiculo tipoVehiculo) {

        double base = 0.0;

        // precios base para vehículos tipo X
        switch (tipoViaje) {
            case CORTA:
            case MEDIA:
                base = 7000;
                break;
            case LARGA:
                base = 20000;
                break;
        }

        // recargos según tipo de vehículo
        if (tipoVehiculo == TipoVehiculo.LUXE) {
            base = base * 1.10;   // 10% más
        } else if (tipoVehiculo == TipoVehiculo.PREMIUM) {
            base = base * 1.20;   // 20% más
        }
        // si es X, queda igual

        return base;
    }
}