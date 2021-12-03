package com.nubank.exam.usecases.exceptions;

public class InsufficientLimitException extends Exception {
    public InsufficientLimitException() {
        super("insufficient-limit");
    }
}
