package com.tasktracker;

public class IdCounter {
    private static long counter = 0;

    public static synchronized long nextId() {
        return ++counter;
    }
}
