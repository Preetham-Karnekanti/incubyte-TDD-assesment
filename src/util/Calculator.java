package util;

import Exceptions.NegativeNumberException;

import java.util.Arrays;
import java.util.List;

public class Calculator {

    public static final String DELIMITER_FOR_PRODUCT = "\\*";
    public final String DEFAULT_DELIMITER = "[,\n]";

    public int add(String numbers) throws NegativeNumberException , IllegalArgumentException{
        if (numbers.isBlank()) {
            return 0;
        }
        String[] parsedNumbers = splitNumbersBasedOnDelimiter(numbers);
        List<Integer> negativeNumbers = Arrays.stream(parsedNumbers).map(String::strip).mapToInt(Integer::parseInt).filter(x -> x < 0).boxed().toList();
        if (!negativeNumbers.isEmpty())
            throw new NegativeNumberException("negative numbers are not allowed: " + negativeNumbers);
        if(numbers.startsWith("//*")){
            return getProduct(parsedNumbers);
        }
        return Arrays.stream(parsedNumbers).map(String::strip).mapToInt(Integer::parseInt).sum();
    }

    private int getProduct(String[] parsedNumbers) {
        int product = 1;
        for (String parsedNumber : parsedNumbers) {
            product *= Integer.parseInt((parsedNumber));
        }
        return product;
    }


    private String[] splitNumbersBasedOnDelimiter(String numbers) {
        String delimiter = extractDelimiter(numbers);
        int delimiterIndex = numbers.startsWith("//")  ? numbers.indexOf("\n") : -1;
        String numberString = numbers.substring(delimiterIndex + 1);
        if (delimiter.equals("*")) {
            return numberString.split(DELIMITER_FOR_PRODUCT);
        }
        return numberString.split(delimiter);
    }

    private String extractDelimiter(String numbers) {
        String delimiter = DEFAULT_DELIMITER;
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterIndex);
            validateDelimiter(delimiter);
        }
        return delimiter;
    }

    private static void validateDelimiter(String delimiter) {
        if (delimiter.length() > 1) throw new IllegalArgumentException("should contain only 1 custom delimiter");
    }
}
