package com.ciclo3.Ciclo3;

import com.ciclo3.Ciclo3.modelos.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
@RestController
public class Ciclo3Application {

	@GetMapping("/hello")
	public String inicio(){

		return "Saldremos vivos de esta...";
	}

	@GetMapping("/test")
	public String test(){
		Empresa empresa1 = new Empresa("GlobalCoffee","Cll 33 78-12","3125588664","125486");
		empresa1.setNombre("Global Software Ltda");
		return empresa1.getNombre();
	}
	public static void main(String[] args) {
		SpringApplication.run(Ciclo3Application.class, args);

	}

}
