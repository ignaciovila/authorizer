package com.nubank.exam.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubank.exam.adapters.controllers.OperationsController;
import com.nubank.exam.adapters.mappers.AccountStatusMapper;
import com.nubank.exam.adapters.mappers.OperationMapper;
import com.nubank.exam.adapters.mappers.OperationsFileParser;
import com.nubank.exam.adapters.presenters.AccountStatusPresenter;
import com.nubank.exam.adapters.resolvers.OperationsResolver;
import com.nubank.exam.frameworks.Authorizer;
import com.nubank.exam.usecases.AccountManager;
import com.nubank.exam.usecases.ExecuteOperationUseCase;
import com.nubank.exam.usecases.validators.AccountCreationValidator;
import com.nubank.exam.usecases.validators.TransactionAuthorizationValidator;
import com.nubank.exam.usecases.validators.account.AccountAlreadyInitializedValidator;
import com.nubank.exam.usecases.validators.transactions.AccountNotInitializedValidator;
import com.nubank.exam.usecases.validators.transactions.CardNotActiveValidator;
import com.nubank.exam.usecases.validators.transactions.DoubledTransactionValidator;
import com.nubank.exam.usecases.validators.transactions.HighFrequencySmallIntervalValidator;
import com.nubank.exam.usecases.validators.transactions.InsufficientLimitValidator;
import com.nubank.exam.usecases.validators.transactions.util.ValidatorDateUtil;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public Authorizer authorizer(OperationsController operationsController) {
        return new Authorizer(operationsController);
    }

    @Bean
    public OperationsController operationsProcessor(OperationsResolver operationsResolver) {
        return new OperationsController(operationsResolver);
    }

    @Bean
    public OperationsResolver operationsResolver(OperationsFileParser operationsFileParser, ExecuteOperationUseCase executeOperationUseCase, AccountStatusPresenter accountStatusPresenter) {
        return new OperationsResolver(operationsFileParser, executeOperationUseCase, accountStatusPresenter);
    }

    @Bean
    public OperationsFileParser operationsFileParser(OperationMapper operationMapper) {
        return new OperationsFileParser(operationMapper);
    }

    @Bean
    public OperationMapper operationMapper(ObjectMapper objectMapper) {
        return new OperationMapper(objectMapper);
    }

    @Bean
    public AccountStatusPresenter accountStatusPresenter(AccountStatusMapper accountStatusMapper) {
        return new AccountStatusPresenter(accountStatusMapper);
    }

    @Bean
    public AccountStatusMapper accountStatusMapper(ObjectMapper objectMapper) {
        return new AccountStatusMapper(objectMapper);
    }

    @Bean
    public ExecuteOperationUseCase executeOperationUseCase(AccountManager accountManager) {
        return new ExecuteOperationUseCase(accountManager);
    }

    @Bean
    public AccountManager accountManager(AccountCreationValidator accountCreationValidator, TransactionAuthorizationValidator transactionAuthorizationValidator) {
        return new AccountManager(accountCreationValidator, transactionAuthorizationValidator);
    }

    @Bean
    public AccountCreationValidator accountCreationValidator(AccountAlreadyInitializedValidator accountAlreadyInitializedValidator) {
        return new AccountCreationValidator(accountAlreadyInitializedValidator);
    }

    @Bean
    public AccountAlreadyInitializedValidator accountAlreadyInitializedValidator() {
        return new AccountAlreadyInitializedValidator();
    }

    @Bean
    public TransactionAuthorizationValidator transactionAuthorizationValidator(
            AccountNotInitializedValidator accountNotInitializedValidator,
            CardNotActiveValidator cardNotActiveValidator,
            InsufficientLimitValidator insufficientLimitValidator,
            HighFrequencySmallIntervalValidator highFrequencySmallIntervalValidator,
            DoubledTransactionValidator doubledTransactionValidator) {
        return new TransactionAuthorizationValidator(List.of(accountNotInitializedValidator,
                cardNotActiveValidator,
                insufficientLimitValidator,
                highFrequencySmallIntervalValidator,
                doubledTransactionValidator));
    }

    @Bean
    public AccountNotInitializedValidator accountNotInitializedValidator() {
        return new AccountNotInitializedValidator();
    }

    @Bean
    public CardNotActiveValidator cardNotActiveValidator() {
        return new CardNotActiveValidator();
    }

    @Bean
    public InsufficientLimitValidator insufficientLimitValidator() {
        return new InsufficientLimitValidator();
    }

    @Bean
    public HighFrequencySmallIntervalValidator highFrequencySmallIntervalValidator(ValidatorDateUtil validatorDateUtil) {
        return new HighFrequencySmallIntervalValidator(validatorDateUtil);
    }

    @Bean
    public DoubledTransactionValidator doubledTransactionValidator(ValidatorDateUtil validatorDateUtil) {
        return new DoubledTransactionValidator(validatorDateUtil);
    }

    @Bean
    public ValidatorDateUtil validatorDateUtil() {
        return new ValidatorDateUtil();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
