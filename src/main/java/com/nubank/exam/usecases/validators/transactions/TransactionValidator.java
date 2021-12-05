package com.nubank.exam.usecases.validators.transactions;

import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.Violations;
import com.nubank.exam.domain.input.Transaction;
import java.util.List;

public interface TransactionValidator {
    void validate(List<Violations> violations, AccountState accountState, Transaction transaction);
}
