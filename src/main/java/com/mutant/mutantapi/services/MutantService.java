package com.mutant.mutantapi.services;

import com.mutant.mutantapi.dto.MutantDTO;
import com.mutant.mutantapi.dto.StatsDTO;
import com.mutant.mutantapi.model.Mutant;

import java.util.List;
import java.util.Optional;

 public interface MutantService {
     Optional<Mutant> getOne(String dna);
     int countTotal ();
     int countMutant ();
     List<MutantDTO> getAll ();
     StatsDTO stats() throws Exception;
     boolean isMutant(String[] adns);
}
