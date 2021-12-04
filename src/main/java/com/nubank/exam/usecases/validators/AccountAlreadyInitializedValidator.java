package com.nubank.exam.usecases.validators;

import com.nubank.exam.usecases.exceptions.AccountAlreadyInitializedException;

public class AccountAlreadyInitializedValidator implements OperationValidator {
    public void validate(Boolean activeCard, Long availableLimit, Long transactionAmount) throws AccountAlreadyInitializedException {
        if (activeCard != null || availableLimit != null) {
            throw new AccountAlreadyInitializedException();
        }
    }
}
