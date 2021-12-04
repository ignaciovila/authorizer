package com.nubank.exam.usecases.validators.account;

import com.nubank.exam.domain.Violations;
import java.util.List;

public class AccountAlreadyInitializedValidator {
    public void validate(List<Violations> violations, Boolean activeCard, Long availableLimit) {
        if (activeCard != null || availableLimit != null) {
            violations.add(Violations.ACCOUNT_ALREADY_INITIALIZED);
        }
    }
}
