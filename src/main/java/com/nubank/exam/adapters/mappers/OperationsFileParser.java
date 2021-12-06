package com.nubank.exam.adapters.mappers;

import com.nubank.exam.domain.entities.input.Operation;
import com.nubank.exam.domain.input.OperationsInput;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OperationsFileParser {

    private OperationMapper operationMapper;

    public List<Operation> parse(OperationsInput input) {
        List<String> operations = new ArrayList<>();
        File file = new File(input.getFileName());
        Scanner scanner;

        try {
             scanner = new Scanner(file);
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("File not found");
        }

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            operations.add(data);
        }
        scanner.close();

        return operations.stream()
                .map(operationMapper::map)
                .collect(Collectors.toList());
    }
}
