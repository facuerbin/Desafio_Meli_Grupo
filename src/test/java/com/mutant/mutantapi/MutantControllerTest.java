package com.mutant.mutantapi;

import com.mutant.mutantapi.controller.MutantController;
import com.mutant.mutantapi.model.Mutant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@SpringBootTest
public class MutantControllerTest {

    @Autowired
    MutantController mutantController;

    @Test
    void isMutantTrueTest() {
        String[] dnas = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        Mutant mutant = new Mutant(null,dnas,false);
        ResponseEntity<String> httpResponse = mutantController.isMutant(mutant);
        Assertions.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void isMutantFalseTest() {
        String[] dnas = {"CTGCGA","CAGTGC","TTATGT","AGAAGG","ACCCTA","TCACTG"};
        Mutant mutant = new Mutant(null,dnas,false);
        ResponseEntity<?> httpResponse = mutantController.isMutant(mutant);
        Assertions.assertEquals(httpResponse.getStatusCode(), HttpStatus.FORBIDDEN);
    }

    @Test
    void getAllTest() {
        ResponseEntity<?> httpResponse = mutantController.getAll();
        Assertions.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void generateDNATest() {
        ResponseEntity<?> httpResponse = mutantController.generateDNA(4);
        Assertions.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void generateDNANot4x4Test() {
        ResponseEntity<?> httpResponse = mutantController.generateDNA(3);
        Assertions.assertEquals(httpResponse.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void statsTest() {
        ResponseEntity<?> httpResponse = mutantController.stats();
        Assertions.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
    }
}
