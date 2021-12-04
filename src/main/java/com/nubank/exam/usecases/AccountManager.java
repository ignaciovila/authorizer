package com.nubank.exam.usecases;

import com.nubank.exam.domain.Account;
import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.input.AccountCreation;
import com.nubank.exam.domain.input.TransactionAuthorization;
import com.nubank.exam.usecases.validators.AccountCreationValidator;
import com.nubank.exam.usecases.validators.TransactionAuthorizationValidator;
import java.util.List;

public class AccountManager {

    private final AccountState accountState = new AccountState();

    public Account createAccount(AccountCreation accountCreation, List<String> violations) {
        AccountCreationValidator validator = AccountCreationValidator.builder()
                        .violations(violations)
                        .activeCard(accountState.getActiveCard())
                        .availableLimit(accountState.getAvailableLimit())
                        .build();

        validator.validate();

        if (violations.isEmpty()) {
            accountState.setActiveCard(accountCreation.getAccount().getActiveCard());
            accountState.setAvailableLimit(accountCreation.getAccount().getAvailableLimit());
        }

        return new Account(accountState.getActiveCard(), accountState.getAvailableLimit());
    }

    public Account authorizeTransaction(TransactionAuthorization transactionAuthorization, List<String> violations) {
        TransactionAuthorizationValidator validator = TransactionAuthorizationValidator.builder()
               .violations(violations)
               .activeCard(accountState.getActiveCard())
               .availableLimit(accountState.getAvailableLimit())
               .transaction(transactionAuthorization.getTransaction())
               .accountState(accountState)
               .build();

        validator.validate();

        if (violations.isEmpty()) {
            accountState.setAvailableLimit(accountState.getAvailableLimit() - transactionAuthorization.getTransaction().getAmount());
            accountState.addTransaction(transactionAuthorization.getTransaction());
        }

        return new Account(accountState.getActiveCard(), accountState.getAvailableLimit());
    }
}
