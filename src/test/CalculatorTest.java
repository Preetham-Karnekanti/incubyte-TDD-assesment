package test;

import Exceptions.NegativeNumberException;
import util.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    public void shouldReturnZeroIfStringIsBlank() throws NegativeNumberException {
        assertEquals(0, calculator.add(""));
    }


    @Test
    public void shouldReturnOneNumberIfNumberIsGiven() throws NegativeNumberException {
        assertEquals(1, calculator.add("1"));
        assertEquals(1, calculator.add("1,"));
    }

    @Test
    public void shouldAddAllNumbersSeparatedByComma() throws NegativeNumberException {
        assertEquals(10, calculator.add("1,2,3,4"));
        assertEquals(11, calculator.add("1,2,3,5"));
    }

    @Test
    public void shouldAddAllNumbersSeparatedNewLine() throws NegativeNumberException {
        assertEquals(10, calculator.add("1\n2\n3\n4\n"));
    }

    @Test
    public void shouldAddAllNumbersSeparatedNewLineAndComma() throws NegativeNumberException {
        assertEquals(10, calculator.add("1,2\n3\n4\n"));
    }

    @Test
    public void shouldNotThrowExceptionWhenSpaceIsPresent() {
        assertDoesNotThrow(() -> calculator.add("1, 2 , 2"));
    }

    @Test
    public void shouldAbleToSupportDifferentDelimiter() throws NegativeNumberException {
        assertEquals(6, calculator.add("//;\n1;2;3"));
        assertEquals(6, calculator.add("//#\n1#2#3"));
        assertEquals(6, calculator.add("//&\n1&2&3"));
    }

    @Test
    public void shouldMultiplyGivenWhenDelimiterIsAstrek() throws NegativeNumberException{
        assertEquals(6000, calculator.add("//*\n10*20*30"));
    }

    @Test
    public void shouldThrowExceptionWhenNegativeAreGiven() {
        Exception exception = assertThrows(NegativeNumberException.class, () ->
                calculator.add("1,2,-3,-4")
        );
        assertEquals("negative numbers are not allowed: [-3, -4]", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionIfMoreThanOneCustomDelimiter() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                calculator.add("//;,\n1;2;3")
        );
        assertEquals("should contain only 1 custom delimiter", exception.getMessage());
    }
}
