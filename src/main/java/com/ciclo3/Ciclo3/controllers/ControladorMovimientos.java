package com.ciclo3.Ciclo3.controllers;

import com.ciclo3.Ciclo3.modelos.Empleado;
import com.ciclo3.Ciclo3.modelos.MovimientoDinero;
import com.ciclo3.Ciclo3.repositorio.MovimientoRepositorio;
import com.ciclo3.Ciclo3.sevicios.MovimientosServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControladorMovimientos {

    @Autowired
    MovimientosServicios movimientosServicios;

    // /enterprises/[id]/movements
    @GetMapping("/movements")
    public List<MovimientoDinero> verTodosMovimientos() {
        return movimientosServicios.obtenerTodosMovimientos();
    }

    @PostMapping("/movements")
    public MovimientoDinero guardarMovimiento(@RequestBody MovimientoDinero movimiento) {
        return this.movimientosServicios.saveUpdateMovimiento(movimiento);
    }

    @GetMapping("/movements/{id}")
    public MovimientoDinero movimientoPorId(@PathVariable("id") Integer id) {
        return movimientosServicios.obtenerMovimientoPorId(id);
    }

    @PatchMapping("/movements/{id}")
    public MovimientoDinero actualizarMovimiento(@PathVariable("id") Integer id, @RequestBody MovimientoDinero movimiento) {
        MovimientoDinero movi = movimientosServicios.obtenerMovimientoPorId(id);
        movi.setMonto(movimiento.getMonto());
        movi.setConcepto(movimiento.getConcepto());
        movi.setUsuario(movimiento.getUsuario());

        return movimientosServicios.saveUpdateMovimiento(movi);
    }

    @DeleteMapping("/movements/{id}")
    public String eliminarMovimiento(@PathVariable("id") Integer id){
        boolean respuesta = movimientosServicios.eliminarMovimiento(id);
        if(respuesta){
            return "Movimiento  ha sido eliminado " + id;
        }
        return "Movimiento  NO ha sido eliminado " + id;
    }

    @GetMapping("/users/{id}/movements")
    public ArrayList<MovimientoDinero> movimientoPorEmpleado(@PathVariable("id") Integer id){
        return movimientosServicios.obtenerPorEmpleado(id);
    }

    @GetMapping("/enterprises/{id}/movements")
    public ArrayList<MovimientoDinero> movimientoPorEmpresa(@PathVariable("id") Integer id){
        return movimientosServicios.obtenerPorEmpresa(id);
    }
}
