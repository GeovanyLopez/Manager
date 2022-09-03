package com.ciclo3.Ciclo3.repositorio;

import com.ciclo3.Ciclo3.modelos.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Esta anotacion le dice a Spring que esta clase es un Repositorio
public interface EmpresaRepositorio extends JpaRepository<Empresa,Integer> {

}
