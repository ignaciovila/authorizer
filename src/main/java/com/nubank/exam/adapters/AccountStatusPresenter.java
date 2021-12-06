package com.nubank.exam.adapters;

import com.nubank.exam.domain.OperationsOutput;
import com.nubank.exam.domain.output.AccountStatus;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountStatusPresenter {

    private final AccountStatusMapper mapper;

    public OperationsOutput present(List<AccountStatus> accountStatuses) {
        return new OperationsOutput(
                accountStatuses.stream()
                .map(mapper::map)
                .collect(Collectors.toList())
        );
    }
}
