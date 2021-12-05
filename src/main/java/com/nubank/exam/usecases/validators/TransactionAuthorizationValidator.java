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
import lombok.Builder;

@Builder
public class TransactionAuthorizationValidator {
    
    private List<Violations> violations;
    private Transaction transaction;
    private AccountState accountState;
    
    private final AccountNotInitializedValidator accountNotInitializedValidator = new AccountNotInitializedValidator();
    private final CardNotActiveValidator cardNotActiveValidator = new CardNotActiveValidator();
    private final InsufficientLimitValidator insufficientLimitValidator = new InsufficientLimitValidator();
    private final HighFrequencySmallIntervalValidator highFrequencySmallIntervalValidator = new HighFrequencySmallIntervalValidator();
    private final DoubledTransactionValidator doubledTransactionValidator = new DoubledTransactionValidator();

    public void validate() {
        accountNotInitializedValidator.validate(violations, accountState.getActiveCard(), accountState.getAvailableLimit());
        cardNotActiveValidator.validate(violations, accountState.getActiveCard());
        insufficientLimitValidator.validate(violations, accountState.getAvailableLimit(), transaction);
        highFrequencySmallIntervalValidator.validate(violations, transaction, accountState);
        doubledTransactionValidator.validate(violations, transaction, accountState);
    }
}
