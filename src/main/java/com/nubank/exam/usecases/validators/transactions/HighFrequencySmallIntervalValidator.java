package com.nubank.exam.usecases.validators.transactions;

import com.nubank.exam.domain.entities.state.AccountState;
import com.nubank.exam.domain.entities.input.transaction.Transaction;
import com.nubank.exam.domain.enums.Violations;
import com.nubank.exam.usecases.validators.transactions.util.ValidatorDateUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HighFrequencySmallIntervalValidator implements TransactionValidator {

    private final ValidatorDateUtil validatorDateUtil;

    public void validate(List<Violations> violations, AccountState accountState, Transaction transaction) {
        List<Transaction> lastTransactions = new ArrayList<>(accountState.getTransactions());
        validatorDateUtil.filterLastTwoMinutesTransactions(transaction, lastTransactions);

        if (lastTransactions.size() > 2) {
            violations.add(Violations.HIGH_FREQUENCY_SMALL_INTERVAL);
        }
    }
}
