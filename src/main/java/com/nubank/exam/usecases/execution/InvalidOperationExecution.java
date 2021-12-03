package com.nubank.exam.usecases.execution;

import com.nubank.exam.domain.input.Operation;
import com.nubank.exam.domain.output.AccountStatus;
import com.nubank.exam.usecases.AccountManager;
import java.util.Optional;

public class InvalidOperationExecution implements OperationExecution {
    @Override
    public Optional<AccountStatus> execute(AccountManager accountManager, Operation operation) {
        return Optional.empty();
    }
}
