package com.mutant.mutantapi.searchMutant;

import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MutantSearch {

    private static final Logger LOGGER  = LoggerFactory.getLogger(MutantSearch.class);

    public void hash(String[] dnas){
        System.out.println(dnas.hashCode());
    }

    public  boolean isMutant(String[] words){
        if(words.length < 4) return false;
        int dnaMutantCount = 0;

        String word = "";

        //Horizontal
        for(String e: words){
            if(search(e)) {
                dnaMutantCount ++;
                LOGGER.info("Match horizontal: " + e);
                if(dnaMutantCount > 1) return true;
            }
        }


        //Vertical
        for(int i = 0 ; i < words.length ; i ++){
            for(int j = 0 ; j < words.length; j ++){
                word += words[j].charAt(i);
            }
            if(search(word)) {
                dnaMutantCount ++;
                LOGGER.info("Match vertical: " + word);
                if(dnaMutantCount > 1) return true;
            };
            word = "";
        }

        //Oblicua
        word = "";
        int height = words.length;
        int width = words[0].length();
        for (Integer diagonal = 1 - width; diagonal <= height - 1; diagonal += 1) {
            for (Integer vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal);
                 vertical < height && horizontal < width; vertical += 1, horizontal += 1) {
                word += words[vertical].charAt(horizontal) ;
            }
            if(search(word)){
                dnaMutantCount++;
                LOGGER.info("Match Oblicua: " + word);
                if(dnaMutantCount > 1) return true;
            }
            word = "";
        }

        //ContraOblicua
        word = "";
        height = words.length;
        width = words[0].length();
        for (Integer diagonal = 1 - width; diagonal <= height - 1; diagonal += 1) {
            for (Integer vertical = Math.max(0, diagonal), horizontal = Math.min(width - 1, diagonal+width-1);
                 vertical < height && horizontal >= 0; vertical += 1, horizontal -= 1) {
                word += words[vertical].charAt(horizontal) ;
            }
            if(search(word)){
                dnaMutantCount++;
                LOGGER.info("Match ContraOblicua: " + word);
                if(dnaMutantCount > 1) return true;
            }
            word = "";
        }
        return false;
    }


    public boolean search(String word){
        char current = word.charAt(0);
        int repeated = 0;
        for(int i = 1 ; i < word.length(); i++){

            if(word.charAt(i) == current){
                repeated++;
                if(repeated == 3) {
                    return true;
                }
            }
            else{
                current = word.charAt(i);
                repeated = 0;
            }
        }
        return false;
    }
}
