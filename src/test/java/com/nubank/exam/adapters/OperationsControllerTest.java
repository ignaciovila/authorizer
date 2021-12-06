package com.nubank.exam.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubank.exam.Main;
import com.nubank.exam.domain.OperationsOutput;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class, args = "src/test/resources/test-operations")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OperationsControllerTest {

    @Autowired
    OperationsController operationsController;

    @Test
    void accountCreationSuccess() throws IOException, JSONException {
        baseTest("./src/test/resources/input/account_creation_success.json",
                "./src/test/resources/expected/account_creation_success_response.json");
    }

    @Test
    void accountCreationError() throws IOException, JSONException {
        baseTest("./src/test/resources/input/account_creation_error.json",
                "./src/test/resources/expected/account_creation_error_response.json");
    }

    @Test
    void processTransactionSuccess() throws IOException, JSONException {
        baseTest("./src/test/resources/input/process_transaction_success.json",
                "./src/test/resources/expected/process_transaction_success_response.json");
    }

    @Test
    void processTransactionAccountNotInitializedError() throws IOException, JSONException {
        baseTest("./src/test/resources/input/process_transaction_account_not_initialized_error.json",
                "./src/test/resources/expected/process_transaction_account_not_initialized_error_response.json");
    }

    @Test
    void processTransactionCardNotActiveError() throws IOException, JSONException {
        baseTest("./src/test/resources/input/process_transaction_card_not_active_error.json",
                "./src/test/resources/expected/process_transaction_card_not_active_error_response.json");
    }

    @Test
    void processTransactionInsufficientLimitError() throws IOException, JSONException {
        baseTest("./src/test/resources/input/process_transaction_insufficient_limit_error.json",
                "./src/test/resources/expected/process_transaction_insufficient_limit_error_response.json");
    }

    @Test
    void processTransactionHighFrequencySmallIntervalError() throws IOException, JSONException {
        baseTest("./src/test/resources/input/process_transaction_high_frequency_small_interval_error.json",
                "./src/test/resources/expected/process_transaction_high_frequency_small_interval_error_response.json");
    }

    @Test
    void processTransactionDoubledTransactionError() throws IOException, JSONException {
        baseTest("./src/test/resources/input/process_transaction_doubled_transaction_error.json",
                "./src/test/resources/expected/process_transaction_doubled_transaction_error_response.json");
    }

    @Test
    void processTransactionMultipleErrors() throws IOException, JSONException {
        baseTest("./src/test/resources/input/process_transaction_multiple_errors.json",
                "./src/test/resources/expected/process_transaction_multiple_errors_response.json");
    }

    private void baseTest(String inputRoute, String expectedRoute) throws IOException, JSONException {
        OperationsOutput output = operationsController.process(inputRoute);

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(expectedRoute);
        Scanner scanner = new Scanner(file);
        List<String> expectedList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            expectedList.add(line);
        }

        for (int i = 0; i < expectedList.size(); i++) {
            JSONAssert.assertEquals(expectedList.get(i), output.getJsons().get(i), JSONCompareMode.NON_EXTENSIBLE);
        }
    }
}