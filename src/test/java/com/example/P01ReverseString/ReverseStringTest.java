package com.example.P01ReverseString;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReverseStringTest {
    @Test
    public void testReverseStringValidInput() {
        String output = ReverseString.reverseString("РаZмеNi текSта!");
        assertEquals("!атSкет iNемZаР", output);
    }
    @Test
    public void testReverseStringValidInputEmoji() {
        String output = ReverseString.reverseString("работи \uD83D\uDE03");
        assertEquals("\uD83D\uDE03 итобар", output);
    }
    @Test
    public void testReverseStringInvalidInput() {
        String input = "";
        assertThrows(IllegalArgumentException.class, () -> ReverseString.reverseString(input));
    }
}