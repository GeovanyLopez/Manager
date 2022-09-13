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
import org.springframework.web.bind.annotation.PathVariable;
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
        Long sumaMonto = movimientosServicios.obtenerSumaMonto();
        model.addAttribute("sumaMonto",sumaMonto);
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
    @PostMapping("/GuardarMovimiento")
    public String guardarEmpleado(MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if(movimientosServicios.saveOrUpdateMovimiento(mov)){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerMovimientos";// Se redirecciona al servicio
        }
        redirectAttributes.addFlashAttribute("mensaje","saveERROR");
        return "redirect:/AgregarMovimiento";
    }

    @GetMapping("/EditarMovimiento/{id}")
    public String editarMovimiento(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero mov = movimientosServicios.getMovimientoById(id);
        model.addAttribute("mov",mov);
        model.addAttribute("mensaje",mensaje);
        List<Empleado> listaEmpleados =empleadoServicios.getAllEmpleado();
        model.addAttribute("emplelist",listaEmpleados);
        return "editarMovimiento";
    }

    @PostMapping("/ActualizarMovimiento")
    public String updateMovimiento(@ModelAttribute("mov") MovimientoDinero mov,RedirectAttributes redirectAttributes){
        if(movimientosServicios.saveOrUpdateMovimiento(mov) == true){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerMovimientos";// Se redirecciona al servicio
        }
        return "redirect:/EditarMovimientos"+ mov.getId();
    }
    @GetMapping("/EliminarMovimiento/{id}")
    public String eliminarMovimiento(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if(movimientosServicios.deleteMovimiento(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","deleteError");
        return "redirect:/VerMovimientos";
    }

    // Buscar movimientos por empleados
    @GetMapping("/Empleado/{id}/Movimientos")
    public String movimientoPorEmpleado(@PathVariable("id") Integer id, Model model){
        List<MovimientoDinero> movlist = movimientosServicios.obtenerPorEmpleado(id);
        model.addAttribute("movlist",movlist);
        Long sumaMonto = movimientosServicios.montoPorEmpleado(id);
        model.addAttribute("sumaMonto",sumaMonto);
        return "verMovimientos";
    }
    // Buscar movimientos por empresa
    @GetMapping("/Empresa/{id}/Movimientos")
    public String movimientoPorEmpresa(@PathVariable("id") Integer id, Model model){
        List<MovimientoDinero> movlist = movimientosServicios.obtenerPorEmpresa(id);
        model.addAttribute("movlist",movlist);
        Long sumaMonto = movimientosServicios.montoPorEmpresa(id);
        model.addAttribute("sumaMonto",sumaMonto);
        return "verMovimientos";
    }

}
