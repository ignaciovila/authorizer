package com.nubank.exam.frameworks;

import com.nubank.exam.adapters.AccountStatusPresenter;
import com.nubank.exam.adapters.OperationsProcessor;
import com.nubank.exam.domain.output.AccountStatus;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;

@AllArgsConstructor
public class Authorizer implements CommandLineRunner {

    private final OperationsProcessor operationsProcessor;
    private final AccountStatusPresenter accountStatusPresenter;

    public void run(String... args) {
        String fileName = args[0];

        List<AccountStatus> output = operationsProcessor.process(fileName);

        accountStatusPresenter.present(output);
    }
}
