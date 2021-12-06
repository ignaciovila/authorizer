package com.nubank.exam.usecases.execution;

import com.nubank.exam.domain.entities.input.Operation;
import com.nubank.exam.domain.entities.input.account.Account;
import com.nubank.exam.domain.entities.input.transaction.TransactionAuthorization;
import com.nubank.exam.domain.entities.output.AccountStatus;
import com.nubank.exam.domain.enums.Violations;
import com.nubank.exam.usecases.AccountManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionAuthorizationExecution implements OperationExecution {

    @Override
    public Optional<AccountStatus> execute(AccountManager accountManager, Operation operation) {
        TransactionAuthorization transactionAuthorization = (TransactionAuthorization) operation;
        List<Violations> violations = new ArrayList<>();

        Account account = accountManager.authorizeTransaction(transactionAuthorization.getTransaction(), violations);

        AccountStatus accountStatus = new AccountStatus(account, violations);

        return Optional.of(accountStatus);
    }
}
