package org.example;

import java.util.*;

public class AnagramAnalyzer {

    public static boolean isAnagram(String a, String b) {

        String aSorted = sortString(a.toLowerCase());
        String bSorted = sortString(b.toLowerCase());

        return aSorted.equals(bSorted);
    }

    private static String sortString(String word) {
        char[] tempArray = word.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public static boolean isAnagramUsingLetterFrequency(String a, String b) {

        if (a.length() != b.length()) {
            return false;
        }

        String lowerA = a.toLowerCase();
        String lowerB = b.toLowerCase();

        Map<Character, Integer> letterFrequencyA = getLetterFrequency(lowerA);
        Map<Character, Integer> letterFrequencyB = getLetterFrequency(lowerB);

        return letterFrequencyA.equals(letterFrequencyB);

    }

    private static Map<Character, Integer> getLetterFrequency(String word) {
        Map<Character, Integer> letterFrequency = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            Integer currentValue = letterFrequency.putIfAbsent(letter, 1);
            if (currentValue != null) {
                letterFrequency.put(letter, currentValue + 1);
            }
        }
        return letterFrequency;
    }

    public static List<List<String>> getAnagrams(List<String> words) {

        LinkedList<String> mutableWords = new LinkedList<>(words);
        Map<String, List<String>> result = new HashMap<>();

        while (mutableWords.size() > 1) {
            String firstWord = mutableWords.removeFirst();
            for (int i = 0; i < mutableWords.size(); i++) {
                String otherWord = mutableWords.get(i);
                if (isAnagram(firstWord, otherWord)) {
                    result.computeIfAbsent(firstWord, key -> new ArrayList<>()).add(otherWord);
                    mutableWords.remove(otherWord);
                    i -= 1;
                }
            }
        }
        return result.entrySet().stream()
                .map(entry -> {
                    List<String> innerList = entry.getValue();
                    innerList.add(0, entry.getKey());
                    return innerList;
                })
                .toList();
    }
}
