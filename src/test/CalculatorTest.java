package test;

import model.Calculator;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void shouldReturnZeroIfStringIsBlank(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(0,  calculator.add(""));
    }


    @Test
    public void shouldReturnOneNumberIfNumberIsGiven(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(1, calculator.add("1"));
        Assert.assertEquals(1, calculator.add("1,"));
    }

    @Test
    public void shouldAddAllNumbersSeparatedByComma(){
        Calculator calculator = new Calculator();
        Assert.assertEquals(10, calculator.add("1,2,3,4"));
        Assert.assertEquals(11, calculator.add("1,2,3,5"));
    }
}
