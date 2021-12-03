package com.nubank.exam.usecases.execution;

import com.nubank.exam.domain.input.Operation;
import com.nubank.exam.domain.input.TransactionAuthorization;
import com.nubank.exam.domain.output.AccountStatus;
import com.nubank.exam.domain.input.Account;
import com.nubank.exam.usecases.AccountManager;
import com.nubank.exam.usecases.exceptions.AccountNotInitializedException;
import com.nubank.exam.usecases.exceptions.CardNotActiveException;
import com.nubank.exam.usecases.exceptions.InsufficientLimitException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionAuthorizationExecution implements OperationExecution {
    @Override
    public Optional<AccountStatus> execute(AccountManager accountManager, Operation operation) {
        TransactionAuthorization transactionAuthorization = (TransactionAuthorization) operation;
        List<String> violations = new ArrayList<>();

        try {
            accountManager.authorize(transactionAuthorization);
        } catch (InsufficientLimitException | CardNotActiveException | AccountNotInitializedException e) {
            violations.add(e.getMessage());
        }

        Account account = new Account(accountManager.getActiveCard(),
                accountManager.getAvailableLimit());

        AccountStatus accountStatus = new AccountStatus(account, violations);

        return Optional.of(accountStatus);
    }
}
