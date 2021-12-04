package com.nubank.exam.usecases.validators.transactions;

import com.nubank.exam.domain.Violations;
import java.util.List;

public class AccountNotInitializedValidator {
    public void validate(List<Violations> violations, Boolean activeCard, Long availableLimit) {
        if (activeCard == null || availableLimit == null) {
            violations.add(Violations.ACCOUNT_NOT_INITIALIZED);
        }
    }
}
