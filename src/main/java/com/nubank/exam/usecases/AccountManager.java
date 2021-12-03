package com.nubank.exam.usecases;

import com.nubank.exam.domain.input.AccountCreation;
import com.nubank.exam.domain.input.TransactionAuthorization;
import com.nubank.exam.usecases.exceptions.AccountAlreadyInitializedException;
import com.nubank.exam.usecases.exceptions.AccountNotInitializedException;
import com.nubank.exam.usecases.exceptions.CardNotActiveException;
import com.nubank.exam.usecases.exceptions.InsufficientLimitException;
import lombok.Getter;

@Getter
public class AccountManager {

    private Boolean activeCard;
    private Long availableLimit;

    public void create(AccountCreation accountCreation) throws AccountAlreadyInitializedException {
        if (this.activeCard != null || this.availableLimit != null) {
            throw new AccountAlreadyInitializedException();
        }

        this.activeCard = accountCreation.getAccount().getActiveCard();
        this.availableLimit = accountCreation.getAccount().getAvailableLimit();
    }

    public void authorize(TransactionAuthorization transactionAuthorization) throws InsufficientLimitException, CardNotActiveException, AccountNotInitializedException {
        if (this.activeCard == null || this.availableLimit == null) {
            throw new AccountNotInitializedException();
        }

        if (!activeCard) {
            throw new CardNotActiveException();
        }

        if (availableLimit < transactionAuthorization.getTransaction().getAmount()) {
            throw new InsufficientLimitException();
        }

        availableLimit -= transactionAuthorization.getTransaction().getAmount();;
    }
}
