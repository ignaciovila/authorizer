package com.nubank.exam.domain.entities.input.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nubank.exam.domain.entities.input.Operation;
import com.nubank.exam.domain.enums.OperationType;
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
