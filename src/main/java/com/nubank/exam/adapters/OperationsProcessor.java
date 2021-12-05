package com.nubank.exam.adapters;

import com.nubank.exam.domain.input.Operation;
import com.nubank.exam.domain.output.AccountStatus;
import com.nubank.exam.usecases.OperationExecutor;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OperationsProcessor {

    private final OperationsFileParser operationsFileParser;
    private final OperationExecutor operationExecutor;

    public List<AccountStatus> process(String fileName) {
        List<Operation> operations = operationsFileParser.parse(fileName);

        return operationExecutor.execute(operations);
    }
}
