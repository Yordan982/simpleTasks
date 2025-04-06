package com.example.P04DuplicateSymbols;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DuplicateSymbolsTest {
    @Test
    public void testDuplicateSymbols() {
        Character[] symbols = {'f', 'a', 'n', 't', 'a', 's', 't', 'i', 'c'};
        HashMap<Character, Integer> actualMap = DuplicateSymbols.findDuplicateChars(symbols);
        HashMap<Character, Integer> manualMap = new HashMap<>();
        manualMap.put('a', 2);
        manualMap.put('t', 2);
        assertEquals(manualMap, actualMap);
    }
}