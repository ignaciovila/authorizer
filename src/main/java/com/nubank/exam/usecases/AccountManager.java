package com.nubank.exam.usecases;

import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.input.AccountCreation;
import com.nubank.exam.domain.input.TransactionAuthorization;
import com.nubank.exam.usecases.validators.AccountAlreadyInitializedValidator;
import com.nubank.exam.usecases.validators.AccountNotInitializedValidator;
import com.nubank.exam.usecases.validators.CardNotActiveValidator;
import com.nubank.exam.usecases.validators.DoubledTransactionValidator;
import com.nubank.exam.usecases.validators.HighFrequencySmallIntervalValidator;
import com.nubank.exam.usecases.validators.InsufficientLimitValidator;
import java.util.List;
import lombok.Getter;

@Getter
public class AccountManager {

    private Boolean activeCard;
    private Long availableLimit;

    private final AccountAlreadyInitializedValidator accountAlreadyInitializedValidator = new AccountAlreadyInitializedValidator();
    private final AccountNotInitializedValidator accountNotInitializedValidator = new AccountNotInitializedValidator();
    private final CardNotActiveValidator cardNotActiveValidator = new CardNotActiveValidator();
    private final InsufficientLimitValidator insufficientLimitValidator = new InsufficientLimitValidator();
    private final HighFrequencySmallIntervalValidator highFrequencySmallIntervalValidator = new HighFrequencySmallIntervalValidator();
    private final DoubledTransactionValidator doubledTransactionValidator = new DoubledTransactionValidator();
    private final AccountState accountState = new AccountState();

    public void create(List<String> violations, AccountCreation accountCreation) {
        accountAlreadyInitializedValidator.validate(violations, activeCard, availableLimit);

        if (!violations.isEmpty()) {
            return;
        }

        this.activeCard = accountCreation.getAccount().getActiveCard();
        this.availableLimit = accountCreation.getAccount().getAvailableLimit();
    }

    public void authorize(TransactionAuthorization transactionAuthorization, List<String> violations) {
        accountNotInitializedValidator.validate(violations, activeCard, availableLimit);
        cardNotActiveValidator.validate(violations, activeCard);
        insufficientLimitValidator.validate(violations, availableLimit, transactionAuthorization.getTransaction());
        highFrequencySmallIntervalValidator.validate(violations, transactionAuthorization.getTransaction(), accountState);
        doubledTransactionValidator.validate(violations, transactionAuthorization.getTransaction(), accountState);

        if (!violations.isEmpty()) {
            return;
        }

        availableLimit -= transactionAuthorization.getTransaction().getAmount();;

        accountState.addTransaction(transactionAuthorization.getTransaction());
    }
}
