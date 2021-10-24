package com.mutant.mutantapi.controller;

import com.mutant.mutantapi.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/mutant/api/v1/mutant")
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping
    public ResponseEntity isMutant(@RequestBody String[] dna){
        return mutantService.isMutant(dna) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
