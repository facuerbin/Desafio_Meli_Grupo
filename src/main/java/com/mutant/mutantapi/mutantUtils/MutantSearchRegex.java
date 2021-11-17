package com.mutant.mutantapi.mutantUtils;

import com.mutant.mutantapi.exceptions.InvalidArrayException;
import com.mutant.mutantapi.exceptions.InvalidDNALetterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@Slf4j
public class MutantSearchRegex {

    private int mutantCount;

    public boolean isMutant(String[] sequences) throws Exception {
        mutantCount = 0;
        if (!isValidArray(sequences)) throw new InvalidArrayException();
        return searchHorizontal(sequences) || searchVertical(sequences) || searchOblique(sequences);
    }

    public void search(String sequence) throws InvalidDNALetterException {
        if (Pattern.compile("[^C|T|A|G]", Pattern.CASE_INSENSITIVE).matcher(sequence).find()) throw new InvalidDNALetterException();
        if (Pattern.compile("(C|T|G|A)\\1{3}", Pattern.CASE_INSENSITIVE).matcher(sequence).find()) {
            mutantCount++;
            log.info("Encontrado : " + sequence);
        }
    }

    public boolean searchHorizontal(String[] sequences) throws InvalidDNALetterException {
        for (String e : sequences) {
            search(e);
            if (mutantCount >= 2) return true;
        }
        return false;
    }

    public boolean searchVertical(String[] sequences) throws InvalidDNALetterException {
        String sequence = "";
        for (int i = 0; i < sequences.length; i++) {
            for (int j = 0; j < sequences.length; j++) {
                sequence += sequences[j].charAt(i);
            }
            search(sequence);
            if (mutantCount >= 2) return true;
            sequence = "";
        }
        return false;
    }

    public boolean searchOblique(String[] sequences) throws InvalidDNALetterException {
        String sequence = "";
        int height = sequences.length;
        int width = sequences[0].length();
        for (int diagonal = 1 - width; diagonal <= height - 1; diagonal += 1) {
            // Search Oblique \
            for (int vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal);
                 vertical < height && horizontal < width; vertical += 1, horizontal += 1) {
                sequence += sequences[vertical].charAt(horizontal);
            }
            search(sequence);
            sequence = "";

            // Search Counter Oblique /
            for (int vertical = Math.max(0, diagonal), horizontal = Math.min(width - 1, diagonal + width - 1);
                 vertical < height && horizontal >= 0; vertical += 1, horizontal -= 1) {
                sequence += sequences[vertical].charAt(horizontal);
            }
            search(sequence);

            if (mutantCount >= 2) return true;
            sequence = "";
        }
        return false;
    }


    public static boolean isValidArray(String[] sequences) {
        int size = sequences.length;
        if (size < 4) return false; // La matriz como mínimo debe ser de 4X4

        for (String line : sequences) {
            if (line.length() != size) return false; // La matriz debe ser cuadrada
        }

        return true;
    }

}
