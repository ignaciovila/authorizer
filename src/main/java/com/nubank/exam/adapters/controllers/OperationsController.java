package com.nubank.exam.adapters.controllers;

import com.nubank.exam.adapters.resolvers.OperationsResolver;
import com.nubank.exam.domain.input.OperationsInput;
import com.nubank.exam.domain.output.OperationsOutput;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OperationsController {

    private final OperationsResolver operationsResolver;

    public OperationsOutput process(String fileName) {
        OperationsInput input = new OperationsInput(fileName);

        return operationsResolver.resolve(input);
    }
}
