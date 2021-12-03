package com.nubank.exam.usecases;

import com.nubank.exam.domain.OperationType;
import com.nubank.exam.domain.output.AccountStatus;
import com.nubank.exam.domain.input.Operation;
import com.nubank.exam.usecases.execution.AccountCreationExecution;
import com.nubank.exam.usecases.execution.InvalidOperationExecution;
import com.nubank.exam.usecases.execution.OperationExecution;
import com.nubank.exam.usecases.execution.TransactionAuthorizationExecution;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class OperationExecutor {

    private final Map<OperationType, OperationExecution> operationExecutionMap;

    public OperationExecutor() {
        this.operationExecutionMap = Map.of(
                OperationType.ACCOUNT_CREATION, new AccountCreationExecution(),
                OperationType.TRANSACTION_AUTHORIZATION, new TransactionAuthorizationExecution(),
                OperationType.INVALID_OPERATION, new InvalidOperationExecution()
        );
    }

    public List<AccountStatus> execute(List<Operation> operations) {
        return operations.stream()
                .map(this::execute)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    private Optional<AccountStatus> execute(Operation operation) {
        OperationExecution execution = operationExecutionMap.getOrDefault(operation.getType(), new InvalidOperationExecution());

        return execution.execute(operation);
    }

}
