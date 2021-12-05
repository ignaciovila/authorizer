package com.nubank.exam.adapters;

import com.nubank.exam.domain.input.Operation;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OperationsFileParser {

    private final OperationMapper operationMapper;

    public List<Operation> parse(String fileName) {
        List<String> operations = new ArrayList<>();
        File file = new File(fileName);
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
