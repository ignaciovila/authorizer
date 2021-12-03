package com.nubank.exam.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @JsonProperty("active-card")
    private Boolean activeCard;

    @JsonProperty("available-limit")
    private Long availableLimit;
}

