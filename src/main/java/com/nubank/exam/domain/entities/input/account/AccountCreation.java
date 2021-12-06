package com.nubank.exam.domain.entities.input.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nubank.exam.domain.entities.input.Operation;
import com.nubank.exam.domain.enums.OperationType;
import lombok.Data;

@Data
public class AccountCreation implements Operation {
    @JsonProperty
    private Account account;

    @Override
    public OperationType getType() {
        return OperationType.ACCOUNT_CREATION;
    }
}
