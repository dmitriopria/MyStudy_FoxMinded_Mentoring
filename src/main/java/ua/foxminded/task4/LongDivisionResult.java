package ua.foxminded.task4;

import java.util.List;
import javafx.util.Pair;

public class LongDivisionResult {
    private int dividend;
    private int divisor;
    private int quotient;
    private List<Pair<Integer, Integer>> divisionSteps;

    public int getDividend() {
        return dividend;
    }

    public void setDividend(int dividend) {
        this.dividend = dividend;
    }

    public int getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

    public int getQuotient() {
        return quotient;
    }

    public void setQuotient(int quotient) {
        this.quotient = quotient;
    }

    public List<Pair<Integer, Integer>> getDivisionSteps() {
        return divisionSteps;
    }

    public void setDivisionSteps(List<Pair<Integer, Integer>> divisionSteps) {
        this.divisionSteps = divisionSteps;
    }
}
