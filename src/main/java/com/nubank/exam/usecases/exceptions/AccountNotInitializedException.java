package com.nubank.exam.usecases.exceptions;

public class AccountNotInitializedException extends ValidationException {
    public AccountNotInitializedException() {
        super("account-not-initialized");
    }
}
