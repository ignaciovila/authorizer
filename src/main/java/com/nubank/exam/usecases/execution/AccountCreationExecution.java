package com.nubank.exam.usecases.execution;

import com.nubank.exam.domain.input.AccountCreation;
import com.nubank.exam.domain.input.Operation;
import com.nubank.exam.domain.output.AccountStatus;
import com.nubank.exam.domain.output.OutputAccount;
import com.nubank.exam.usecases.AccountManager;
import com.nubank.exam.usecases.exceptions.AccountAlreadyInitializedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountCreationExecution implements OperationExecution {
    public Optional<AccountStatus> execute(Operation operation) {
        AccountCreation accountCreation = (AccountCreation) operation;
        List<String> violations = new ArrayList<>();

        AccountManager accountManager = AccountManager.getInstance();
        try {
            accountManager.create(accountCreation);
        } catch (AccountAlreadyInitializedException e) {
           violations.add(e.getMessage());
        }

        OutputAccount outputAccount = OutputAccount.builder()
                .activeCard(accountCreation.getAccount().getActiveCard())
                .availableLimit(accountCreation.getAccount().getAvailableLimit())
                .violations(violations)
                .build();

        AccountStatus accountStatus = AccountStatus.builder()
                .account(outputAccount)
                .build();

        return Optional.of(accountStatus);
    }
}
