package com.nubank.exam.usecases.execution;

import com.nubank.exam.domain.Account;
import com.nubank.exam.domain.input.AccountCreation;
import com.nubank.exam.domain.input.Operation;
import com.nubank.exam.domain.output.AccountStatus;
import com.nubank.exam.usecases.AccountManager;
import com.nubank.exam.usecases.exceptions.AccountAlreadyInitializedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountCreationExecution implements OperationExecution {

    @Override
    public Optional<AccountStatus> execute(AccountManager accountManager, Operation operation) {
        AccountCreation accountCreation = (AccountCreation) operation;
        List<String> violations = new ArrayList<>();

        try {
            accountManager.create(accountCreation);
        } catch (AccountAlreadyInitializedException e) {
           violations.add(e.getMessage());
        }

        Account account = new Account(accountManager.getActiveCard(), accountManager.getAvailableLimit());

        AccountStatus accountStatus = new AccountStatus(account, violations);

        return Optional.of(accountStatus);
    }
}
