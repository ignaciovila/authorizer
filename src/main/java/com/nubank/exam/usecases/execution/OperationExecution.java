package com.nubank.exam.usecases.execution;

import com.nubank.exam.domain.input.Operation;
import com.nubank.exam.domain.output.AccountStatus;
import java.util.Optional;

public interface OperationExecution {
    Optional<AccountStatus> execute(Operation operation);
}
