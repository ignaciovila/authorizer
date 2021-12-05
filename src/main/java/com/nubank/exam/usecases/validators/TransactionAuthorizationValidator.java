package com.nubank.exam.usecases.validators;

import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.Violations;
import com.nubank.exam.domain.input.Transaction;
import com.nubank.exam.usecases.validators.transactions.AccountNotInitializedValidator;
import com.nubank.exam.usecases.validators.transactions.CardNotActiveValidator;
import com.nubank.exam.usecases.validators.transactions.DoubledTransactionValidator;
import com.nubank.exam.usecases.validators.transactions.HighFrequencySmallIntervalValidator;
import com.nubank.exam.usecases.validators.transactions.InsufficientLimitValidator;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionAuthorizationValidator {
    
    private final AccountNotInitializedValidator accountNotInitializedValidator;
    private final CardNotActiveValidator cardNotActiveValidator;
    private final InsufficientLimitValidator insufficientLimitValidator;
    private final HighFrequencySmallIntervalValidator highFrequencySmallIntervalValidator;
    private final DoubledTransactionValidator doubledTransactionValidator;

    public void validate(List<Violations> violations, Transaction transaction, AccountState accountState) {
        accountNotInitializedValidator.validate(violations, accountState.getActiveCard(), accountState.getAvailableLimit());
        cardNotActiveValidator.validate(violations, accountState.getActiveCard());
        insufficientLimitValidator.validate(violations, accountState.getAvailableLimit(), transaction);
        highFrequencySmallIntervalValidator.validate(violations, transaction, accountState);
        doubledTransactionValidator.validate(violations, transaction, accountState);
    }
}
