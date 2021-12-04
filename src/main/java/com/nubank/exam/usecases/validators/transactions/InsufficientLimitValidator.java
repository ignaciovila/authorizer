package com.nubank.exam.usecases.validators.transactions;

import com.nubank.exam.domain.input.Transaction;
import java.util.List;

public class InsufficientLimitValidator {
    public void validate(List<String> violations, Long availableLimit, Transaction transaction) {
        if (availableLimit != null && transaction != null && availableLimit < transaction.getAmount()) {
            violations.add("insufficient-limit");
        }
    }
}
