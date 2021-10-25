package com.mutant.mutantapi.mutantUtils;

import java.util.Random;

public class GenerateRandom {
    private static final char[] dnaLetters = {'A', 'C', 'T', 'G'}; // Letras válidas para ADN mutante

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
}
