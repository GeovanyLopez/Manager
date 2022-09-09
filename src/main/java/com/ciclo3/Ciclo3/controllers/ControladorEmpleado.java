package com.ciclo3.Ciclo3.controllers;

import com.ciclo3.Ciclo3.modelos.Empleado;
import com.ciclo3.Ciclo3.sevicios.EmpleadoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ControladorEmpleado {

    @Autowired
    EmpleadoServicios empleadoServicios;

    @GetMapping("/users")
    public List<Empleado> verEmleados(){
        return empleadoServicios.obtenerTodosEmpleados();
    }
    @PostMapping("/users")
    public Empleado guardarEmpleado(@RequestBody Empleado empl){
        return this.empleadoServicios.saveUpdateEmpleado(empl );
    }

    @GetMapping("/users/{id}")
    public Optional<Empleado> empleadoPorId(@PathVariable("id") Integer id){
        return this.empleadoServicios.buscarPorId(id);
    }

    @GetMapping("/enterprises/{id}/users")
    public ArrayList<Empleado> empleadoPorEmpresa(@PathVariable("id") Integer id){
        return empleadoServicios.obtenerPorEmpresa(id);
    }

    @PatchMapping("/users/{id}")
    public Empleado actualizarEmpleado(@PathVariable("id") Integer id,@RequestBody Empleado empleado){
        Empleado empl = empleadoServicios.buscarPorId(id).get();
        empl.setNombreEmpleado(empleado.getNombreEmpleado());
        empl.setCorreo(empleado.getCorreo());
        empl.setEmpresa(empleado.getEmpresa());
        empl.setRol(empleado.getRol());

        return empleadoServicios.saveUpdateEmpleado(empl);
    }

    @DeleteMapping("/users/{id}")
    public String eliminarEmpleado(@PathVariable("id") Integer id){
        boolean respuesta = this.empleadoServicios.eliminarEmpleado(id);
        if(respuesta){
            return "Se ha eliminado el empleado con id " + id;
        }
        else{
            return "NO se pudo eliminar el empleado con id " + id;
        }
    }
}
