package com.nubank.exam.domain.entities.state;

import com.nubank.exam.domain.entities.input.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class AccountState {
    private Boolean activeCard;
    private Long availableLimit;
    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public boolean isInitialized() {
        return activeCard != null && availableLimit != null;
    }

    public boolean isNotInitialized() {
        return activeCard == null || availableLimit == null;
    }

    public boolean isInactiveCard() {
        return activeCard != null && !activeCard;
    }

    public boolean isInvalidTransaction(Transaction transaction) {
        if (transaction == null || availableLimit == null) {
            return false;
        }

        return transaction.getAmount() > availableLimit;
    }
}
