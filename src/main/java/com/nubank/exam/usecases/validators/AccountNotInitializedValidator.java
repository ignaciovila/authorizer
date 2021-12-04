package com.nubank.exam.usecases.validators;

import com.nubank.exam.usecases.exceptions.AccountNotInitializedException;
import com.nubank.exam.usecases.exceptions.ValidationException;

public class AccountNotInitializedValidator implements OperationValidator {
    @Override
    public void validate(Boolean activeCard, Long availableLimit, Long transactionAmount) throws ValidationException {
        if (activeCard == null || availableLimit == null) {
            throw new AccountNotInitializedException();
        }
    }
}
