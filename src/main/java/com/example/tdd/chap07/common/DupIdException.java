package com.example.tdd.chap07.common;

public class DupIdException extends RuntimeException {

    public DupIdException(final String message) {
        super(message);
    }
}
