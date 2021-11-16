package com.mutant.mutantapi.repository;

import com.mutant.mutantapi.model.Mutant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
public class MutantRepositoryTest {
    private String[] dnas1 = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    private String[] dnas2 = {"ATGTGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    private String[] dnas3 = {"AGGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};


    @Autowired
    MutantRepository repository;

    @Test
    public void getAll(){
        List<Mutant> listExpected = new ArrayList<>();
        Mutant mutant1 = new Mutant(1L, Arrays.stream(dnas1).collect(Collectors.joining()), true);
        Mutant mutant2 = new Mutant(2L, Arrays.stream(dnas2).collect(Collectors.joining()),true);
        Mutant mutant3 = new Mutant(3L, Arrays.stream(dnas3).collect(Collectors.joining()),false);

        listExpected.add(mutant1);
        listExpected.add(mutant2);
        listExpected.add(mutant3);

        repository.save(mutant1);
        repository.save(mutant2);
        repository.save(mutant3);

        List<Mutant> mutants = repository.findAll();
        Assertions.assertNotNull(mutants);
        Assertions.assertEquals(3,listExpected.size());
        Assertions.assertEquals(listExpected,mutants);
    }

}
