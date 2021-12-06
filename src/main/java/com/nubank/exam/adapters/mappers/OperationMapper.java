package com.nubank.exam.adapters.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubank.exam.domain.entities.input.InvalidOperation;
import com.nubank.exam.domain.entities.input.Operation;
import com.nubank.exam.domain.entities.input.account.AccountCreation;
import com.nubank.exam.domain.entities.input.transaction.TransactionAuthorization;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OperationMapper {

    private final ObjectMapper objectMapper;

    public Operation map(String operation) {
        Exception accountCreationMappingException;
        Exception transactionAuthorizationMappingException;

        try {
            return objectMapper.readValue(operation, AccountCreation.class);
        } catch (Exception exception) {
            accountCreationMappingException = exception;
        }

        try {
            return objectMapper.readValue(operation, TransactionAuthorization.class);
        } catch (Exception exception) {
            transactionAuthorizationMappingException = exception;
        }

        accountCreationMappingException.printStackTrace();
        transactionAuthorizationMappingException.printStackTrace();

        return new InvalidOperation();
    }
}
