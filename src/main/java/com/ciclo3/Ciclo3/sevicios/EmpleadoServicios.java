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

    //Metodo para ver todos los empleados registrados
    public List<Empleado> getAllEmpleado(){
        List<Empleado> empleadoList= new ArrayList<>();
        empleadoRepositorio.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;
    }

    //Metodo para buscar empleados por ID
    public Optional<Empleado> getEmpleadoById(Integer id){ //Existe optional y asi se podria usar
        return empleadoRepositorio.findById(id);
    }

    //Metodo para buscar empleados por empresa
    public ArrayList<Empleado> obtenerPorEmpresa(Integer id){
        return empleadoRepositorio.findByEmpresa(id);
    }

    //Metodo para guardar o actualizar registros en Empleados
    public boolean saveOrUpdateEmpleado(Empleado empl) {
        Empleado emp=empleadoRepositorio.save(empl);
        if (empleadoRepositorio.findById(emp.getId()) != null) {
            return true;
        }
        return false;
    }

    //Metodo para eliminar un registro de Empleado por Id
    public boolean deleteEmpleado(Integer id){
        empleadoRepositorio.deleteById(id);
        if(this.empleadoRepositorio.findById(id).isPresent()){
            return false;
        }
        return true;
    }
}
