package com.ciclo3.Ciclo3.sevicios;

import com.ciclo3.Ciclo3.modelos.Empleado;
import com.ciclo3.Ciclo3.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicios {

    @Autowired
    EmpleadoRepositorio empleadoRepositorio;

    // Metodo para ver todos los empleados
    public List<Empleado> obtenerTodosEmpleados(){
        List<Empleado> empleadoList = new ArrayList<>();
        empleadoRepositorio.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;
    }

    // Metodo para buscar empleados por id
    public Optional<Empleado> buscarPorId(Integer id){
        return empleadoRepositorio.findById(id);
    }

    // Metodo para guardar o actualizar datos de empleado
    public Empleado saveUpdateEmpleado(Empleado empleado){
        return empleadoRepositorio.save(empleado);
    }

    // Metodo para eliminar un empleado
    public boolean eliminarEmpleado(Integer id){
        empleadoRepositorio.deleteById(id);
        if(this.empleadoRepositorio.findById(id).isPresent()){
            return false;
        }
        return true;
    }

    // Metodo para buscar por Empleado por empresa
    public ArrayList<Empleado> obtenerPorEmpresa(Integer id){
        return empleadoRepositorio.findByEmpresa(id);
    }

}
