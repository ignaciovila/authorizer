package com.nubank.exam.usecases.execution;

import com.nubank.exam.domain.entities.input.Operation;
import com.nubank.exam.domain.entities.output.AccountStatus;
import com.nubank.exam.usecases.AccountManager;
import java.util.Optional;

public interface OperationExecution {
    Optional<AccountStatus> execute(AccountManager accountManager, Operation operation);
}
