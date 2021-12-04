package com.nubank.exam.usecases.validators;

import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.input.Transaction;
import java.util.ArrayList;
import java.util.List;

public class HighFrequencySmallIntervalValidator extends DateValidator {
    public void validate(List<String> violations, Transaction transaction, AccountState accountState) {
        List<Transaction> lastTransactions = new ArrayList<>(accountState.getTransactions());
        filterLastTwoMinutesTransactions(transaction, lastTransactions);

        if (lastTransactions.size() > 2) {
            violations.add("high-frequency-small-interval");
        }
    }
}
