package com.nubank.exam.usecases.validators;

import com.nubank.exam.usecases.exceptions.ValidationException;

public interface OperationValidator {
    void validate(Boolean activeCard, Long availableLimit, Long transactionAmount) throws ValidationException;
}
