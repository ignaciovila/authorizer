package com.nubank.exam.usecases;

import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.input.AccountCreation;
import com.nubank.exam.domain.input.TransactionAuthorization;
import com.nubank.exam.usecases.validators.AccountAlreadyInitializedValidator;
import com.nubank.exam.usecases.validators.TransactionAuthorizationValidator;
import java.util.List;
import lombok.Getter;

@Getter
public class AccountManager {

    private Boolean activeCard;
    private Long availableLimit;

    private final AccountAlreadyInitializedValidator accountAlreadyInitializedValidator = new AccountAlreadyInitializedValidator();

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
        TransactionAuthorizationValidator validator = TransactionAuthorizationValidator.builder()
               .violations(violations)
               .activeCard(activeCard)
               .availableLimit(availableLimit)
               .transaction(transactionAuthorization.getTransaction())
               .accountState(accountState)
               .build();

        validator.validate();

        if (!violations.isEmpty()) {
            return;
        }

        availableLimit -= transactionAuthorization.getTransaction().getAmount();;

        accountState.addTransaction(transactionAuthorization.getTransaction());
    }
}
