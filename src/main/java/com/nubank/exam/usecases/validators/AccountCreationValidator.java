package com.nubank.exam.usecases.validators;

import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.Violations;
import com.nubank.exam.usecases.validators.account.AccountAlreadyInitializedValidator;
import java.util.List;
import lombok.Builder;

@Builder
public class AccountCreationValidator {
    private List<Violations> violations;
    private AccountState accountState;

    private final AccountAlreadyInitializedValidator accountAlreadyInitializedValidator = new AccountAlreadyInitializedValidator();

    public void validate() {
        accountAlreadyInitializedValidator.validate(violations, accountState.getActiveCard(), accountState.getAvailableLimit());
    }
}
