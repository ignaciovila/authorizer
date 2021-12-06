package com.nubank.exam.adapters;

import com.nubank.exam.domain.OperationsInput;
import com.nubank.exam.domain.OperationsOutput;
import com.nubank.exam.domain.input.Operation;
import com.nubank.exam.domain.output.AccountStatus;
import com.nubank.exam.usecases.ExecuteOperationUseCase;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OperationsResolver {

    private OperationsFileParser operationsFileParser;
    private ExecuteOperationUseCase executeOperationUseCase;
    private AccountStatusPresenter accountStatusPresenter;

    public OperationsOutput resolve(OperationsInput input) {
        // build input
        List<Operation> operations = operationsFileParser.parse(input);

        // execute use case
        List<AccountStatus> output = executeOperationUseCase.run(operations);

        return accountStatusPresenter.present(output);
    }
}
