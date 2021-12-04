package com.nubank.exam;

import com.nubank.exam.adapters.AccountStatusPresenter;
import com.nubank.exam.adapters.OperationsProcessor;
import com.nubank.exam.domain.output.AccountStatus;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = args[0];

        List<AccountStatus> output = new OperationsProcessor().process(fileName);

        new AccountStatusPresenter().present(output);
    }
}
