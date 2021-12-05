package com.nubank.exam.usecases.validators.transactions;

import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.Violations;
import com.nubank.exam.domain.input.Transaction;
import java.util.List;

public class InsufficientLimitValidator implements TransactionValidator {
    public void validate(List<Violations> violations, AccountState accountState, Transaction transaction) {
        if (accountState.isInsufficientLimitTransaction(transaction)) {
            violations.add(Violations.INSUFFICIENT_LIMIT);
        }
    }
}
