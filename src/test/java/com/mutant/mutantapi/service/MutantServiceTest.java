package com.mutant.mutantapi.service;

import com.mutant.mutantapi.dto.MutantDTO;
import com.mutant.mutantapi.dto.StatsDTO;
import com.mutant.mutantapi.mappers.MutantMapper;
import com.mutant.mutantapi.model.Mutant;
import com.mutant.mutantapi.mutantUtils.MutantSearchRegex;
import com.mutant.mutantapi.repository.MutantRepository;
import com.mutant.mutantapi.services.MutantService;
import com.mutant.mutantapi.services.MutantServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class MutantServiceTest {

    String[] dnas = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    List<Mutant> mutants;
    List<MutantDTO> mutantsDTO;

    @Mock
    MutantSearchRegex regex;

    @Mock
    MutantRepository repository;

    @InjectMocks
    MutantServiceImpl service;

    @BeforeEach
    public void setup(){

        Mutant mutant1 = new Mutant(1L, Arrays.stream(dnas).collect(Collectors.joining()), true);
        Mutant mutant2 = new Mutant(2L, Arrays.stream(dnas).collect(Collectors.joining()),true);
        Mutant mutant3 = new Mutant(3L, Arrays.stream(dnas).collect(Collectors.joining()),false);

        mutants = new ArrayList<>(Arrays.asList(mutant1,mutant2,mutant3));

        mutantsDTO = MutantMapper.toDTO(mutants);

    }

    @Test
    public void getAll(){
        Mockito.when(repository.findAll()).thenReturn(mutants);

        List<MutantDTO> mutantsDTO = service.getAll();

        Assertions.assertNotNull(mutantsDTO);
        Assertions.assertEquals(mutants.size(),mutantsDTO.size());

    }
    @Test
    public void isMutantTest(){
        Mockito.when(regex.isMutant(dnas)).thenReturn(true);

        boolean isMutant = service.isMutant(dnas);

        Assertions.assertTrue(isMutant);
    }
    @Test
    public void stats()throws Exception{
        Mockito.when(repository.countMutant()).thenReturn(2);
        Mockito.when(repository.countTotal()).thenReturn(3);

        StatsDTO stats = service.stats();

        Assertions.assertNotNull(stats);
        Assertions.assertEquals(2.0,stats.getRatio());
    }

//    @Test
//    public void statsDividedByZero()throws Exception{
//        Mockito.when(repository.countMutant()).thenReturn(2);
//        Mockito.when(repository.countTotal()).thenReturn(2);
//
//        StatsDTO stats = service.stats();
//
//        Assertions.assertNotNull(stats);
//        Assertions.assertEquals(2.0,stats.getRatio());
//    }
}
