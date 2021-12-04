package com.nubank.exam.usecases.validators.transactions;

import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.Violations;
import com.nubank.exam.domain.input.Transaction;
import java.util.ArrayList;
import java.util.List;

public class DoubledTransactionValidator extends DateValidator {
    public void validate(List<Violations> violations, Transaction transaction, AccountState accountState) {
        List<Transaction> lastTransactions = new ArrayList<>(accountState.getTransactions());
        filterLastTwoMinutesTransactions(transaction, lastTransactions);

        boolean isDoubled = lastTransactions.stream()
                .anyMatch(t ->
                        t.getMerchant().equals(transaction.getMerchant())
                                && t.getAmount().equals(transaction.getAmount()));

        if (isDoubled) {
            violations.add(Violations.DOUBLED_TRANSACTION);
        }
    }
}
