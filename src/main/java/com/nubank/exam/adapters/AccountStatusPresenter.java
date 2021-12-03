package com.nubank.exam.adapters;

import com.nubank.exam.domain.output.AccountStatus;
import java.util.List;

public class AccountStatusPresenter {
    public void present(List<AccountStatus> output) {
        AccountStatusMapper mapper = new AccountStatusMapper();
        output.stream()
                .map(mapper::map)
                .forEach(System.out::println);
    }
}
