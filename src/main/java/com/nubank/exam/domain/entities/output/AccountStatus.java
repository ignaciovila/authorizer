package com.nubank.exam.domain.entities.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nubank.exam.domain.entities.input.account.Account;
import com.nubank.exam.domain.enums.Violations;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatus {
    @JsonProperty
    private Account account;
    @JsonProperty
    private List<Violations> violations;
}
