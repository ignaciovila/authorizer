package com.nubank.exam.configuration;

import com.nubank.exam.adapters.AccountStatusMapper;
import com.nubank.exam.adapters.AccountStatusPresenter;
import com.nubank.exam.adapters.OperationMapper;
import com.nubank.exam.adapters.OperationsFileParser;
import com.nubank.exam.adapters.OperationsProcessor;
import com.nubank.exam.frameworks.Authorizer;
import com.nubank.exam.usecases.AccountManager;
import com.nubank.exam.usecases.OperationExecutor;
import com.nubank.exam.usecases.validators.AccountCreationValidator;
import com.nubank.exam.usecases.validators.TransactionAuthorizationValidator;
import com.nubank.exam.usecases.validators.account.AccountAlreadyInitializedValidator;
import com.nubank.exam.usecases.validators.transactions.AccountNotInitializedValidator;
import com.nubank.exam.usecases.validators.transactions.CardNotActiveValidator;
import com.nubank.exam.usecases.validators.transactions.DoubledTransactionValidator;
import com.nubank.exam.usecases.validators.transactions.HighFrequencySmallIntervalValidator;
import com.nubank.exam.usecases.validators.transactions.InsufficientLimitValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public Authorizer authorizer(OperationsProcessor operationsProcessor, AccountStatusPresenter accountStatusPresenter) {
        return new Authorizer(operationsProcessor, accountStatusPresenter);
    }

    @Bean
    public OperationsProcessor operationsProcessor(OperationsFileParser operationsFileParser, OperationExecutor operationExecutor) {
        return new OperationsProcessor(operationsFileParser, operationExecutor);
    }

    @Bean
    public OperationsFileParser operationsFileParser(OperationMapper operationMapper) {
        return new OperationsFileParser(operationMapper);
    }

    @Bean
    public OperationMapper operationMapper() {
        return new OperationMapper();
    }

    @Bean
    public AccountStatusPresenter accountStatusPresenter(AccountStatusMapper accountStatusMapper) {
        return new AccountStatusPresenter(accountStatusMapper);
    }

    @Bean
    public AccountStatusMapper accountStatusMapper() {
        return new AccountStatusMapper();
    }

    @Bean
    public OperationExecutor operationExecutor(AccountManager accountManager) {
        return new OperationExecutor(accountManager);
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
        return new TransactionAuthorizationValidator(accountNotInitializedValidator,
                cardNotActiveValidator,
                insufficientLimitValidator,
                highFrequencySmallIntervalValidator,
                doubledTransactionValidator);
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
    public HighFrequencySmallIntervalValidator highFrequencySmallIntervalValidator() {
        return new HighFrequencySmallIntervalValidator();
    }

    @Bean
    public DoubledTransactionValidator doubledTransactionValidator() {
        return new DoubledTransactionValidator();
    }
}
