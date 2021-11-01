package com.mutant.mutantapi.services;

import com.mutant.mutantapi.model.Mutant;
import com.mutant.mutantapi.repository.MutantRepository;
import com.mutant.mutantapi.mutantUtils.MutantSearchRegex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MutantService {

    @Autowired
    MutantSearchRegex mutantSearch;

    @Autowired
    MutantRepository mutantRepository;


    public List<Mutant> getAll () {
            return mutantRepository.findAll();
    }

    public int countTotal () {
        return mutantRepository.countTotal();
    }

    public int countMutant () {
        return mutantRepository.countMutant();
    }

   public boolean isMutant(String[] adns){
       boolean isMutant = mutantSearch.isMutant(adns);
       Mutant mutant = Mutant.builder().
               isMutant(isMutant).
               dna(adns).
               build();

       try {
           mutantRepository.save(mutant);
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }

       return isMutant;
   }
}
