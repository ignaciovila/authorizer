package com.nubank.exam.adapters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubank.exam.domain.output.AccountStatus;

public class AccountStatusMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String map(AccountStatus accountStatus) {
        String output;

        try {
            output = objectMapper.writeValueAsString(accountStatus);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }

        return output;
    }
}
