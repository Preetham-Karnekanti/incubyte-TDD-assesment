package model;

import java.util.Arrays;

public class Calculator {

    public int add(String numbers){
        if(numbers.isBlank())
            return 0;
        String[] parsedNumbers = numbers.split(",");
        return Arrays.stream(parsedNumbers).map(String::strip).mapToInt(Integer::parseInt).sum();
    }
}
