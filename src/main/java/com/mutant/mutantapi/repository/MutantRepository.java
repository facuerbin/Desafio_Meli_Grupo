package com.mutant.mutantapi.repository;

import com.mutant.mutantapi.model.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MutantRepository extends JpaRepository<Mutant,Long> {
}
