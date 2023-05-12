package ua.foxminded.task4;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javafx.util.Pair;

public class Calculator {
    public LongDivisionResult doLongDivision(int dividend, int divisor) {
        inputValidation(dividend, divisor);
        int quotient = dividend / divisor;

        List<Pair<Integer, Integer>> longDivisionStepsResult = calculateLongDivisionSteps(separateDividend(dividend),
                divisor);

        LongDivisionResult longDivisionResult = new LongDivisionResult();
        longDivisionResult.setDividend(dividend);
        longDivisionResult.setDivisor(divisor);
        longDivisionResult.setQuotient(quotient);
        longDivisionResult.setDivisionSteps(longDivisionStepsResult);
        return longDivisionResult;
    }

    private List<Pair<Integer, Integer>> calculateLongDivisionSteps(List<Integer> separatedDividendDigits, int divisor) {
        int remainder;
        int beginIndex = 0;
        int endIndex = 0;
        int partOfDividend = 0;
        List<Pair<Integer, Integer>> divisionStep = new LinkedList<>();
        while (beginIndex <= endIndex && endIndex < separatedDividendDigits.size()) {
            if (beginIndex == endIndex) {
                partOfDividend = separatedDividendDigits.get(beginIndex);
            } else {
                partOfDividend = Integer.parseInt(partOfDividend + String.valueOf(separatedDividendDigits.get(endIndex)));
            }
            if (partOfDividend < divisor) {
                endIndex++;
            } else {
                remainder = partOfDividend - (partOfDividend % divisor);
                divisionStep.add(new Pair<>(partOfDividend, remainder));
                if (partOfDividend % divisor > 0) {
                    separatedDividendDigits.set(endIndex, partOfDividend % divisor);
                } else {
                    endIndex++;
                }
                beginIndex = endIndex;
            }
        }
        return divisionStep;
    }

    private List<Integer> separateDividend(int dividend) {
        List<Integer> separatedDividendDigits = new LinkedList<>();
        while (dividend > 0) {
            separatedDividendDigits.add(dividend % 10);
            dividend /= 10;
        }
        Collections.reverse(separatedDividendDigits);
        return separatedDividendDigits;
    }

    private void inputValidation(int dividend, int divisor) {
        if (dividend < 0 || divisor < 0) {
            throw new IllegalArgumentException("Input number must be positive");
        } else if (dividend == 0) {
            throw new IllegalArgumentException("If dividend is 0, then answer is 0");
        } else if (divisor == 0) {
            throw new IllegalArgumentException("Input divisor must not be 0");
        } else if (dividend < divisor) {
            throw new IllegalArgumentException("Dividend must be bigger than divisor");
        }
    }
}
