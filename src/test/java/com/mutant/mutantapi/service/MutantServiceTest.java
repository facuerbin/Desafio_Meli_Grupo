package com.mutant.mutantapi.service;

import com.mutant.mutantapi.model.Mutant;
import com.mutant.mutantapi.mutantUtils.MutantSearchRegex;
import com.mutant.mutantapi.repository.MutantRepository;
import com.mutant.mutantapi.services.MutantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MutantServiceTest {

//    String[] dnas = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
//    List<Mutant> mutants;
//
//    @Mock
//    MutantSearchRegex regex;
//
//    @Mock
//    MutantRepository repository;
//
//    @InjectMocks
//    MutantService service;
//
//    @BeforeEach
//    public void setup(){
//
//        Mutant mutant1 = new Mutant(1L,dnas,true);
//        Mutant mutant2 = new Mutant(2L,dnas,true);
//        Mutant mutant3 = new Mutant(3L,dnas,false);
//
//        mutants = new ArrayList<>(Arrays.asList(mutant1,mutant2,mutant3));
//
//    }
//
//    @Test
//    public void getAll(){
//        Mockito.when(repository.findAll()).thenReturn(mutants);
//
//        List<Mutant> mutantsServ = service.getAll();
//
//        Assertions.assertNotNull(mutantsServ);
//        Assertions.assertEquals(mutants,mutantsServ);
//
//    }
//    @Test
//    public void isMutantTest(){
//        Mockito.when(regex.isMutant(dnas)).thenReturn(true);
//
//        boolean isMutant = service.isMutant(dnas);
//
//        Assertions.assertTrue(isMutant);
//    }
}
