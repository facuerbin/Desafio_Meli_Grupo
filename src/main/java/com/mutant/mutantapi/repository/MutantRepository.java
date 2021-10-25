package com.mutant.mutantapi.repository;

import com.mutant.mutantapi.model.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MutantRepository extends JpaRepository<Mutant,Long> {
    @Query(value = "SELECT count(id) FROM Mutant WHERE is_mutant=true", nativeQuery = true)
    int countMutant();

    @Query(value = "SELECT count(id) FROM Mutant", nativeQuery = true)
    int countTotal();
}
