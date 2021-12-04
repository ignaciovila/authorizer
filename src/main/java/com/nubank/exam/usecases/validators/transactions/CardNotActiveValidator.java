package com.nubank.exam.usecases.validators.transactions;

import com.nubank.exam.domain.Violations;
import java.util.List;

public class CardNotActiveValidator {
    public void validate(List<Violations> violations, Boolean activeCard) {
        if (activeCard != null && !activeCard) {
            violations.add(Violations.CARD_NOT_ACTIVE);
        }
    }
}
