package ua.foxminded.task4;

import java.util.List;

import javafx.util.Pair;

public class Formatter {
    public String printResult(LongDivisionResult longDivisionResult) {
        int dividend = longDivisionResult.getDividend();
        int divisor = longDivisionResult.getDivisor();
        int quotient = longDivisionResult.getQuotient();
        List<Pair<Integer, Integer>> divisionSteps = longDivisionResult.getDivisionSteps();

        StringBuilder resultStringTable = new StringBuilder();
        int lengthDividend = String.valueOf(dividend).length();
        int lengthRemainder = String.valueOf(divisionSteps.get(0).getValue()).length();
        String dividendLine = "_" + dividend + '|' + divisor;
        String separationLine = ' ' + divisionSteps.get(0).getValue().toString() + spaces(lengthDividend - lengthRemainder) + '|'
                + hyphens(lengthDividend);
        String quotientLine = ' ' + hyphens(lengthRemainder) + spaces(lengthDividend - lengthRemainder) + '|'
                + quotient;

        resultStringTable.append(dividendLine).append("\n");
        resultStringTable.append(separationLine).append("\n");
        resultStringTable.append(quotientLine).append("\n");

        String secondRowToNumber = ' ' + divisionSteps.get(0).getValue().toString();
        int lengthIndents = secondRowToNumber.length();
        int i;
        for (i = 1; i < divisionSteps.size(); ) {
            int lengthUpperNumber = divisionSteps.get(i).getKey().toString().length();
            int lengthLowerNumber = divisionSteps.get(i).getValue().toString().length();

            String upperRow = spaces(lengthIndents - lengthUpperNumber) + '_' + divisionSteps.get(i).getKey();
            lengthIndents = upperRow.length();
            String lowerRow = spaces(lengthIndents - lengthLowerNumber) + divisionSteps.get(i).getValue();
            lengthIndents = lowerRow.length();
            String dividingRow = spaces(lengthIndents - lengthLowerNumber) + hyphens(lengthLowerNumber);

            resultStringTable.append(upperRow).append("\n");
            resultStringTable.append(lowerRow).append("\n");
            resultStringTable.append(dividingRow).append("\n");
            i++;
        }
        String endRow = spaces(i + 1) + (dividend % divisor);
        resultStringTable.append(endRow);

        return resultStringTable.toString();
    }

    private String spaces(int length) {
        String spaces = "";
        for (int i = 0; i < length; i++) {
            spaces = spaces.concat(" ");
        }
        return spaces;
    }

    private String hyphens(int length) {
        String hyphens = "";
        for (int i = 0; i < length; i++) {
            hyphens = hyphens.concat("-");
        }
        return hyphens;
    }
}
