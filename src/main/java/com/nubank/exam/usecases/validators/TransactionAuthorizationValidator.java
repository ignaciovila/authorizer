package com.nubank.exam.usecases.validators;

import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.input.Transaction;
import java.util.List;
import lombok.Builder;

@Builder
public class TransactionAuthorizationValidator {
    
    private List<String> violations;
    private Boolean activeCard;
    private Long availableLimit;
    private Transaction transaction;
    private AccountState accountState;
    
    private final AccountNotInitializedValidator accountNotInitializedValidator = new AccountNotInitializedValidator();
    private final CardNotActiveValidator cardNotActiveValidator = new CardNotActiveValidator();
    private final InsufficientLimitValidator insufficientLimitValidator = new InsufficientLimitValidator();
    private final HighFrequencySmallIntervalValidator highFrequencySmallIntervalValidator = new HighFrequencySmallIntervalValidator();
    private final DoubledTransactionValidator doubledTransactionValidator = new DoubledTransactionValidator();

    public void validate() {
        accountNotInitializedValidator.validate(violations, activeCard, availableLimit);
        cardNotActiveValidator.validate(violations, activeCard);
        insufficientLimitValidator.validate(violations, availableLimit, transaction);
        highFrequencySmallIntervalValidator.validate(violations, transaction, accountState);
        doubledTransactionValidator.validate(violations, transaction, accountState);
    }
}
