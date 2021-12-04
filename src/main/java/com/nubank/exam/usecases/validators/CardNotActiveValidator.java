package com.nubank.exam.usecases.validators;

import com.nubank.exam.usecases.exceptions.CardNotActiveException;
import com.nubank.exam.usecases.exceptions.ValidationException;

public class CardNotActiveValidator implements OperationValidator {
    @Override
    public void validate(Boolean activeCard, Long availableLimit, Long transactionAmount) throws ValidationException {
        if (!activeCard) {
            throw new CardNotActiveException();
        }
    }
}
