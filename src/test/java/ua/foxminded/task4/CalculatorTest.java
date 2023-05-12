package ua.foxminded.task4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.util.Pair;

class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void initialization() {
        calculator = new Calculator();
    }

    @Test
    void testCalculateLongDivision() {
        LongDivisionResult actual = calculator.doLongDivision(4541, 32);
        int dividend = actual.getDividend();
        int divisor = actual.getDivisor();
        int quotient = actual.getQuotient();
        List<Pair<Integer, Integer>> divisionSteps = actual.getDivisionSteps();
        String actualString = dividend + " " + divisor + " " + quotient
                + " " + divisionSteps;
        assertEquals("4541 32 141 [45=32, 134=128, 61=32]", actualString);
    }

    @Test
    void testRequirePositiveNumberDividend() {

        assertThrows(IllegalArgumentException.class, () -> calculator.doLongDivision(-2, 6));
    }

    @Test
    void testRequirePositiveNumberDivisor() {
        assertThrows(IllegalArgumentException.class, () -> calculator.doLongDivision(6, -2));
    }

    @Test
    void testIfDividendIsZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.doLongDivision(0, 45));
    }

    @Test
    void testIfDivisorIsZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.doLongDivision(45, 0));
    }

    @Test
    void testRequireDividendBiggerThanDivisor() {
        assertThrows(IllegalArgumentException.class, () -> calculator.doLongDivision(121, 4894));
    }
}
