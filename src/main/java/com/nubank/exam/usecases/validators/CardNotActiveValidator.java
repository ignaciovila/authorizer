package com.nubank.exam.usecases.validators;

import java.util.List;

public class CardNotActiveValidator {
    public void validate(List<String> violations, Boolean activeCard) {
        if (activeCard != null && !activeCard) {
            violations.add("card-not-active");
        }
    }
}
