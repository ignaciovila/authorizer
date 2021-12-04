package com.nubank.exam.domain;

import com.nubank.exam.domain.input.Transaction;
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
}
