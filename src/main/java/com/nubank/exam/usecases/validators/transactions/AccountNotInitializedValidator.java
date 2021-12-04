package com.nubank.exam.usecases.validators.transactions;

import java.util.List;

public class AccountNotInitializedValidator {
    public void validate(List<String> violations, Boolean activeCard, Long availableLimit) {
        if (activeCard == null || availableLimit == null) {
            violations.add("account-not-initialized");
        }
    }
}
