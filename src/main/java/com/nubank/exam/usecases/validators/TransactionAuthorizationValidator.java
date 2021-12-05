package com.nubank.exam.usecases.validators;

import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.Violations;
import com.nubank.exam.domain.input.Transaction;
import com.nubank.exam.usecases.validators.transactions.TransactionValidator;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionAuthorizationValidator {
    
    private List<TransactionValidator> transactionValidators;

    public void validate(List<Violations> violations, AccountState accountState, Transaction transaction) {
        transactionValidators.forEach(transactionValidator ->
                transactionValidator.validate(violations, accountState, transaction));
    }
}
