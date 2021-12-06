package com.nubank.exam.usecases.validators;

import com.nubank.exam.domain.entities.state.AccountState;
import com.nubank.exam.domain.entities.input.transaction.Transaction;
import com.nubank.exam.domain.enums.Violations;
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
