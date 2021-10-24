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
       boolean isMutant = mutantSearch.isMutant(adns);
       Mutant mutant = Mutant.builder().
               isMutant(isMutant).
                dna(adns).
               build();

       mutantRepository.save(mutant);

       return mutantSearch.isMutant(adns);
   }
}
