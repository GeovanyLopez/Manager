package com.ciclo3.Ciclo3.controllers;

import com.ciclo3.Ciclo3.modelos.Empresa;
import com.ciclo3.Ciclo3.sevicios.EmpresaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller

public class Controlador {
    @Autowired
    EmpresaServicios empresaServicios;

    @GetMapping({"/Empresas", "/VerEmpresas"})
    public String viewEmpresas(Model model) {
        List<Empresa> listaEmpresas = empresaServicios.listarEmpresas();
        model.addAttribute("empresaList", listaEmpresas);
        return "verEmpresas";// verEmpresas es un archivo html
    }

}
