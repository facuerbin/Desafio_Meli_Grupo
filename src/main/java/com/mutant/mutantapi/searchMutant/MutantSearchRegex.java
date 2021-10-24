package com.mutant.mutantapi.searchMutant;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.regex.Pattern;

@Component
public class MutantSearchRegex {

    private int mutantCount;
    private static final char[] dnaLetters = {'A', 'C', 'T', 'G'}; // Letras válidas para ADN mutante
    private static final int cantidadLetrasMutante = 4; // la cantidad de letras que se deben repetir para ADN mutante

    public boolean isMutant(String[] sequences){
        mutantCount = 0;
        if( ! isValidArray(sequences) ) return false;
        return searchHorizontal(sequences) || searchVertical(sequences) || searchOblique(sequences) || searchContraOblique(sequences);
    }

    public void search(String sequence){
        if(Pattern.compile("(C|T|G|A)\\1{3,4}").matcher(sequence).find()) {
            mutantCount++;
            System.out.println("Encontrado " + sequence);
        }
    }

    public boolean searchHorizontal(String[] sequences){
        for(String e : sequences){
            search(e);
            if(mutantCount >= 2) return true;
        }
        return false;
    }

    public boolean searchVertical(String[] sequences){
        String sequence = "";
            for(int i = 0 ; i < sequences.length ; i ++){
                for(int j = 0 ; j < sequences.length; j ++){
                    sequence += sequences[j].charAt(i);
                }
                search(sequence);
                if(mutantCount >= 2) return true;
            sequence = "";
        }
        return false;
    }

    public boolean searchOblique (String[] sequences){
        String sequence = "";
        int height = sequences.length;
        int width = sequences[0].length();
        for (int diagonal = 1 - width; diagonal <= height - 1; diagonal += 1) {
            for (int vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal);
                 vertical < height && horizontal < width; vertical += 1, horizontal += 1) {
                sequence += sequences[vertical].charAt(horizontal) ;
            }
            search(sequence);
            if(mutantCount >= 2) return true;
            sequence = "";
        }
        return false;
    }

    public boolean searchContraOblique(String[] sequences){
        String sequence = "";
        int height = sequences.length;
        int width = sequences[0].length();
        for (int diagonal = 1 - width; diagonal <= height - 1; diagonal += 1) {
            for (int vertical = Math.max(0, diagonal), horizontal = Math.min(width - 1, diagonal+width-1);
                 vertical < height && horizontal >= 0; vertical += 1, horizontal -= 1) {
                sequence += sequences[vertical].charAt(horizontal) ;
            }
            search(sequence);
            if(mutantCount >= 2) return true;
            sequence = "";
        }
        return false;
    }

    public static String[] generateRandom(int size) {
        String[] generatedDna = new String[size];
        Random rand = new Random();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            String row = "";
            for (int j = 0; j < size; j++) {
                char letter = dnaLetters[rand.nextInt(dnaLetters.length)];
                row += letter;
            }
            generatedDna[i] = row;
        }

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Se tardó " + totalTime + " milisegundo en generar una matriz de ADN de " + size + "X" + size);
        return generatedDna;
    }

    public static boolean isValidArray(String[] dna) {
        int size = dna.length;
        if (size < 4) {
            // La matriz como mínimo debe ser de 4X4
            return false;
        }

        for (String line : dna) {
            if (line.length() != size) {
                return false;
            }
        }

        return true;
    }

}
