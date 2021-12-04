package com.nubank.exam.usecases.exceptions;

public class AccountAlreadyInitializedException extends ValidationException {

    public AccountAlreadyInitializedException() {
        super("account-already-initialized");
    }
}
