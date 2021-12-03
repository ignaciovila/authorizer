package com.nubank.exam.usecases.exceptions;

public class AccountNotInitializedException extends Exception {
    public AccountNotInitializedException() {
        super("account-not-initialized");
    }
}
