package com.nubank.exam.usecases;

import com.nubank.exam.domain.input.AccountCreation;
import com.nubank.exam.domain.input.TransactionAuthorization;
import com.nubank.exam.usecases.exceptions.ValidationException;
import com.nubank.exam.usecases.validators.AccountAlreadyInitializedValidator;
import com.nubank.exam.usecases.validators.AccountNotInitializedValidator;
import com.nubank.exam.usecases.validators.CardNotActiveValidator;
import com.nubank.exam.usecases.validators.InsufficientLimitValidator;
import com.nubank.exam.usecases.validators.OperationValidator;
import java.util.List;
import lombok.Getter;

@Getter
public class AccountManager {

    private Boolean activeCard;
    private Long availableLimit;

    private final List<OperationValidator> creationValidators = List.of(new AccountAlreadyInitializedValidator());
    private final List<OperationValidator> authorizationValidators = List.of(
            new AccountNotInitializedValidator(),
            new CardNotActiveValidator(),
            new InsufficientLimitValidator());

    public void create(AccountCreation accountCreation) throws ValidationException {
        for (OperationValidator validator : creationValidators) {
            validator.validate(activeCard, availableLimit, null);
        }

        this.activeCard = accountCreation.getAccount().getActiveCard();
        this.availableLimit = accountCreation.getAccount().getAvailableLimit();
    }

    public void authorize(TransactionAuthorization transactionAuthorization) throws ValidationException {
        for (OperationValidator validator : authorizationValidators) {
            validator.validate(activeCard, availableLimit, transactionAuthorization.getTransaction().getAmount());
        }

        availableLimit -= transactionAuthorization.getTransaction().getAmount();;
    }
}
