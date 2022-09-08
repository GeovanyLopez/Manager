package com.ciclo3.Ciclo3.sevicios;

import com.ciclo3.Ciclo3.modelos.Empleado;
import com.ciclo3.Ciclo3.modelos.MovimientoDinero;
import com.ciclo3.Ciclo3.repositorio.MovimientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientosServicios {
    @Autowired
    MovimientoRepositorio movimientoRepositorio;

    // Metodo para ver todos los movimientos
    public List<MovimientoDinero> obtenerTodosMovimientos(){
        List<MovimientoDinero> movimientoList = new ArrayList<>();
        movimientoRepositorio.findAll().forEach(movimiento -> movimientoList.add(movimiento));
        return movimientoList;
    }

    // Metodo para ver movimiento por id
    public MovimientoDinero obtenerMovimientoPorId(Integer id){
        return movimientoRepositorio.findById(id).get();
    }

    // Metodo para guardar o actualizar datos de movimiento
    public MovimientoDinero saveUpdateMovimiento(MovimientoDinero movimientoDinero){
        return movimientoRepositorio.save(movimientoDinero);
    }

    // Metodo para eliminar un movimiento
    public boolean eliminarMovimiento(Integer id){
        movimientoRepositorio.deleteById(id);
        if(movimientoRepositorio.findById(id).isPresent()){
            return false;
        }
        return true;
    }

    // Metodo para buscar empleado que realizo el movimiento
    public ArrayList<MovimientoDinero> obtenerPorEmpleado(Integer id){
        return movimientoRepositorio.encontrarPorEmpleado(id);
    }

    // Metodo para buscar empresa que realizo el movimiento
    public ArrayList<MovimientoDinero> obtenerPorEmpresa(Integer id){
        return movimientoRepositorio.encontrarPorEmpresas(id);
    }
}
