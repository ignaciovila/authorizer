package com.nubank.exam.domain.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nubank.exam.domain.Account;
import com.nubank.exam.domain.Violations;
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
