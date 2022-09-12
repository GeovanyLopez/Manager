package com.ciclo3.Ciclo3.controllers;

import com.ciclo3.Ciclo3.modelos.Empleado;
import com.ciclo3.Ciclo3.modelos.Empresa;
import com.ciclo3.Ciclo3.modelos.MovimientoDinero;
import com.ciclo3.Ciclo3.sevicios.EmpleadoServicios;
import com.ciclo3.Ciclo3.sevicios.EmpresaServicios;
import com.ciclo3.Ciclo3.sevicios.MovimientosServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControladorMovimientos {

    @Autowired
    MovimientosServicios movimientosServicios;
    @Autowired
    EmpleadoServicios empleadoServicios;
    @Autowired
    EmpresaServicios empresaServicios;

    @GetMapping("/VerMovimientos")
    public String viewMovimientos(Model model, @ModelAttribute("mensaje") String mensaje){
        List<MovimientoDinero> listaMovimientos=movimientosServicios.getAllMovimientos();
        model.addAttribute("movlist",listaMovimientos);
        model.addAttribute("mensaje",mensaje);
        return "verMovimientos"; //Llamamos al HTML
    }

    @GetMapping("/AgregarMovimiento")
    public String agregarNuevoMovimiento(Model model,@ModelAttribute("mensaje") String mensaje){
        MovimientoDinero movimiento = new MovimientoDinero();
        model.addAttribute("mov",movimiento);
        model.addAttribute("mensaje",mensaje);
        List<Empleado> listaEmpleados = empleadoServicios.getAllEmpleado();
        model.addAttribute("emplelist",listaEmpleados);
        return "agregarMovimiento";
    }

}
