package com.nubank.exam.usecases.validators.transactions;

import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.Violations;
import com.nubank.exam.domain.input.Transaction;
import com.nubank.exam.usecases.validators.transactions.util.ValidatorDateUtil;
import java.util.ArrayList;
import java.util.List;

public class HighFrequencySmallIntervalValidator implements TransactionValidator {

    private final ValidatorDateUtil validatorDateUtil = new ValidatorDateUtil();

    public void validate(List<Violations> violations, AccountState accountState, Transaction transaction) {
        List<Transaction> lastTransactions = new ArrayList<>(accountState.getTransactions());
        validatorDateUtil.filterLastTwoMinutesTransactions(transaction, lastTransactions);

        if (lastTransactions.size() > 2) {
            violations.add(Violations.HIGH_FREQUENCY_SMALL_INTERVAL);
        }
    }
}
