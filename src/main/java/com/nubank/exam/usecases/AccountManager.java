package com.nubank.exam.usecases;

import com.nubank.exam.domain.Account;
import com.nubank.exam.domain.AccountState;
import com.nubank.exam.domain.Violations;
import com.nubank.exam.domain.input.AccountCreation;
import com.nubank.exam.domain.input.TransactionAuthorization;
import com.nubank.exam.usecases.validators.AccountCreationValidator;
import com.nubank.exam.usecases.validators.TransactionAuthorizationValidator;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountManager {

    private final AccountCreationValidator accountCreationValidator;
    private final TransactionAuthorizationValidator transactionAuthorizationValidator;
    private final AccountState accountState = new AccountState();

    public Account createAccount(AccountCreation accountCreation, List<Violations> violations) {
        accountCreationValidator.validate(violations, accountState.getActiveCard(), accountState.getAvailableLimit());

        if (violations.isEmpty()) {
            accountState.setActiveCard(accountCreation.getAccount().getActiveCard());
            accountState.setAvailableLimit(accountCreation.getAccount().getAvailableLimit());
        }

        return new Account(accountState.getActiveCard(), accountState.getAvailableLimit());
    }

    public Account authorizeTransaction(TransactionAuthorization transactionAuthorization, List<Violations> violations) {
        transactionAuthorizationValidator.validate(violations, transactionAuthorization.getTransaction(), accountState);

        if (violations.isEmpty()) {
            accountState.setAvailableLimit(accountState.getAvailableLimit() - transactionAuthorization.getTransaction().getAmount());
            accountState.addTransaction(transactionAuthorization.getTransaction());
        }

        return new Account(accountState.getActiveCard(), accountState.getAvailableLimit());
    }
}
