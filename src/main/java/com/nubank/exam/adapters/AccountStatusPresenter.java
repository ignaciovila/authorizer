package com.nubank.exam.adapters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubank.exam.domain.output.AccountStatus;
import java.util.List;

public class AccountStatusPresenter {
    public void present(List<AccountStatus> accountStatuses) {
        ObjectMapper objectMapper = new ObjectMapper();
        accountStatuses.forEach(accountStatus -> {
            try {
                String output = objectMapper.writeValueAsString(accountStatus);
                System.out.println(output);
            } catch (JsonProcessingException ignored) {
            }
        });
    }
}
