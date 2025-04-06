package com.example.P03ArrayListSpeed;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListSpeedTest {
    @Test
    public void testComparePerformance() {
        ArrayList<String> testList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            testList.add("test" + i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        ArrayListSpeed.comparePerformance(testList);
        String output = outputStream.toString();

        assertTrue(output.contains("For-loop time:"), "Output should contain For-loop time");
        assertTrue(output.contains("While-loop time:"), "Output should contain While-loop time");
        assertTrue(output.contains("Iterator time:"), "Output should contain Iterator time");

        String[] lines = output.split("\n");
        long forLoopTime = Long.parseLong(lines[0].split(":")[1].trim().replace(" nanoseconds", ""));
        long whileLoopTime = Long.parseLong(lines[1].split(":")[1].trim().replace(" nanoseconds", ""));
        long iteratorTime = Long.parseLong(lines[2].split(":")[1].trim().replace(" nanoseconds", ""));

        assertTrue(forLoopTime >= 0, "For-loop time should be non-negative");
        assertTrue(whileLoopTime >= 0, "While-loop time should be non-negative");
        assertTrue(iteratorTime >= 0, "Iterator time should be non-negative");
    }
}