package com.nubank.exam.domain.entities.input;

import com.nubank.exam.domain.enums.OperationType;

public class InvalidOperation implements Operation {
    @Override
    public OperationType getType() {
        return OperationType.INVALID_OPERATION;
    }
}
