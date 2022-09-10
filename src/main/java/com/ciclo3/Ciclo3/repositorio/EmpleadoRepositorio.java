package com.ciclo3.Ciclo3.repositorio;

import com.ciclo3.Ciclo3.modelos.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Repository
public interface EmpleadoRepositorio extends CrudRepository<Empleado,Integer> {
    @Query(value="SELECT * FROM empleado WHERE empresa_id = ?1", nativeQuery = true)
    public ArrayList<Empleado>findByEmpresa(Integer id);

}
