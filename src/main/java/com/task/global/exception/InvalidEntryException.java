package com.task.global.exception;

public class InvalidEntryException extends RuntimeException {
    public InvalidEntryException() {
        super("Invalid data provided");
    }
}