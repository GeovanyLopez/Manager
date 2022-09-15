package com.ciclo3.Ciclo3;

import com.ciclo3.Ciclo3.modelos.Empleado;
import com.ciclo3.Ciclo3.modelos.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication		// (exclude = {SecurityAutoConfiguration.class})

public class Ciclo3Application {

	@GetMapping("/hello")
	public String inicio(){
		return "Saldremos vivos de esta...";
	}

	@GetMapping("/empresa")
	public String test(){
		Empresa empresa1 = new Empresa("GlobalCoffee","Cll 33 78-12","3125588664","125486");
		empresa1.setNombre("Global Software Ltda");
		return empresa1.getNombre();
	}

    @GetMapping("/empleado")
    public String test1(){
        Empleado empleado1 = new Empleado();
        empleado1.setNombreEmpleado("James Lopez Restrepo");
        return empleado1.getNombreEmpleado();
    }
	public static void main(String[] args) {
		SpringApplication.run(Ciclo3Application.class, args);



	}

}
