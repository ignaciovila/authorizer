package com.nubank.exam.domain.entities.input.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Data;

@Data
public class Transaction {
    @JsonProperty
    private String merchant;

    @JsonProperty
    private Long amount;

    @JsonProperty
    private Date time;
}
