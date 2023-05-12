package ua.foxminded.task4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter dividend: ");
        int dividendInput = userInput.nextInt();
        System.out.print("Enter divisor: ");
        int divisorInput = userInput.nextInt();

        Calculator calculator = new Calculator();
        LongDivisionResult resultObject = calculator.doLongDivision(dividendInput, divisorInput);

        Formatter formatter = new Formatter();
        String result = formatter.printResult(resultObject);
        System.out.println(result);
    }
}
