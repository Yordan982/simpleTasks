package com.example.P02WordCounter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WordCounter {
    public static LinkedHashMap<String,Integer> wordCounter(String[] input) {
        HashMap<String, Integer> countOccurrence = new HashMap<>();
        for (String word : input) {
            String newWord = removePunctuation(word);
            if (countOccurrence.containsKey(newWord)) {
                countOccurrence.put(newWord, countOccurrence.get(newWord) + 1);
            } else {
                countOccurrence.put(newWord, 1);
            }
        }
        return sortWordCounterSortedByValuesThenByKeyAsc(countOccurrence);
    }

    private static String removePunctuation(String word) {
        return word.replaceAll("[.,!?]", "").toLowerCase();
    }

    private static LinkedHashMap<String, Integer> sortWordCounterSortedByValuesThenByKeyAsc(HashMap<String, Integer> countOccurrence) {
        return countOccurrence
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}