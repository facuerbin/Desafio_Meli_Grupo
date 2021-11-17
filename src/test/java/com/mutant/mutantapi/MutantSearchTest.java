package com.mutant.mutantapi;

import com.mutant.mutantapi.mutantUtils.MutantSearchRegex;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MutantSearchTest {

    MutantSearchRegex regex = new MutantSearchRegex();

    @Test
    public void regexTest(){
        String[] dnas = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

        try {
            assertTrue(regex.isMutant(dnas));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void regexNotOkTest(){
        String[] dnas = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        try {
            assertFalse(regex.isMutant(dnas));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
