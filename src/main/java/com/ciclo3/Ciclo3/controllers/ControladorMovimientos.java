package com.ciclo3.Ciclo3.controllers;

import com.ciclo3.Ciclo3.modelos.Empleado;
import com.ciclo3.Ciclo3.modelos.MovimientoDinero;
import com.ciclo3.Ciclo3.repositorio.MovimientoRepositorio;
import com.ciclo3.Ciclo3.sevicios.MovimientosServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControladorMovimientos {

    @Autowired
    MovimientosServicios movimientosServicios;

    // /enterprises/[id]/movements
    @GetMapping("/movements")
    public List<MovimientoDinero> verTodosMovimientos(){
        return movimientosServicios.obtenerTodosMovimientos();
    }

    @PostMapping("/movements")
    public MovimientoDinero guardarMovimiento(@RequestBody MovimientoDinero movimiento){
        return this.movimientosServicios.saveUpdateMovimiento(movimiento);
    }

    @GetMapping("/movements/{id}")
    public MovimientoDinero movimientoPorId(@PathVariable("id") Integer id){
        return movimientosServicios.obtenerMovimientoPorId(id);
    }

    @PatchMapping("/movements/{id}")
    public MovimientoDinero actualizarEmpleado(@PathVariable("id") Integer id,@RequestBody MovimientoDinero movimiento){
        MovimientoDinero movi = movimientosServicios.obtenerMovimientoPorId(id);
        movi.setMonto(movimiento.getMonto());
        movi.setConcepto(movimiento.getConcepto());
        movi.setUsuario(movimiento.getUsuario());

        return movimientosServicios.saveUpdateMovimiento(movi);
    }

}
