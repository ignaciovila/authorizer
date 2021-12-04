package com.nubank.exam.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Violations {
    ACCOUNT_ALREADY_INITIALIZED("account-already-initialized"),
    ACCOUNT_NOT_INITIALIZED("account-not-initialized"),
    CARD_NOT_ACTIVE("card-not-active"),
    DOUBLED_TRANSACTION("doubled-transaction"),
    HIGH_FREQUENCY_SMALL_INTERVAL("high-frequency-small-interval"),
    INSUFFICIENT_LIMIT("insufficient-limit");

    @JsonValue
    private String description;
}
