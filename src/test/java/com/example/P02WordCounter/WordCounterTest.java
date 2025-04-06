package com.example.P02WordCounter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WordCounterTest {
    String[] words = {"rabbit", "zeBr.a", "заек", "bear", "raB,bit", "zebRa!", "заЕК"};

    @Test
    public void testWordCounterWithoutOrder() {
        LinkedHashMap<String, Integer> manualMap = new LinkedHashMap<>();
        manualMap.put("rabbit", 2);
        manualMap.put("zebra", 2);
        manualMap.put("bear", 1);
        manualMap.put("заек", 2);
        assertEquals(manualMap, WordCounter.wordCounter(words));
    }

    @Test
    public void testWordCounterWithOrder() {
        List<String> expectedKeys = Arrays.asList("rabbit", "zebra", "заек", "bear");
        List<Integer> expectedValues = Arrays.asList(2, 2, 2, 1);

        LinkedHashMap<String, Integer> actual = WordCounter.wordCounter(words);

        assertEquals(expectedKeys, new ArrayList<>(actual.keySet()));
        assertEquals(expectedValues, new ArrayList<>(actual.values()));
    }
}