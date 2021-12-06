package com.nubank.exam.adapters;

import com.nubank.exam.domain.OperationsInput;
import com.nubank.exam.domain.OperationsOutput;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OperationsController {

    private final OperationsResolver operationsResolver;

    public OperationsOutput process(String fileName) {
        OperationsInput input = new OperationsInput(fileName);

        return operationsResolver.resolve(input);
    }
}
