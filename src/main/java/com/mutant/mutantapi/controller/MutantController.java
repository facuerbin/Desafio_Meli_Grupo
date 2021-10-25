package com.mutant.mutantapi.controller;

import com.mutant.mutantapi.dto.StatsDTO;
import com.mutant.mutantapi.model.Mutant;
import com.mutant.mutantapi.services.MutantService;
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
    private MutantService mutantService;

    @PostMapping
    public ResponseEntity isMutant(@RequestBody Mutant mutant){
        return mutantService.isMutant(mutant.getDna()) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("")
    public ResponseEntity<?> getAll () {
        try {
            List<Mutant> allMutants = mutantService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(allMutants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }
    }

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

    @GetMapping("/stats")
    public ResponseEntity<?> stats() {
        try {
            int countTotal = mutantService.countTotal();
            int countMutant = mutantService.countMutant();
            int countHuman = countTotal-countMutant;
            double ratio = (double) countMutant/(double) countHuman;
            StatsDTO dtoEstadistica = StatsDTO.builder().
                    count_mutant_dna(countMutant).
                    count_human_dna(countHuman).
                    ratio(ratio).
                    build();
            return ResponseEntity.status(HttpStatus.OK).body((dtoEstadistica));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }
    }

}
