package com.nubank.exam.usecases.validators;

import com.nubank.exam.domain.Violations;
import com.nubank.exam.usecases.validators.account.AccountAlreadyInitializedValidator;
import java.util.List;
import lombok.Builder;

@Builder
public class AccountCreationValidator {
    private List<Violations> violations;
    private Boolean activeCard;
    private Long availableLimit;

    private final AccountAlreadyInitializedValidator accountAlreadyInitializedValidator = new AccountAlreadyInitializedValidator();

    public void validate() {
        accountAlreadyInitializedValidator.validate(violations, activeCard, availableLimit);
    }
}
