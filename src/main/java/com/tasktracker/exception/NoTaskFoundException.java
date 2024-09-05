package com.tasktracker.exception;

public class NoTaskFoundException extends RuntimeException {
    public NoTaskFoundException(String message) {
        super(message);
    }
}
