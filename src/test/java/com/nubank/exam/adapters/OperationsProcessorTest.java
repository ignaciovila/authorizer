package com.nubank.exam.adapters;

import java.io.IOException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

class OperationsProcessorTest extends BaseTest {

    @Test
    void accountCreationSuccess() throws JSONException, IOException {
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
}