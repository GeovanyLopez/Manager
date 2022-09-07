package com.ciclo3.Ciclo3.controllers;


import com.ciclo3.Ciclo3.modelos.Empresa;
import com.ciclo3.Ciclo3.sevicios.EmpresaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controlador {

    @Autowired
    EmpresaServicios empresaServicios;

    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas(){
        return empresaServicios.listarEmpresas();

    }

    @PostMapping("/enterprises") // Guarda una Empresa
    public Empresa guardarEmpresa(@RequestBody Empresa emp){
        return this.empresaServicios.saveUpdate(emp);
    }

    @GetMapping("/enterprises/{id}")
    public Empresa empresaPorId(@PathVariable("id") Integer id){
        return this.empresaServicios.obtenerEmpresaID(id);
    }

    @PatchMapping("enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id,@RequestBody Empresa empresa){
        Empresa emp = empresaServicios.obtenerEmpresaID(id);
        emp.setNombre(empresa.getNombre());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setNIT(empresa.getNIT());
        return empresaServicios.saveUpdate(emp);
    }

    @DeleteMapping("/enterprises/{id}")
    public String eliminarEmpresa(@PathVariable("id") Integer id){
        boolean respuesta = this.empresaServicios.eliminarEmpresa(id);
        if(respuesta){
            return "Se ha eliminado la empresa con id " + id;
        }
        else{
            return "NO se pudo eliminar la empresa con id " + id;
        }
    }


}
