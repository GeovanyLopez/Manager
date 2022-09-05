package com.ciclo3.Ciclo3.sevicios;

import com.ciclo3.Ciclo3.modelos.Empresa;
import com.ciclo3.Ciclo3.repositorio.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaServicios {

    @Autowired // Conectamos esta clase con el repositorio Empresa
    // Creamos un objeto de la interfaz EmpresaRepositorio
    EmpresaRepositorio empresaRepositorio;

    // Metodo para listar las empresas usando metodos de jpaRepository
    public List<Empresa> listarEmpresas() {
        List<Empresa> listaEmpresas = new ArrayList<>();
        // Recorremos el iterable y lo guarda en una lista
        empresaRepositorio.findAll().forEach(empresa -> listaEmpresas.add((Empresa) empresa));
        return listaEmpresas;
    }

    // Metodo que trae un objeto de tipo Empresa con el id
    public Empresa obtenerEmpresaID(Integer id) {
        return (Empresa) empresaRepositorio.findById(id).get();
    }

    // Metodo para guardar o actualizar objetos de tipo empresa
    public boolean saveUpdate(Empresa empresa) {
        Empresa emp = (Empresa) empresaRepositorio.save(empresa);
        if (empresaRepositorio.findById(emp.getId()) != null) {
            return true;
        }
        return false;
    }

    // Metodo para eliminar una Empresa
    public boolean eliminarEmpresa(Integer id) {
        empresaRepositorio.deleteById(id);
        if (empresaRepositorio.findById(id) != null) {
            return true;
        }
        return false;
    }
}
