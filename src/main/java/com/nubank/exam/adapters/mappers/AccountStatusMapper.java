package com.nubank.exam.adapters.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubank.exam.domain.entities.output.AccountStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountStatusMapper {

    private final ObjectMapper objectMapper;

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
