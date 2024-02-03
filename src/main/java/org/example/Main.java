package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> words = FileHandler.readAllNonEmptyLines("src/main/resources/words-utf8.txt");

        List<List<String>> anagrams = AnagramAnalyzer.getAnagrams(words);

        FileHandler.writeToFile(anagrams, "src/main/resources/anagrams.txt", " ");

    }


}