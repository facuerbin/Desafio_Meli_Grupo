package com.mutant.mutantapi.controller;

import com.mutant.mutantapi.dto.MutantDTO;
import com.mutant.mutantapi.dto.StatsDTO;
import com.mutant.mutantapi.services.MutantServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.mutant.mutantapi.mutantUtils.GenerateRandom.generateRandom;


@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/mutant")
public class MutantController {
    @Autowired
    private MutantServiceImpl mutantService;

    @Operation(
            summary = "Endpoint que recibe ADN y responde si es ADN mutante. En caso de ser ADN mutante retorna estado 200 y si no lo es retorna 403",
            description = "Se requiere una matriz de NxN con las letras (A, C, G, T)"
    )
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Retorna 200 en caso de que el ADN enviado sea mutante. Es decir tiene mínimo 2 cadenas de 4 letras iguales sucesivas en cualquier dirección. No devuelve contenido"
                ), @ApiResponse(
                        responseCode = "403",
                        description = "Retorna 403 en caso de que el ADN enviado NO sea mutante. No devuelve contenido"
                    )
            }
    )
    @PostMapping
    public ResponseEntity isMutant(@RequestBody MutantDTO mutant){
        return mutantService.isMutant(mutant.getDna()) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    @Operation(
            summary = "Endpoint que retorna un array con todos los ADN analizados por el sistema."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Retorna todos los ADN analizados por el sistema, mutantes como no mutantes."
                    )
            }
    )
    @GetMapping("")
    public ResponseEntity<?> getAll () {
        try {
            List<MutantDTO> allMutants = mutantService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(allMutants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @Operation(
            summary = "Endpoint para generar matrices de ADN aleatorias. Requiere que se le pase el tamaño de la matriz por parámetro",
            parameters = @Parameter(
                    name = "size",
                    description = "Debe ser un número entero mayor o igual a 4",
                    in = ParameterIn.PATH,
                    required = true,
                    schema = @Schema( type = "integer")
            )
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Retorna una matriz cuadrada del tamaño del parámetro generada aleatoriamente."
                    )
            }
    )
    @GetMapping("/{size}")
    public ResponseEntity<?> generateDNA (@PathVariable int size) {
        try {
            if (size < 4) {
                throw new Exception("El tamaño mínimo es de 4");
            }
            String[] generatedDNA = generateRandom(size);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(generatedDNA);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: \n"+e.getMessage());
        }
    }

    @Operation(
            summary = "Endpoint para consultar las estadísticas del sistema."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Retorna un JSON con la cantidad de ADN mutante y ADN humano de la base de datos y el ratio entre ambos. ratio = mutant_dna / human_dna"
                    )
            }
    )
    @GetMapping("/stats")
    public ResponseEntity<?> stats() {
        try {
            StatsDTO dtoEstadistica = mutantService.stats();
            return ResponseEntity.status(HttpStatus.OK).body((dtoEstadistica));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }
    }

}
