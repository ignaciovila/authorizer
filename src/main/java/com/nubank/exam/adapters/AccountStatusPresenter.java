package com.nubank.exam.adapters;

import com.nubank.exam.domain.output.AccountStatus;
import java.util.List;

public class AccountStatusPresenter {

    private final AccountStatusMapper mapper = new AccountStatusMapper();

    public void present(List<AccountStatus> output) {
        output.stream()
                .map(mapper::map)
                .forEach(System.out::println);
    }
}
