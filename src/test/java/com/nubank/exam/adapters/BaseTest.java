package com.nubank.exam.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubank.exam.domain.output.AccountStatus;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public abstract class BaseTest {

    OperationsProcessor operationsProcessor;

    @BeforeEach
    void setUp() {
        operationsProcessor = new OperationsProcessor();
    }

    void baseTest(String inputRoute, String expectedRoute) throws IOException, JSONException {
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

        for (int i = 0; i < expectedList.size(); i++) {
            String expected = objectMapper.writeValueAsString(expectedList.get(i));
            String actual = objectMapper.writeValueAsString(actualList.get(i));

            JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
        }
    }
}
