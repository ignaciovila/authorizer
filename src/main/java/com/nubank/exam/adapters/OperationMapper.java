package com.nubank.exam.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubank.exam.domain.input.AccountCreation;
import com.nubank.exam.domain.input.InvalidOperation;
import com.nubank.exam.domain.input.Operation;
import com.nubank.exam.domain.input.TransactionAuthorization;

public class OperationMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

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
