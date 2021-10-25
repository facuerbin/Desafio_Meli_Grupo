package com.mutant.mutantapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatsDTO {
    int countMutantDNA;
    int countHumanDNA;
    double ratio;
}
