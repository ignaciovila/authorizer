package com.nubank.exam.usecases.validators.transactions;

import com.nubank.exam.domain.entities.state.AccountState;
import com.nubank.exam.domain.entities.input.transaction.Transaction;
import com.nubank.exam.domain.enums.Violations;
import java.util.List;

public class InsufficientLimitValidator implements TransactionValidator {
    public void validate(List<Violations> violations, AccountState accountState, Transaction transaction) {
        if (accountState.isInvalidTransaction(transaction)) {
            violations.add(Violations.INSUFFICIENT_LIMIT);
        }
    }
}
