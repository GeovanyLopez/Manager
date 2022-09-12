package com.ciclo3.Ciclo3.controllers;

import com.ciclo3.Ciclo3.modelos.Empleado;
import com.ciclo3.Ciclo3.modelos.Empresa;
import com.ciclo3.Ciclo3.sevicios.EmpleadoServicios;
import com.ciclo3.Ciclo3.sevicios.EmpresaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class ControladorEmpresa {
    @Autowired
    EmpresaServicios empresaServicios;
    @Autowired
    EmpleadoServicios empleadosServisios;

    @GetMapping({"/", "/VerEmpresas"})
    public String viewEmpresas(Model model,@ModelAttribute("mensaje") String mensaje) {
        List<Empresa> listaEmpresas = empresaServicios.listarEmpresas();
        model.addAttribute("empresaList", listaEmpresas);
        model.addAttribute("mensaje",mensaje);
        return "verEmpresas";// verEmpresas es un archivo html
    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model,@ModelAttribute("mensaje") String mensaje){
        Empresa emp = new Empresa();
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje",mensaje);
        return "agregarEmpresa";

    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes){
        if(empresaServicios.saveUpdate(emp) == true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerEmpresas";// Se redirecciona al servicio
        }
        redirectAttributes.addFlashAttribute("mensaje","saveERROR");
        return "redirect:/AgregarEmpresa";
    }

    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id,@ModelAttribute("mensaje") String mensaje){
        Empresa emp = empresaServicios.obtenerEmpresaID(id);
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje",mensaje);
        return "editarEmpresa";
    }

    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa(@ModelAttribute("emp") Empresa emp,RedirectAttributes redirectAttributes){
        if(empresaServicios.saveUpdate(emp) == true){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerEmpresas";// Se redirecciona al servicio
        }
        return "redirect:/EditarEmpresa"+ emp.getId();
    }

    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if(empresaServicios.eliminarEmpresa(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","deleteError");
        return "redirect:/VerEmpresas";
    }

    // buscar empleados por empresa
    @GetMapping("/Empresa/{id}/Empleados")
    public String verEmpleadosPorEmpresa(@PathVariable("id") Integer id, Model model){
        List<Empleado> listaEmpleados = empleadosServisios.obtenerPorEmpresa(id);
        model.addAttribute("emplelist",listaEmpleados);
        return "verEmpleados";
    }
}
