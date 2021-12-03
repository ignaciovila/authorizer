package com.nubank.exam.domain.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nubank.exam.domain.OperationType;
import lombok.Data;

@Data
public class TransactionAuthorization implements Operation {
    @JsonProperty
    private Transaction transaction;

    @Override
    public OperationType getType() {
        return OperationType.TRANSACTION_AUTHORIZATION;
    }
}
