package com.nubank.exam.usecases.validators.account;

import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.Violations;
import java.util.List;

public class AccountAlreadyInitializedValidator {
    public void validate(List<Violations> violations, AccountState accountState) {
        if (accountState.isInitialized()) {
            violations.add(Violations.ACCOUNT_ALREADY_INITIALIZED);
        }
    }
}
