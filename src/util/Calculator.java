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
        String[] parsedNumbers = getParsedNumbers(numbers);
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


    private String[] getParsedNumbers(String numbers) {
        String delimiter = DEFAULT_DELIMiTER;
        String numberString = numbers;
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterIndex);
            if(delimiter.length() > 1)
                throw new IllegalArgumentException("should contain only 1 custom delimiter");
            numberString = numbers.substring(delimiterIndex + 1);
            if(delimiter.equals("*")){
                return numberString.split("\\*");
            }
        }
        return numberString.split(delimiter);
    }
}
