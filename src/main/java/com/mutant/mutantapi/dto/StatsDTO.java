package com.mutant.mutantapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatsDTO {
    int count_mutant_dna;
    int count_human_dna;
    double ratio;
}