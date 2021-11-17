package com.mutant.mutantapi.controller;

import com.mutant.mutantapi.dto.MutantDTO;
import com.mutant.mutantapi.dto.StatsDTO;
import com.mutant.mutantapi.mutantUtils.JsonParser;
import com.mutant.mutantapi.services.MutantServiceImpl;
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
    String[] dnas = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    String basePath = "/api/v1/mutant";
    MutantDTO requestMutantDTO;
    StatsDTO stats;
    List<MutantDTO> mutantsDTO;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MutantServiceImpl mutantService;

    @BeforeEach
    public void setup(){
        mutantsDTO = new ArrayList<>();
        MutantDTO mutant1 = MutantDTO.builder().id(1L).dna(dnas).isMutant(true).build();
        MutantDTO mutant2 = MutantDTO.builder().id(2L).dna(dnas).isMutant(false).build();
        MutantDTO mutant3 = MutantDTO.builder().id(3L).dna(dnas).isMutant(true).build();

        requestMutantDTO = MutantDTO.builder().dna(dnas).build();


        mutantsDTO.add(mutant1);
        mutantsDTO.add(mutant2);
        mutantsDTO.add(mutant3);

        stats = StatsDTO.builder().count_mutant_dna(2).count_human_dna(1).ratio(2/1).build();

    }

    @Test
    public void isMutantOkTest() throws Exception{
        Mockito.when(mutantService.isMutant(dnas)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.
                        post(basePath).
                        contentType(MediaType.APPLICATION_JSON).
                        content(JsonParser.toJsonString(requestMutantDTO)).
                        accept(MediaType.APPLICATION_JSON)).
                        andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(mutantService).isMutant(dnas);
    }

    @Test
    public void isMutantNotOkTest() throws Exception{
        Mockito.when(mutantService.isMutant(dnas)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.
                post(basePath).
                contentType(MediaType.APPLICATION_JSON).
                content(JsonParser.toJsonString(requestMutantDTO)).
                accept(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void listAllTest() throws Exception{
        Mockito.when(mutantService.getAll()).thenReturn(mutantsDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(basePath).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        Mockito.verify(mutantService).getAll();
    }

    @Test
    public void statsTest() throws Exception{
        Mockito.when(mutantService.stats()).thenReturn(stats);

        mockMvc.perform(MockMvcRequestBuilders.get(basePath + "/stats").
                accept(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.content().json(JsonParser.toJsonString(stats)));
    }
}
