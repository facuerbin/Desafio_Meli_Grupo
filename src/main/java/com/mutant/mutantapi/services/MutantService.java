package com.mutant.mutantapi.services;

import com.mutant.mutantapi.dto.MutantDTO;
import com.mutant.mutantapi.dto.StatsDTO;
import com.mutant.mutantapi.mappers.MutantMapper;
import com.mutant.mutantapi.model.Mutant;
import com.mutant.mutantapi.repository.MutantRepository;
import com.mutant.mutantapi.mutantUtils.MutantSearchRegex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MutantService {

    @Autowired
    MutantSearchRegex mutantSearch;

    @Autowired
    MutantRepository mutantRepository;

    public Optional<Mutant> getOne(String dna){
        return mutantRepository.getByDna(dna);
    }
    public int countTotal () {
        return mutantRepository.countTotal();
    }

    public int countMutant () {
        return mutantRepository.countMutant();
    }

    public List<MutantDTO> getAll () {
        List<Mutant> listMutants;
        listMutants = mutantRepository.findAll();

        return MutantMapper.toDTO(listMutants);
    }

    public StatsDTO stats(){
        int countTotal = this.countTotal();
        int countMutant = this.countMutant();
        int countHuman = countTotal-countMutant;
        double ratio = (double) countMutant/(double) countHuman;
        return  StatsDTO.builder().
                count_mutant_dna(countMutant).
                count_human_dna(countHuman).
                ratio(ratio).
                build();

    }

   public boolean isMutant(String[] adns){
        StringBuilder sbDNA = new StringBuilder();
        Arrays.stream(adns).forEach(x -> sbDNA.append(x));
        Optional<Mutant> existingMutant = this.getOne(sbDNA.toString());
        if(!existingMutant.isEmpty()){
            return existingMutant.get().isMutant();
        }
        boolean isMutant = mutantSearch.isMutant(adns);
        StringBuilder sb = new StringBuilder();
        Arrays.stream(adns).forEach(x -> sb.append(x));
        Mutant mutant = Mutant.builder()
                           .isMutant(isMutant)
                           .dna(sb.toString())
                           .build();
        try {
               mutantRepository.save(mutant);
        } catch (Exception e) {
               System.out.println(e.getMessage());
//               Aca falta hacer
        }
        return isMutant;
   }
}
