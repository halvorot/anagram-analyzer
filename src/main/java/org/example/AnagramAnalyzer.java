package org.example;

import java.util.*;

public class AnagramAnalyzer {

    public static boolean isAnagram(final String a, final String b) {

        String aSorted = sortString(a.toLowerCase());
        String bSorted = sortString(b.toLowerCase());

        return aSorted.equals(bSorted);
    }

    private static String sortString(final String word) {
        char[] tempArray = word.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public static List<List<String>> getAnagrams(final List<String> words) {

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
