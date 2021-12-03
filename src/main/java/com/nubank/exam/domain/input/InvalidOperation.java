package com.nubank.exam.domain.input;

import com.nubank.exam.domain.OperationType;

public class InvalidOperation implements Operation {
    @Override
    public OperationType getType() {
        return OperationType.INVALID_OPERATION;
    }
}
