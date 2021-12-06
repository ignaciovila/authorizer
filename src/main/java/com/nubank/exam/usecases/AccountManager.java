package com.nubank.exam.usecases;

import com.nubank.exam.domain.entities.state.AccountState;
import com.nubank.exam.domain.entities.input.account.Account;
import com.nubank.exam.domain.entities.input.transaction.Transaction;
import com.nubank.exam.domain.enums.Violations;
import com.nubank.exam.usecases.validators.AccountCreationValidator;
import com.nubank.exam.usecases.validators.TransactionAuthorizationValidator;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountManager {

    private final AccountCreationValidator accountCreationValidator;
    private final TransactionAuthorizationValidator transactionAuthorizationValidator;
    private final AccountState accountState = new AccountState();

    public Account createAccount(Account account, List<Violations> violations) {
        accountCreationValidator.validate(violations, accountState);

        if (violations.isEmpty()) {
            accountState.setActiveCard(account.getActiveCard());
            accountState.setAvailableLimit(account.getAvailableLimit());
        }

        return new Account(accountState.getActiveCard(), accountState.getAvailableLimit());
    }

    public Account authorizeTransaction(Transaction transaction, List<Violations> violations) {
        transactionAuthorizationValidator.validate(violations, accountState, transaction);

        if (violations.isEmpty()) {
            accountState.setAvailableLimit(accountState.getAvailableLimit() - transaction.getAmount());
            accountState.addTransaction(transaction);
        }

        return new Account(accountState.getActiveCard(), accountState.getAvailableLimit());
    }
}
