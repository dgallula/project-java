package com.library.exception;

public class BorrowingException extends RuntimeException {
    public BorrowingException(String message) {
        super(message);
    }
}