package com.nubank.exam.domain;

import com.nubank.exam.domain.input.Transaction;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class AccountState {
    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
