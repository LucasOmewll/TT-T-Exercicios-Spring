package com.lucas.exercicio;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Exercício 02", version = "1", description = "API desenvolvida conforme requerido pelo Exercício 02"))
@EnableScheduling
public class ExercicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExercicioApplication.class, args);
	}

}
