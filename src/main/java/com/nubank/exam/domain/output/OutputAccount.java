package com.nubank.exam.domain.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutputAccount {
    @JsonProperty("active-card")
    private Boolean activeCard;

    @JsonProperty("available-limit")
    private Long availableLimit;

    @JsonProperty
    private List<String> violations;
}
