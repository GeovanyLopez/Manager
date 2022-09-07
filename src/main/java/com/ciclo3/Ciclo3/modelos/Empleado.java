package com.ciclo3.Ciclo3.modelos;

import javax.persistence.*;

@Entity
@Table(name = "Empleado")// Se relaciona la clase Empleado con una tabla llamada Empleado
public class Empleado {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//permite generar un Id en orden para los
    // objetos que se vayan creando
    private int id;
    private String nombreEmpleado;
    private String correo;
    @ManyToOne // Asociar tablas
    @JoinColumn(name = "empresa_id")// Relacionar Empleado con una empresa
    private Empresa empresa;
    private String rol;

    // Constructor
    public Empleado() {

    }

    public Empleado(String nombreEmpleado, String correo, Empresa empresa, String rol) {
        this.nombreEmpleado = nombreEmpleado;
        this.correo = correo;
        this.empresa = empresa;
        this.rol = rol;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
