package com.spring.exercicio;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Exercício 01", version = "1", description = "API desenvolvida conforme requerido pelo Exercício 01"))
@EnableScheduling
public class TarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarefasApplication.class, args);
	}

}
