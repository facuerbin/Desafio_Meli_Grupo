import com.mutant.mutantapi.searchMutant.MutantSearchRegex;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MutantTest {

    MutantSearchRegex regex = new MutantSearchRegex();

    @Test
    public void regexTest(){
        String[] dnas = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};

        assertTrue(regex.isMutant(dnas));
    }

    @Test
    public void regexNotOkTest(){
        String[] dnas = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        assertFalse(regex.isMutant(dnas));
    }
    
//    @Test
//    public void searchRepeatTest(){
//        String word = "CCCCTA";
//        boolean isRepeated = mutantSearch.search(word);
//        assertTrue(isRepeated);
//    }
//
//    @Test
//    public void searchRepeatTestNotOk(){
//        String word = "TTATTT";
//        boolean isRepeated = mutantSearch.search(word);
//        assertFalse(isRepeated);
//    }
//
//    @Test
//    public void isMutantTestOk(){
//        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
//        boolean isMutant = mutantSearch.isMutant(dna);
//        assertTrue(isMutant);
//    }
//
//    @Test
//    public void isMutantTestNotOk(){
//        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
//        boolean isMutant = mutantSearch.isMutant(dna);
//        assertFalse(isMutant);
//    }

}
