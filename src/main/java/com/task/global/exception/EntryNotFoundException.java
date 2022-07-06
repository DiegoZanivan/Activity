package com.task.global.exception;

public class EntryNotFoundException extends RuntimeException {
    public EntryNotFoundException(String id) {
        super("No entry found for id: " + id);
    }
}
