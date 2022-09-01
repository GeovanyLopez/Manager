package com.ciclo3.Ciclo3.modelos;

import javax.persistence.*;

@Entity
@Table(name = "Empresa")// Se relaciona la clase Empresa con una tabla llamada Empresa
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//permite generar un Id en orden para los
    // objetos que se vayan creando
    private int id;

    private String nombre;
    private String direccion;
    private String telefono;
    private String NIT;

    // Constructor
    public Empresa() {
    }

    public Empresa(String nombre, String direccion, String telefono, String NIT) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.NIT = NIT;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }
}
