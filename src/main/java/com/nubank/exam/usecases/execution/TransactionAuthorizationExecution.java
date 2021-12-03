package com.nubank.exam.usecases.execution;

import com.nubank.exam.domain.input.Operation;
import com.nubank.exam.domain.input.TransactionAuthorization;
import com.nubank.exam.domain.output.AccountStatus;
import com.nubank.exam.domain.output.OutputAccount;
import com.nubank.exam.usecases.AccountManager;
import com.nubank.exam.usecases.exceptions.CardNotActiveException;
import com.nubank.exam.usecases.exceptions.InsufficientLimitException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionAuthorizationExecution implements OperationExecution {
    @Override
    public Optional<AccountStatus> execute(Operation operation) {
        TransactionAuthorization transactionAuthorization = (TransactionAuthorization) operation;
        AccountManager accountManager = AccountManager.getInstance();
        List<String> violations = new ArrayList<>();

        try {
            accountManager.authorize(transactionAuthorization);
        } catch (InsufficientLimitException|CardNotActiveException e) {
            violations.add(e.getMessage());
        }

        OutputAccount outputAccount = OutputAccount.builder()
                .activeCard(accountManager.getActiveCard())
                .availableLimit(accountManager.getAvailableLimit())
                .violations(violations)
                .build();

        AccountStatus accountStatus = AccountStatus.builder()
                .account(outputAccount)
                .build();

        return Optional.of(accountStatus);
    }
}
