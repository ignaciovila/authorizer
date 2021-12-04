package com.nubank.exam.usecases.validators;

import com.nubank.exam.usecases.exceptions.InsufficientLimitException;
import com.nubank.exam.usecases.exceptions.ValidationException;

public class InsufficientLimitValidator implements OperationValidator {
    @Override
    public void validate(Boolean activeCard, Long availableLimit, Long transactionAmount) throws ValidationException {
        if (availableLimit < transactionAmount) {
            throw new InsufficientLimitException();
        }
    }
}
