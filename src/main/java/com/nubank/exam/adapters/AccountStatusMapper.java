package com.nubank.exam.adapters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubank.exam.domain.output.AccountStatus;

public class AccountStatusMapper {
    public String map(AccountStatus accountStatus) {
        ObjectMapper objectMapper = new ObjectMapper();
        String output = "";

        try {
            output = objectMapper.writeValueAsString(accountStatus);
        } catch (JsonProcessingException ignored) {
        }

        return output;
    }
}
