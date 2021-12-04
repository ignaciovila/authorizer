package com.nubank.exam.usecases.exceptions;

public class InsufficientLimitException extends ValidationException {
    public InsufficientLimitException() {
        super("insufficient-limit");
    }
}
