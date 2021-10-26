package com.mutant.mutantapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Desafio Mercado Libre - Mutantes",
        version = "1.0",
        description = "Proyecto realizado para la cátedra Taller de Programación Avanzada - UTN FRM")
)
public class MutantApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutantApiApplication.class, args);
    }

}
