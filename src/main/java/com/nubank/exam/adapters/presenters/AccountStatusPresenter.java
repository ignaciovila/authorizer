package com.nubank.exam.adapters.presenters;

import com.nubank.exam.adapters.mappers.AccountStatusMapper;
import com.nubank.exam.domain.entities.output.AccountStatus;
import com.nubank.exam.domain.output.OperationsOutput;
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
