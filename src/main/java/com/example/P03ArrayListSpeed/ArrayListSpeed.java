package com.example.P03ArrayListSpeed;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListSpeed {
    private static void arrayListSpeedFor(ArrayList<?> list) {
        for (int current = 0; current < list.size() - 1; current++) {
            list.get(current);
        }
    }

    private static void arrayListSpeedWhile(ArrayList<?> list) {
        int currentElementIndex = 0;
        while (currentElementIndex < list.size()) {
            list.get(currentElementIndex);
            currentElementIndex++;
        }
    }

    private static void arrayListSpeedIterator(ArrayList<?> list) {
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object currentElement = iterator.next();
            iterator.next();
        }
    }

    public static void comparePerformance(ArrayList<?> list) {
        long startTime = System.nanoTime();
        arrayListSpeedFor(list);
        long endTime = System.nanoTime();
        long durationFor = endTime - startTime;
        System.out.println("For-loop time: " + durationFor + " nanoseconds");

        startTime = System.nanoTime();
        arrayListSpeedWhile(list);
        endTime = System.nanoTime();
        long durationWhile = endTime - startTime;
        System.out.println("While-loop time: " + durationWhile + " nanoseconds");

        startTime = System.nanoTime();
        arrayListSpeedIterator(list);
        endTime = System.nanoTime();
        long durationIterator = endTime - startTime;
        System.out.println("Iterator time: " + durationIterator + " nanoseconds");
    }
}