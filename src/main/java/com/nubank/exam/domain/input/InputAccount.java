package com.nubank.exam.domain.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InputAccount {
    @JsonProperty("active-card")
    private Boolean activeCard;

    @JsonProperty("available-limit")
    private Long availableLimit;
}
