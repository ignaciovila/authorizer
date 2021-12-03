package com.nubank.exam.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubank.exam.domain.input.AccountCreation;
import com.nubank.exam.domain.input.InvalidOperation;
import com.nubank.exam.domain.input.Operation;
import com.nubank.exam.domain.input.TransactionAuthorization;

public class OperationMapper {

    private Exception accountCreationMappingException;
    private Exception transactionAuthorizationMappingException;

    public Operation map(String operation) {
        try {
            return new ObjectMapper().readValue(operation, AccountCreation.class);
        } catch (Exception exception) {
            accountCreationMappingException = exception;
        }

        try {
            return new ObjectMapper().readValue(operation, TransactionAuthorization.class);
        } catch (Exception exception) {
            transactionAuthorizationMappingException = exception;
        }

        accountCreationMappingException.printStackTrace();
        transactionAuthorizationMappingException.printStackTrace();

        return new InvalidOperation();
    }
}
