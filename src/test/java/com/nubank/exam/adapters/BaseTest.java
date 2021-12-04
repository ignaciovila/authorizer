package com.nubank.exam.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubank.exam.domain.output.AccountStatus;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    OperationsProcessor operationsProcessor;

    @BeforeEach
    void setUp() {
        operationsProcessor = new OperationsProcessor();
    }

    void baseTest(String inputRoute, String expectedRoute) throws IOException {
        List<AccountStatus> actualList = operationsProcessor.process(inputRoute);

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(expectedRoute);
        Scanner scanner = new Scanner(file);
        List<AccountStatus> expectedList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            AccountStatus accountStatus = objectMapper.readValue(line, AccountStatus.class);
            expectedList.add(accountStatus);
        }

        Assertions.assertEquals(expectedList, actualList);
    }
}
