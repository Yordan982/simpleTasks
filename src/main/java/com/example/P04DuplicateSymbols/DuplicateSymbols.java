package com.example.P04DuplicateSymbols;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DuplicateSymbols {
    public static HashMap<Character, Integer> findDuplicateChars(Character[] symbols) {
        if (symbols == null || symbols.length == 0)
            throw new IllegalArgumentException("The input symbols cannot be null or empty");

        HashMap<Character, Integer> duplicatedChars = new HashMap<>();
        Set<Character> temp = new HashSet<>();
        for (Character symbol : symbols) {
            if (temp.contains(symbol)) {
                duplicatedChars.put(symbol, duplicatedChars.getOrDefault(symbol, 1) + 1);
            }
            temp.add(symbol);
        }
        return duplicatedChars;
    }
}