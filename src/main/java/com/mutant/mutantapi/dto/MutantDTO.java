package com.mutant.mutantapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MutantDTO {
    private long id;
    private String[]dna;
    private boolean isMutant;
}
