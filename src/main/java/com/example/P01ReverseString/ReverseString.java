package com.example.P01ReverseString;

public class ReverseString {
    public static String reverseString(String input) {
        if (input == null || input.isEmpty())
            throw new IllegalArgumentException("Input cannot be null or empty");

        int[] codePoints = input.codePoints().toArray();
        String reversed = "";

        for (int i = codePoints.length - 1; i >= 0; i--) {
            reversed += new String(Character.toChars(codePoints[i]));
        }

        return reversed;
    }
}