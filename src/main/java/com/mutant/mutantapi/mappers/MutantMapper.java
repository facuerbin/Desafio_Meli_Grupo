package com.mutant.mutantapi.mappers;

import com.mutant.mutantapi.dto.MutantDTO;
import com.mutant.mutantapi.model.Mutant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MutantMapper {
    public static List<MutantDTO> toDTO(List<Mutant> mutants){
        List<MutantDTO> mutantsDTO = new ArrayList<MutantDTO>();
        for(Mutant mutant: mutants){
            mutantsDTO.add(
                    MutantDTO.builder()
                    .isMutant(mutant.isMutant())
                    .id(mutant.getId())
                    .dna(mutant.getDna().split("(?<=\\G.{"+(int)Math.sqrt(mutant.getDna().length())+"})"))
                    .build()
            );
        }
        return mutantsDTO;
    }
//    public static List<Mutant> toEntity(List<MutantDTO> dtos){
//        List<Mutant> mutants = new ArrayList<Mutant>();
//        for(MutantDTO dto : dtos){
//            StringBuilder sb = new StringBuilder();
//            Arrays.stream(dto.getDna()).forEach(x -> sb.append(x));
//            mutants.add(
//              Mutant.builder()
//                      .isMutant(dto.isMutant())
//                      .dna(sb.toString())
//                      .build()
//            );
//        }
//        return mutants;
//    }
}
