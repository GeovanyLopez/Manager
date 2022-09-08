package com.ciclo3.Ciclo3.repositorio;

import com.ciclo3.Ciclo3.modelos.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovimientoRepositorio extends JpaRepository<MovimientoDinero, Integer> {

    // Metodo para buscar movimientos por empleado
    @Query(value ="SELECT * FROM movimientos WHERE empleado_id =?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> encontrarPorEmpleado(Integer id);

    // Metodo para buscar movimientos por empresa
    @Query(value= "SELECT * FROM movimientos WHERE empleado_id in(SELECT id FROM empleado WHERE empresa_id= ?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> encontrarPorEmpresas(Integer id);
}
