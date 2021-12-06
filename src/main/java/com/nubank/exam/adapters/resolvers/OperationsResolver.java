package com.nubank.exam.adapters.resolvers;

import com.nubank.exam.adapters.mappers.OperationsFileParser;
import com.nubank.exam.adapters.presenters.AccountStatusPresenter;
import com.nubank.exam.domain.entities.input.Operation;
import com.nubank.exam.domain.entities.output.AccountStatus;
import com.nubank.exam.domain.input.OperationsInput;
import com.nubank.exam.domain.output.OperationsOutput;
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

        // return the output
        return accountStatusPresenter.present(output);
    }
}
