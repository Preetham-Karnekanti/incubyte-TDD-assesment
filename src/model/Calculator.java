package model;

import java.util.Arrays;

public class Calculator {

    public final String DEFAULT_DELIMiTER = "[,\n]";

    public int add(String numbers) {
        if (numbers.isBlank()) {
            return 0;
        }
        String delimiter = DEFAULT_DELIMiTER;
        String numberString = numbers;
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterIndex);
            numberString = numbers.substring(delimiterIndex + 1);
        }
        String[] parsedNumbers = numberString.split(delimiter);
        return Arrays.stream(parsedNumbers).map(String::strip).mapToInt(Integer::parseInt).sum();
    }
}
