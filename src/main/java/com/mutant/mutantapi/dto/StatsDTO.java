package com.mutant.mutantapi.dto;

import lombok.Builder;
import lombok.Data;
import org.json.JSONObject;

@Data
@Builder
public class StatsDTO {
    int count_mutant_dna;
    int count_human_dna;
    double ratio;

    public JSONObject toJSONObject(){
        JSONObject jo = new JSONObject();
        jo.put("count_mutant_dna",count_mutant_dna);
        jo.put("count_human_dna", count_human_dna);
        jo.put("ratio",ratio);
        return jo;
    }
}
