package com.nubank.exam.usecases.validators;

import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.Violations;
import com.nubank.exam.usecases.validators.account.AccountAlreadyInitializedValidator;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountCreationValidator {

    private final AccountAlreadyInitializedValidator accountAlreadyInitializedValidator;

    public void validate(List<Violations> violations, AccountState accountState) {
        accountAlreadyInitializedValidator.validate(violations, accountState);
    }
}
