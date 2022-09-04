package com.ciclo3.Ciclo3.controllers;

import com.ciclo3.Ciclo3.modelos.Empresa;
import com.ciclo3.Ciclo3.sevicios.EmpresaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class Controlador {
    @Autowired
    EmpresaServicios empresaServicios;

    @GetMapping({"/", "/VerEmpresas"})
    public String viewEmpresas(Model model) {
        List<Empresa> listaEmpresas = empresaServicios.listarEmpresas();
        model.addAttribute("empresaList", listaEmpresas);
        return "verEmpresas";// verEmpresas es un archivo html
    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model){
        Empresa emp = new Empresa();
        model.addAttribute("emp",emp);
        return "agregarEmpresa";

    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes){
        if(empresaServicios.saveUpdate(emp) == true){
            return "redirect:/VerEmpresas";// Se redirecciona al servicio
        }
        return "redirect:/AgregarEmpresa";
    }

    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id){
        Empresa emp = empresaServicios.obtenerEmpresaID(id);
        model.addAttribute("emp",emp);
        return "editarEmpresa";
    }

    @PostMapping("/ActualizarEmpresa")
    public String guardarEmpresa(Empresa emp){
        if(empresaServicios.saveUpdate(emp) == true){
            return "redirect:/VerEmpresas";// Se redirecciona al servicio
        }
        return "redirect:/EditarEmpresa";
    }

    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable Integer id){
       try{
           empresaServicios.eliminarEmpresa(id);
       }catch(Exception e){
           return "redirect:/VerEmpresas";
       }
       return "redirect:/VerEmpresas";
    }
}
