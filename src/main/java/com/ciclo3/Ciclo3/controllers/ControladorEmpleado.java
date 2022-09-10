package com.ciclo3.Ciclo3.controllers;

import com.ciclo3.Ciclo3.modelos.Empleado;
import com.ciclo3.Ciclo3.modelos.Empresa;
import com.ciclo3.Ciclo3.repositorio.EmpleadoRepositorio;
import com.ciclo3.Ciclo3.sevicios.EmpleadoServicios;
import com.ciclo3.Ciclo3.sevicios.EmpresaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControladorEmpleado {

    @Autowired
    EmpleadoServicios empleadoServicios;
    @Autowired
    EmpresaServicios empresaServicios;

    @GetMapping("/VerEmpleados")
    public String viewEmpleados(Model model, @ModelAttribute("mensaje") String mensaje){
        List<Empleado> listaEmpleados=empleadoServicios.getAllEmpleado();
        model.addAttribute("emplelist",listaEmpleados);
        model.addAttribute("mensaje",mensaje);
        return "verEmpleados"; //Llamamos al HTML
    }

    @GetMapping("/AgregarEmpleado")
    public String agregarNuevoEmpleado(Model model,@ModelAttribute("mensaje") String mensaje){
        Empleado empl = new Empleado();
        model.addAttribute("empl",empl);
        model.addAttribute("mensaje",mensaje);
        List<Empresa> listaEmpresas = empresaServicios.listarEmpresas();
        model.addAttribute("emprelist",listaEmpresas);
        return "agregarEmpleado.html";
    }

    @PostMapping("/GuardarEmpleado")
    public String guardarEmpleado(Empresa empl, RedirectAttributes redirectAttributes){
        if(empleadoServicios.saveOrUpdateEmpleado(empl)== true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerEmpresas";// Se redirecciona al servicio
        }
        redirectAttributes.addFlashAttribute("mensaje","saveERROR");
        return "redirect:/AgregarEmpresa";
    }
}
