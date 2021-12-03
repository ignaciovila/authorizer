package com.nubank.exam;

import com.nubank.exam.adapters.OperationsProcessor;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        String fileName = args[0];

        try {
            new OperationsProcessor().process(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
