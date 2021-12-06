package com.nubank.exam.frameworks;

import com.nubank.exam.adapters.OperationsController;
import com.nubank.exam.domain.OperationsOutput;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;

@AllArgsConstructor
public class Authorizer implements CommandLineRunner {

    private final OperationsController operationsController;

    public void run(String... args) {
        String fileName = args[0];

        OperationsOutput operationsOutput = operationsController.process(fileName);

        operationsOutput.getJsons().forEach(System.out::println);
    }
}
