package com.nubank.exam.usecases.validators;

import java.util.List;

public class AccountAlreadyInitializedValidator {
    public void validate(List<String> violations, Boolean activeCard, Long availableLimit) {
        if (activeCard != null || availableLimit != null) {
            violations.add("account-already-initialized");
        }
    }
}
