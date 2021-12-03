package com.nubank.exam.domain.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountStatus {
    @JsonProperty
    private OutputAccount account;
}
