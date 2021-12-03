package com.nubank.exam.usecases.exceptions;

public class AccountAlreadyInitializedException extends Exception {

    public AccountAlreadyInitializedException() {
        super("account-already-initialized");
    }
}
