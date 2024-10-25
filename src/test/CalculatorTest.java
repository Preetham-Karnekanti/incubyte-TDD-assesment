package test;

import model.Calculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTest {

    private final Calculator calculator = new Calculator();
    @Test
    public void shouldReturnZeroIfStringIsBlank(){
        assertEquals(0, calculator.add(""));
    }


    @Test
    public void shouldReturnOneNumberIfNumberIsGiven(){
        assertEquals(1, calculator.add("1"));
        assertEquals(1, calculator.add("1,"));
    }

    @Test
    public void shouldAddAllNumbersSeparatedByComma(){
        assertEquals(10, calculator.add("1,2,3,4"));
        assertEquals(11, calculator.add("1,2,3,5"));
    }

    @Test
    public void shouldAddAllNumbersSeparatedNewLine(){
        assertEquals(10, calculator.add("1\n2\n3\n4\n"));
    }

    @Test
    public void shouldAddAllNumbersSeparatedNewLineAndComma(){
        assertEquals(10, calculator.add("1,2\n3\n4\n"));
    }

    @Test
    public void shouldNotThrowExceptionWhenSpaceIsPresent(){
        assertDoesNotThrow(() -> calculator.add("1, 2 , 2"));
    }
}
