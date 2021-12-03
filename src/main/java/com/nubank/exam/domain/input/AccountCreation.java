package com.nubank.exam.domain.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nubank.exam.domain.OperationType;
import lombok.Data;

@Data
public class AccountCreation implements Operation {
    @JsonProperty
    private InputAccount account;

    @Override
    public OperationType getType() {
        return OperationType.ACCOUNT_CREATION;
    }
}
