package com.mutant.mutantapi.services;

import com.mutant.mutantapi.model.Mutant;
import com.mutant.mutantapi.repository.MutantRepository;
import com.mutant.mutantapi.searchMutant.MutantSearchRegex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MutantService {

    @Autowired
    MutantSearchRegex mutantSearch;

    @Autowired
    MutantRepository mutantRepository;

   public boolean isMutant(String[] adns){
//       Para reconstruir las secuencias
//
//       StringBuilder dna = new StringBuilder();
//       Arrays.stream(adns).forEach(word -> dna.append(word));
//       System.out.println(dna);
//       System.out.println(Arrays.toString(
//               dna.toString().split("(?<=\\G.{"+adns.length+"})")
//       ));

       boolean isMutant = mutantSearch.isMutant(adns);
       StringBuilder allSequences = new StringBuilder();
       Arrays.stream(adns).forEach(sequence -> allSequences.append(sequence));
       Mutant mutant = Mutant.builder().
               isMutant(isMutant).
               adn(allSequences.toString()).
               adnLength(adns.length).
               build();

       mutantRepository.save(mutant);

       return mutantSearch.isMutant(adns);
   }
}
