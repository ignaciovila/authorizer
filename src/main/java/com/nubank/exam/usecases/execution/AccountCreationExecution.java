package com.nubank.exam.usecases.execution;

import com.nubank.exam.domain.entities.input.Operation;
import com.nubank.exam.domain.entities.input.account.Account;
import com.nubank.exam.domain.entities.input.account.AccountCreation;
import com.nubank.exam.domain.entities.output.AccountStatus;
import com.nubank.exam.domain.enums.Violations;
import com.nubank.exam.usecases.AccountManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountCreationExecution implements OperationExecution {

    @Override
    public Optional<AccountStatus> execute(AccountManager accountManager, Operation operation) {
        AccountCreation accountCreation = (AccountCreation) operation;
        List<Violations> violations = new ArrayList<>();

        Account account = accountManager.createAccount(accountCreation.getAccount(), violations);

        AccountStatus accountStatus = new AccountStatus(account, violations);

        return Optional.of(accountStatus);
    }
}
