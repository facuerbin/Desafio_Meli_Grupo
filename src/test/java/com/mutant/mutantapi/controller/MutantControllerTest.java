package com.mutant.mutantapi.controller;

import com.mutant.mutantapi.dto.StatsDTO;
import com.mutant.mutantapi.model.Mutant;
import com.mutant.mutantapi.services.MutantService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(MutantController.class)
public class MutantControllerTest {
//    String[] dnas = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
//    String basePath = "/api/v1/mutant";
//    String requestBody ;
//    StatsDTO stats;
//    List<Mutant> mutants;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    MutantService mutantService;
//
//    @BeforeEach
//    public void setup(){
//        mutants = new ArrayList<>();
//        Mutant mutant1 = new Mutant(1L,dnas,true);
//        Mutant mutant2 = new Mutant(2L,dnas,true);
//        Mutant mutant3 = new Mutant(3L,dnas,false);
//
//
//        requestBody = "{\n" +
//                "    \"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]\n" +
//                "}";
//
//        mutants.add(mutant1);
//        mutants.add(mutant2);
//        mutants.add(mutant3);
//
//        stats = StatsDTO.builder().count_mutant_dna(2).count_human_dna(1).ratio(2/1).build();
//
//    }
//
//    @Test
//    public void isMutantOkTest() throws Exception{
//        Mockito.when(mutantService.isMutant(dnas)).thenReturn(true);
//
//        mockMvc.perform(MockMvcRequestBuilders.
//                        post(basePath).
//                        contentType(MediaType.APPLICATION_JSON).
//                        content(requestBody).
//                        accept(MediaType.APPLICATION_JSON)).
//                        andExpect(MockMvcResultMatchers.status().isOk());
//
//        Mockito.verify(mutantService).isMutant(dnas);
//    }
//
//    @Test
//    public void isMutantNotOkTest() throws Exception{
//        Mockito.when(mutantService.isMutant(dnas)).thenReturn(false);
//
//        mockMvc.perform(MockMvcRequestBuilders.
//                post(basePath).
//                contentType(MediaType.APPLICATION_JSON).
//                content(requestBody).
//                accept(MediaType.APPLICATION_JSON)).
//                andExpect(MockMvcResultMatchers.status().isForbidden());
//    }
//
//    @Test
//    public void listAllTest() throws Exception{
//        Mockito.when(mutantService.getAll()).thenReturn(mutants);
//
//        mockMvc.perform(MockMvcRequestBuilders.get(basePath).accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(jsonPath("$", hasSize(3)));
//
//        Mockito.verify(mutantService).getAll();
//    }
//
//    @Test
//    public void statsTest() throws Exception{
//        Mockito.when(mutantService.countTotal()).thenReturn(3);
//        Mockito.when(mutantService.countMutant()).thenReturn(2);
//
//        mockMvc.perform(MockMvcRequestBuilders.get(basePath + "/stats").
//                accept(MediaType.APPLICATION_JSON)).
//                andExpect(MockMvcResultMatchers.status().isOk()).
//                andExpect(MockMvcResultMatchers.content().json(stats.toJSONObject().toString()));
//    }
}
