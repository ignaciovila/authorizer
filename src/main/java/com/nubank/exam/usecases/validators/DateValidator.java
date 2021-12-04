package com.nubank.exam.usecases.validators;

import com.nubank.exam.domain.input.Transaction;
import java.util.Calendar;
import java.util.List;

public abstract class DateValidator {
    public void filterLastTwoMinutesTransactions(Transaction transaction, List<Transaction> transactionHistory) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(transaction.getTime());
        calendar.add(Calendar.MINUTE, -2);

        transactionHistory.removeIf(t -> t.getTime().compareTo(calendar.getTime()) < 0);
    }
}
