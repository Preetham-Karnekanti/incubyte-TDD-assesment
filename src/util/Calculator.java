package util;

import Exceptions.NegativeNumberException;

import java.util.Arrays;
import java.util.List;

public class Calculator {

    public final String DEFAULT_DELIMiTER = "[,\n]";

    public int add(String numbers) throws NegativeNumberException , IllegalArgumentException{
        if (numbers.isBlank()) {
            return 0;
        }
        String delimiter = DEFAULT_DELIMiTER;
        String numberString = numbers;
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterIndex);
            if(delimiter.length() > 1)
                throw new IllegalArgumentException("should contain only 1 custom delimiter");
            numberString = numbers.substring(delimiterIndex + 1);
        }
        String[] parsedNumbers = numberString.split(delimiter);
        List<Integer> negativeNumbers = Arrays.stream(parsedNumbers).map(String::strip).mapToInt(Integer::parseInt).filter(x -> x < 0).boxed().toList();
        if (!negativeNumbers.isEmpty())
            throw new NegativeNumberException("negative numbers are not allowed: " + negativeNumbers);
        return Arrays.stream(parsedNumbers).map(String::strip).mapToInt(Integer::parseInt).sum();
    }
}
