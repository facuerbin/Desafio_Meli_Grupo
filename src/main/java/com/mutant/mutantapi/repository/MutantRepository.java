package com.mutant.mutantapi.repository;

import com.mutant.mutantapi.model.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MutantRepository extends JpaRepository<Mutant,Long> {
    @Query(value = "SELECT count(id) FROM Mutant WHERE isMutant = true")
    int countMutant();

    @Query(value = "SELECT count(id) FROM Mutant")
    int countTotal();

   Optional<Mutant> getByDna(String dna);
}
