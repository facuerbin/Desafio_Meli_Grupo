package com.mutant.mutantapi.services;

import com.mutant.mutantapi.dto.MutantDTO;
import com.mutant.mutantapi.dto.StatsDTO;
import com.mutant.mutantapi.mappers.MutantMapper;
import com.mutant.mutantapi.model.Mutant;
import com.mutant.mutantapi.repository.MutantRepository;
import com.mutant.mutantapi.mutantUtils.MutantSearchRegex;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MutantServiceImpl implements MutantService{

    @Autowired
    MutantSearchRegex mutantSearch;

    @Autowired
    MutantRepository mutantRepository;

    public Optional<Mutant> getOne(String dna){
        log.info("Fetching mutant by dna");
        return mutantRepository.getByDna(dna);
    }
    public int countTotal () {
        log.info("Fetching total quantity");
        return mutantRepository.countTotal();
    }

    public int countMutant () {
        log.info("Fetching mutants quantity");
        return mutantRepository.countMutant();
    }

    public List<MutantDTO> getAll () {
        log.info("Fetching all mutants");
        List<Mutant> listMutants;
        listMutants = mutantRepository.findAll();
        return MutantMapper.toDTO(listMutants);
    }

    public StatsDTO stats() throws Exception{
        int countTotal = this.countTotal();
        int countMutant = this.countMutant();
        int countHuman = countTotal-countMutant;
        try{
            double ratio = (double) countMutant/(double) countHuman;
            return  StatsDTO.builder().
                    count_mutant_dna(countMutant).
                    count_human_dna(countHuman).
                    ratio(ratio).
                    build();
        }catch (Exception e){
            log.error(e.getMessage());
            throw new Exception();
        }


    }

   public boolean isMutant(String[] adns){

       StringBuilder sb = new StringBuilder();
       Arrays.stream(adns).forEach(x -> sb.append(x));

        Optional<Mutant> existingMutant = this.getOne(sb.toString());
        if(!existingMutant.isEmpty()){
            return existingMutant.get().isMutant();
        }

        boolean isMutant = mutantSearch.isMutant(adns);
        Mutant mutant = Mutant.builder()
                           .isMutant(isMutant)
                           .dna(sb.toString())
                           .build();
        try {
            log.info("Saving Mutant : " + mutant.toString());
            mutantRepository.save(mutant);
        } catch (JDBCException e) {
            log.error(e.getMessage(),e.getSQL());
        } catch (Exception e){
            log.error(e.getMessage(),e.getCause());
        }
        return isMutant;
   }
}
