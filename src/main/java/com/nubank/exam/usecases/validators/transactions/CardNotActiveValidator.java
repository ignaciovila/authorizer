package com.nubank.exam.usecases.validators.transactions;

import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.Violations;
import com.nubank.exam.domain.input.Transaction;
import java.util.List;

public class CardNotActiveValidator implements TransactionValidator {
    public void validate(List<Violations> violations, AccountState accountState, Transaction transaction) {
        if (accountState.isInactiveCard()) {
            violations.add(Violations.CARD_NOT_ACTIVE);
        }
    }
}
