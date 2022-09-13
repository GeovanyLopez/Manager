package com.ciclo3.Ciclo3.repositorio;

import com.ciclo3.Ciclo3.modelos.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface MovimientoRepositorio extends JpaRepository<MovimientoDinero,Integer> {
    // Metodo para buscar movimientos por empleado
    @Query(value ="select * from Movimientos where empleado_id= ?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpleado(Integer id);

    //Metodo para filtrar movimientos por empresa
    @Query(value="select * from Movimientos where empleado_id in (select id from empleado where empresa_id= ?1)", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpresa(Integer id);

    // Metodo para sumar todos los movimientos
    @Query(value = "SELECT SUM(monto) FROM Movimientos", nativeQuery = true)
    public abstract long sumaMonto();

    // Metodo para sumar todos los movimientos por empleado
    @Query(value = "SELECT SUM(monto) FROM Movimientos WHERE empleado_id=?1", nativeQuery = true)
    public abstract long sumaMontoPorEmpleado(Integer id);

    // Metodo para sumar todos los movimientos
    @Query(value = "SELECT SUM(monto) FROM Movimientos WHERE empleado_id in (SELECT id FROM empleado WHERE empresa_id= ?1)", nativeQuery = true)
    public abstract long sumaMontoPorEmpresa(Integer id);

}
