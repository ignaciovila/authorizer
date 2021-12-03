package com.nubank.exam.adapters;

import com.nubank.exam.domain.output.AccountStatus;
import com.nubank.exam.domain.input.Operation;
import com.nubank.exam.usecases.OperationExecutor;
import java.io.FileNotFoundException;
import java.util.List;

public class OperationsProcessor {
    public List<AccountStatus> process(String fileName) throws FileNotFoundException {
        List<Operation> operations = new OperationsFileParser().parse(fileName);

        return new OperationExecutor().execute(operations);
    }
}
