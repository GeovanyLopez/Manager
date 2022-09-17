package com.ciclo3.Ciclo3.controllers;

import com.ciclo3.Ciclo3.modelos.Empleado;
import com.ciclo3.Ciclo3.modelos.Empresa;
import com.ciclo3.Ciclo3.repositorio.EmpleadoRepositorio;
import com.ciclo3.Ciclo3.sevicios.EmpleadoServicios;
import com.ciclo3.Ciclo3.sevicios.EmpresaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        return "agregarEmpleado";
    }

    @PostMapping("/GuardarEmpleado")
    public String guardarEmpleado(Empleado empl, RedirectAttributes redirectAttributes){
        //encriptar la contraseña
        String passwordEncrip = passwordEncoder().encode(empl.getPassword());
        empl.setPassword(passwordEncrip);
        if(empleadoServicios.saveOrUpdateEmpleado(empl)== true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerEmpleados";// Se redirecciona al servicio
        }
        redirectAttributes.addFlashAttribute("mensaje","saveERROR");
        return "redirect:/AgregarEmpleado";
    }

    @GetMapping("/EditarEmpleado/{id}")
    public String editarEmpleado(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empleado empl = empleadoServicios.getEmpleadoById(id).get();
        model.addAttribute("empl",empl);
        model.addAttribute("mensaje",mensaje);
        List<Empresa> listaEmpresas = empresaServicios.listarEmpresas();
        model.addAttribute("emprelist",listaEmpresas);
        return "editarEmpleado";
    }

    @PostMapping("/ActualizarEmpleado")
    public String updateEmpresa(@ModelAttribute("empl") Empleado empl,RedirectAttributes redirectAttributes){
        Integer id = empl.getId();
        String oldPass = empleadoServicios.getEmpleadoById(id).get().getPassword();
        if(!empl.getPassword().equals(oldPass)){
            //encriptar la contraseña
            String passwordEncrip = passwordEncoder().encode(empl.getPassword());
            empl.setPassword(passwordEncrip);
        }

        if(empleadoServicios.saveOrUpdateEmpleado(empl) == true){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerEmpleados";// Se redirecciona al servicio
        }
        return "redirect:/EditarEmpleados" + empl.getId();
    }

    @GetMapping("/EliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if(empleadoServicios.deleteEmpleado(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje","deleteError");
        return "redirect:/VerEmpleados";
    }

    // Metodo para encriptar la contraseña del empleado
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @RequestMapping(value="/Denegado")
    public String accesoDenegado(){

        return "accesoDenegado";
    }
}
